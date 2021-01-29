package web.wikidata;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import web.URLCrawler;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WikidataSearcher {
    private final static String searchUrl = "https://www.wikidata.org/w/index.php?search=";

    /**
     * Sucht auf Wikidata und extrahiert eine bestimmte Anzahl an Wikidata-IDs
     * @param queryString String, nach welchem auf Wikidata gesucht wird
     * @param numberOfResults Anzahl der Suchergebnisse aus denen die IDs extrahiert werden, angefangen beim ersten
     * @return Liste von Wikidata-IDs
     * @throws Exception
     */
    public ArrayList<String> getFirstResultsIDs(String queryString, int numberOfResults) throws Exception {
        ArrayList<String> ids = new ArrayList<>();

        String encodedQuery = URLEncoder.encode(queryString, StandardCharsets.UTF_8);
        Element body = URLCrawler.crawlBody(searchUrl + encodedQuery);
        Elements searchResults = body.getElementsByClass("mw-search-result-heading");

        for (int i = 0; i < Math.min(searchResults.size(), numberOfResults); i++) {
            Element result = searchResults.get(i);
            String id = result.getElementsByClass("wb-itemlink-id").get(0).text();
            ids.add(id.substring(1, id.length() - 1));
        }

        return ids;
    }
}
