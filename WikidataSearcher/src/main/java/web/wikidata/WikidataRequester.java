package web.wikidata;

import web.SPARQLInquirer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WikidataRequester {
    private final static String endpointUrl = "https://query.wikidata.org/sparql";

    public HashSet<String> queryIDsWithLabelFrench(String label) throws Exception {
        return queryAll(SPARQLQueries.queryIDsWithLabel(label, "fr"));
    }

    public HashSet<String> queryIDsWithLabelEnglish(String label) throws Exception {
        return queryAll(SPARQLQueries.queryIDsWithLabel(label, "en"));
    }

    public HashSet<String> queryIDsWithAliasFrench(String alias) throws Exception {
        return queryAll(SPARQLQueries.queryIDsWithAlias(alias, "fr"));
    }

    public HashSet<String> queryIDsWithAliasEnglish(String alias) throws Exception {
        return queryAll(SPARQLQueries.queryIDsWithAlias(alias, "en"));
    }

    public String queryLabel(String id) throws Exception {
        return queryFirst(SPARQLQueries.queryStringLabel(id, "en"));
    }

    public String queryFrenchLabel(String id) throws Exception {
        return queryFirst(SPARQLQueries.queryStringLabel(id, "fr"));
    }

    public String queryGermanLabel(String id) throws Exception {
        return queryFirst(SPARQLQueries.queryStringLabel(id, "de"));
    }

    // Fragt das 1. Ergebnis zu einer SPARQL-Query ab
    private String queryFirst(String queryString) throws Exception {
        SPARQLInquirer inquirer = new SPARQLInquirer();
        HashMap results = inquirer.request(endpointUrl, queryString);
        return extractFirst(results);
    }

    // Extrahiert das 1. Ergebnis der Ergebnisliste
    private static String extractFirst(HashMap<String, HashMap> results) {
        for (HashMap value : (ArrayList<HashMap>) results.get("result").get("rows")) {
            for (String variable : (ArrayList<String>) results.get("result").get("variables")) {
                return (String) value.get(variable);
            }
        }
        return null;
    }

    // Fragt alle Ergebnisse zu einer SPARQL-Query ab
    public HashSet<String> queryAll(String queryString) throws Exception {
        SPARQLInquirer inquirer = new SPARQLInquirer();
        HashMap results = inquirer.request(endpointUrl, queryString);
        return extractAll(results);
    }

    // Extrahiert alle Ergebnisse der Ergebnisliste
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
