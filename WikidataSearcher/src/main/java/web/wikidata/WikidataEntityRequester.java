package web.wikidata;

import web.SPARQLInquirer;

import java.util.ArrayList;
import java.util.HashMap;

public class WikidataEntityRequester {
    private final static String endpointUrl = "https://query.wikidata.org/sparql";

    /**
     * SPARQL-Anfrage zum Bestimmen des Labels in einer gewuenschten Sprache
     * @param entityID Wikidata-Entity-ID
     * @param language Sprache (e.g. "en", "fr",...)
     * @return SPARQL-Request als String
     */
    private String queryStringLabel(String entityID, String language) {
        return "SELECT ?label \n" +
                "WHERE \n" +
                "{\n" +
                "  wd:" + entityID + " rdfs:label ?label.\n" +
                "  filter(lang(?label)=\"" + language  + "\")\n" +
                "}";
    }

    /**
     * Bestimmt das englische Labels zu einer Wikidata-ID
     * @param entityID Wikidata-ID
     * @return englische Label
     * @throws Exception
     */
    public String queryLabel(String entityID) throws Exception {
        return queryFirst(queryStringLabel(entityID, "en"));
    }

    /**
     * Bestimmt das franzoesische Labels zu einer Wikidata-ID
     * @param entityID Wikidata-ID
     * @return franzoesische Label
     * @throws Exception
     */
    public String queryFrenchLabel(String entityID) throws Exception {
        return queryFirst(queryStringLabel(entityID, "fr"));
    }

    private String queryFirst(String queryString) throws Exception {
        SPARQLInquirer inquirer = new SPARQLInquirer();
        HashMap results = inquirer.request(endpointUrl, queryString);
        return extractFirst(results);
    }

    private static String extractFirst(HashMap<String, HashMap> results) {
        for (HashMap value : (ArrayList<HashMap>) results.get("result").get("rows")) {
            for (String variable : (ArrayList<String>) results.get("result").get("variables")) {
                return (String) value.get(variable);
            }
        }
        return null;
    }

    /*
    public ArrayList<String> queryAll(String queryString) throws Exception {
        SPARQLInquirer inquirer = new SPARQLInquirer();
        HashMap results = inquirer.request(endpointUrl, queryString);
        return extractAll(results);
    }

    private static ArrayList<String> extractAll(HashMap<String, HashMap> results) {
        ArrayList<String> resultList = new ArrayList<>();
        for (HashMap value : (ArrayList<HashMap>) results.get("result").get("rows")) {
            for (String variable : (ArrayList<String>) results.get("result").get("variables")) {
                resultList.add((String) value.get(variable));
            }
        }
        return resultList;
    }*/
}
