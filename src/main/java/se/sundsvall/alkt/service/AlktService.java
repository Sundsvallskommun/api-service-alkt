package se.sundsvall.alkt.service;

import org.springframework.stereotype.Service;

import se.sundsvall.alkt.integration.db.CaseManagerRepository;
import se.sundsvall.alkt.integration.party.PartyClient;

import generated.se.sundsvall.party.PartyType;

@Service
public class AlktService {

	private final PartyClient partyClient;
	private final CaseManagerRepository caseManagerRepository;

	public AlktService(PartyClient partyClient, CaseManagerRepository caseManagerRepository) {
		this.partyClient = partyClient;
		this.caseManagerRepository = caseManagerRepository;
	}

	public String getLegalId(String partyId) {
		var legalId = partyClient.getLegalId(PartyType.PRIVATE, partyId);

		return legalId.orElseThrow(() -> new RuntimeException("Could not find legalId for partyId: " + partyId));
	}

	public void getAllCaseManagers() {
		var distinctHandlaggarID = caseManagerRepository.findDistinctHandlaggarID();
		System.out.println(distinctHandlaggarID);
	}
}
