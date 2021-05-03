package web.wikidata;

public class SPARQLQueries {

    /**
     * SPARQL-Anfrage zum Finden von IDs der Entitaeten, die das Label in der Sprache besitzen
     * @param label Wikidata-Label
     * @param language Sprache (e.g. "en", "fr",...)
     * @return SPARQL-Request als String
     */
    static String queryIDsWithLabel(String label, String language) {
        return "SELECT ?id \n" +
                "WHERE \n" +
                "{\n" +
                "  ?id rdfs:label \"" + label + "\"@" + language + ".\n" +
                "}";
    }

    /**
     * SPARQL-Anfrage zum Finden von IDs der Entitaeten, die den Alias in der Sprache besitzen
     * @param alias Wikidata-Alias (also known as)
     * @param language Sprache (e.g. "en", "fr",...)
     * @return SPARQL-Request als String
     */
    static String queryIDsWithAlias(String alias, String language) {
        return "SELECT ?id \n" +
                "WHERE \n" +
                "{\n" +
                "  ?id skos:altLabel \"" + alias + "\"@" + language + ".\n" +
                "}";
    }

    /**
     * SPARQL-Anfrage zum Bestimmen des Labels einer Wikidata-ID in der Sprache
     * @param id Wikidata-Entity-ID
     * @param language Sprache (e.g. "en", "fr",...)
     * @return SPARQL-Request als String
     */
    static String queryStringLabel(String id, String language) {
        return "SELECT ?label \n" +
                "WHERE \n" +
                "{\n" +
                "  wd:" + id + " rdfs:label ?label.\n" +
                "  filter(lang(?label)=\"" + language  + "\")\n" +
                "}";
    }
}
