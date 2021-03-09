package web.wikidata;

import web.SPARQLInquirer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WikidataEntityRequester {
    private final static String endpointUrl = "https://query.wikidata.org/sparql";

    private String queryIDsWithLabel(String label, String language) {
        return "SELECT ?id \n" +
                "WHERE \n" +
                "{\n" +
                "  ?id rdfs:label \"" + label + "\"@" + language + ".\n" +
                "}";
    }

    private String queryIDsWithAlias(String label, String language) {
        return "SELECT ?id \n" +
                "WHERE \n" +
                "{\n" +
                "  ?id skos:altLabel \"" + label + "\"@" + language + ".\n" +
                "}";
    }

    public HashSet<String> queryIDsWithLabelFrench(String label) throws Exception {
        return queryAll(queryIDsWithLabel(label, "fr"));
    }

    public HashSet<String> queryIDsWithLabelEnglish(String label) throws Exception {
        return queryAll(queryIDsWithLabel(label, "en"));
    }

    public HashSet<String> queryIDsWithAliasFrench(String label) throws Exception {
        return queryAll(queryIDsWithAlias(label, "fr"));
    }

    public HashSet<String> queryIDsWithAliasEnglish(String label) throws Exception {
        return queryAll(queryIDsWithAlias(label, "en"));
    }

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
     * Bestimmt das englische Label zu einer Wikidata-ID
     * @param entityID Wikidata-ID
     * @return englische Label
     * @throws Exception
     */
    public String queryLabel(String entityID) throws Exception {
        return queryFirst(queryStringLabel(entityID, "en"));
    }

    /**
     * Bestimmt das franzoesische Label zu einer Wikidata-ID
     * @param entityID Wikidata-ID
     * @return franzoesische Label
     * @throws Exception
     */
    public String queryFrenchLabel(String entityID) throws Exception {
        return queryFirst(queryStringLabel(entityID, "fr"));
    }

    /**
     * Bestimmt das deutsche Label zu einer Wikidata-ID
     * @param entityID Wikidata-ID
     * @return deutsches Label
     * @throws Exception
     */
    public String queryGermanLabel(String entityID) throws Exception {
        return queryFirst(queryStringLabel(entityID, "de"));
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

    public HashSet<String> queryAll(String queryString) throws Exception {
        SPARQLInquirer inquirer = new SPARQLInquirer();
        HashMap results = inquirer.request(endpointUrl, queryString);
        return extractAll(results);
    }

    private static HashSet<String> extractAll(HashMap<String, HashMap> results) {
        HashSet<String> set = new HashSet<>();
        for (HashMap value : (ArrayList<HashMap>) results.get("result").get("rows")) {
            for (String variable : (ArrayList<String>) results.get("result").get("variables")) {
                set.add((String) value.get(variable));
            }
        }
        return set;
    }
}
