package se.sundsvall.alkt.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import se.sundsvall.alkt.api.model.Owner;
import se.sundsvall.alkt.integration.db.entity.ErrandEntity;
import se.sundsvall.alkt.integration.db.entity.ErrandEventEntity;
import se.sundsvall.alkt.integration.db.entity.ObjectEntity;
import se.sundsvall.alkt.integration.db.entity.OwnerEntity;
import se.sundsvall.alkt.integration.db.entity.PlainTextEntity;

@Component
public class Mapper {

	public Owner mapToResponse(OwnerEntity ownerEntity) {
		var build = Owner.builder()
				.withOrganizationNumber(ownerEntity.getOrganisationsNr())
				.withOrganizationName(ownerEntity.getBolagsnamn())
				.withObjectList(ownerEntity.getObjects().stream()
						.map(this::mapToAlktObject)
						.toList())
				.build();
		return build;
	}

	public Owner.AlktObject mapToAlktObject(ObjectEntity objectEntity) {
		return Owner.AlktObject.builder()
				.withObjectId(objectEntity.getObjectId())
				.withName(objectEntity.getServeringsNamn())
				.withChangedDate(objectEntity.getAndradDatum())
				.withPostedDate(objectEntity.getUpplagdDatum())
				.withErrandsList(objectEntity.getErrands().stream()
						.map(this::mapToErrand)
						.toList())
				.build();
	}

	public Owner.AlktObject.Errand mapToErrand(ErrandEntity errandEntity) {
		return Owner.AlktObject.Errand.builder()
				.withErrandType(errandEntity.getArendeTyp())
				.withCaseManagerId(errandEntity.getHandlaggarID())
				.withDiarieNumber(errandEntity.getDiarieNr())
				.withChanged(errandEntity.getAndradDatum())
				.withClosed(errandEntity.getAvslutsDatum())
				.withOpened(errandEntity.getOppnandeDatum())
				.withPosted(errandEntity.getUpplagdDatum())
				.withEvents(errandEntity.getEvents().stream()
						.map(this::mapToEvent)
						.toList())
				.build();
	}

	public Owner.AlktObject.Errand.Event mapToEvent(ErrandEventEntity errandEntity) {
		return Owner.AlktObject.Errand.Event.builder()
				.withChanged(errandEntity.getAndradDatum())
				.withPosted(errandEntity.getUpplagdDatum())
				.withEventDate(errandEntity.getHandelseDatumTid())
				.withDiarieNumber(errandEntity.getDiarieNr())
				.withEventType(errandEntity.getHandelseTyp())
				.build();
	}
}
