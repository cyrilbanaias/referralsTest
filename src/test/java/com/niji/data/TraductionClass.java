package com.niji.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TraductionClass {

    public HashMap<String, HashMap<String, String>> leadStatus = new HashMap<>();

    public void initialize() {
        leadStatus.put("Closing The deal", new HashMap<>());
        leadStatus.get("Closing The deal").put("FR", "Contrat en attente signature client");
        leadStatus.get("Closing The deal").put("NL", "Contract in afwachting van ondertekening klant");

        leadStatus.put("Need created", new HashMap<>());
        leadStatus.get("Need created").put("FR", "Demande créée");
        leadStatus.get("Need created").put("NL", "Aanvraag gecreëerd");

        leadStatus.put("Client contact in progress", new HashMap<>());
        leadStatus.get("Client contact in progress").put("FR", "Contact client en cours");
        leadStatus.get("Client contact in progress").put("NL", "Lopende klantencontact");

        leadStatus.put("Out of scope", new HashMap<>());
        leadStatus.get("Out of scope").put("FR", "Hors Cible");
        leadStatus.get("Out of scope").put("NL", "Buiten doelgroep");

        leadStatus.put("In negotiation", new HashMap<>());
        leadStatus.get("In negotiation").put("FR", "Négociation");
        leadStatus.get("In negotiation").put("NL", "In onderhandeling");

        leadStatus.put("Waiting for customer decision", new HashMap<>());
        leadStatus.get("Waiting for customer decision").put("FR", "Attente décision client");
        leadStatus.get("Waiting for customer decision").put("NL", "In afwachting beslissing klant");

        leadStatus.put("Waiting for risk sharing", new HashMap<>());
        leadStatus.get("Waiting for risk sharing").put("FR", "Attente analyse de solvabilité");
        leadStatus.get("Waiting for risk sharing").put("NL", "In afwachting van risicoanalyse");

        leadStatus.put("Lost", new HashMap<>());
        leadStatus.get("Lost").put("FR", "Sans suite client");
        leadStatus.get("Lost").put("NL", "Zonder gevolg door de klant");

        leadStatus.put("Won", new HashMap<>());
        leadStatus.get("Won").put("FR", "Contrat signé");
        leadStatus.get("Won").put("NL", "Contract ondertekend");

        leadStatus.put("Not engaged", new HashMap<>());
        leadStatus.get("Not engaged").put("FR", "Manque d'intérêt");
        leadStatus.get("Not engaged").put("NL", "Geen interesse");
    }
}

