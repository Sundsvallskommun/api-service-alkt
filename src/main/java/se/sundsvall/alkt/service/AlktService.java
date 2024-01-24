package se.sundsvall.alkt.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.sundsvall.alkt.configuration.LocalDateTimeTypeAdapter;
import se.sundsvall.alkt.integration.db.CaseManagerRepository;
import se.sundsvall.alkt.integration.db.ErrandRepository;
import se.sundsvall.alkt.integration.db.ObjectRepository;
import se.sundsvall.alkt.integration.db.OwnerRepository;
import se.sundsvall.alkt.integration.db.PlainTextRepository;
import se.sundsvall.alkt.integration.db.entity.ObjectEntity;
import se.sundsvall.alkt.integration.db.entity.OwnerEntity;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;
import se.sundsvall.alkt.integration.party.PartyClient;

import generated.se.sundsvall.party.PartyType;
import io.kubernetes.client.openapi.JSON;

@Service
public class AlktService {

	private static final Logger LOG = LoggerFactory.getLogger(AlktService.class);
	private final Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
			.setPrettyPrinting()
			.create();

	private final PartyClient partyClient;
	private final OwnerRepository ownerRepository;

	public AlktService(PartyClient partyClient, OwnerRepository ownerRepository) {
		this.partyClient = partyClient;
		this.ownerRepository = ownerRepository;
	}

	public String getLegalId(String partyId) {
		var legalId = partyClient.getLegalId(PartyType.PRIVATE, partyId);

		return legalId.orElseThrow(() -> new RuntimeException("Could not find legalId for partyId: " + partyId));
	}

	public void doSomething() {
		var organizations = ownerRepository.findByOrOrganisationsNr("556506-9852");
		organizations.forEach(organization -> organization.getObjects().forEach(object -> {
			LOG.info("Object: {}", object.getServeringsNamn());

			object.getErrands().forEach(errandEntity -> LOG.info("Errand: {}", errandEntity));
		}));
	}
}
