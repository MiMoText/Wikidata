package web;

import com.bordercloud.sparql.SparqlClient;
import com.bordercloud.sparql.SparqlClientException;
import com.bordercloud.sparql.SparqlResult;

import java.net.URI;
import java.util.HashMap;

public class SPARQLInquirer {
    private final int maxErrorsAllowed = 10;
    private int errorCount = 0;

    public HashMap<String, Object> request(String endpointUrl, String query) throws Exception {
        try {
            URI endpoint = new URI(endpointUrl);
            SparqlClient sc = new SparqlClient(false);
            sc.setEndpointRead(endpoint);
            SparqlResult sr = sc.query(query);
            return sr.resultHashMap;
        } catch (SparqlClientException e) {
            if (++errorCount > maxErrorsAllowed) return null;
            Thread.sleep(10000);
            return request(endpointUrl, query);
        }
    }
}