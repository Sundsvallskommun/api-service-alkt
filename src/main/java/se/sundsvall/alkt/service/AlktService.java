package se.sundsvall.alkt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.configuration.LocalDateTimeTypeAdapter;
import se.sundsvall.alkt.integration.db.CurrentPermitRepository;
import se.sundsvall.alkt.integration.db.OwnerRepository;
import se.sundsvall.alkt.integration.db.PlainTextRepository;
import se.sundsvall.alkt.integration.db.entity.ErrandEntity;
import se.sundsvall.alkt.integration.db.entity.ObjectEntity;
import se.sundsvall.alkt.integration.db.entity.OwnerEntity;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;
import se.sundsvall.alkt.integration.party.PartyClient;

import generated.se.sundsvall.party.PartyType;

@Service
public class AlktService {

	private static final Logger LOG = LoggerFactory.getLogger(AlktService.class);
	private final Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
			.setPrettyPrinting()
			.create();

	private final PartyClient partyClient;
	private final OwnerRepository ownerRepository;
	private final CurrentPermitRepository currentPermitRepository;
	private final PlainTextRepository plainTextRepository;
	private final Mapper mapper;

	public AlktService(PartyClient partyClient, OwnerRepository ownerRepository, CurrentPermitRepository currentPermitRepository,
			PlainTextRepository plainTextRepository, Mapper mapper) {
		this.partyClient = partyClient;
		this.ownerRepository = ownerRepository;
		this.currentPermitRepository = currentPermitRepository;
		this.plainTextRepository = plainTextRepository;
		this.mapper = mapper;
	}

	public String getLegalId(String partyId) {
		var legalId = partyClient.getLegalId(PartyType.PRIVATE, partyId);

		return legalId.orElseThrow(() -> new RuntimeException("Could not find legalId for partyId: " + partyId));
	}

	public Owner doSomething(String orgNumber) {
		var owner = ownerRepository.findByOrOrganisationsNr(orgNumber);

		/*for (ObjectEntity objectEntity : owner.getObjects()) {
			for (ErrandEntity errand : objectEntity.getErrands()) {
				var arendeTyp = errand.getArendeTyp();
				var byKodAndErrands = plainTextRepository.findByKodAndErrands(arendeTyp);
				var byKodAndErrandEvents = plainTextRepository.findByKodAndErrandEvents(arendeTyp);

				LOG.info("ÄrendeTyp: {}, mappas till: {}", arendeTyp, byKodAndErrands);
				LOG.info("Event: {}, mappas till: {}", arendeTyp, byKodAndErrandEvents);
			}
		}*/


		var ownerResponse = mapper.mapToResponse(owner);

		ownerResponse.getObjectList().stream()
			.flatMap(alktObject -> alktObject.getErrandsList().stream())
			.forEach(errand -> {
				addPlainTextToErrand(errand);
				errand.getEvents().forEach(this::addPlainTextToEvent);
			});

		return ownerResponse;
	}

	public void addPlainTextToErrand(Owner.AlktObject.Errand errand) {
		var errandType = errand.getErrandType();
		var byKodAndErrands = plainTextRepository.findByKodAndErrands(errandType);

		errand.setErrandTypeDescription(byKodAndErrands.map(PlainTextEntity::getKlartext).orElse(""));
	}

	public void addPlainTextToEvent(Owner.AlktObject.Errand.Event event) {
		var eventType = event.getEventType();
		var byKodAndErrandEvents = plainTextRepository.findByKodAndErrandEvents(eventType);

		event.setEventTypeDescription(byKodAndErrandEvents.map(PlainTextEntity::getKlartext).orElse(""));
	}
}
