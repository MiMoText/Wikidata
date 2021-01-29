package web.wikidata;

import java.util.ArrayList;
import java.util.Arrays;

public class WikidataEntity {
    private final String id;
    private final String url;
    private final String label;
    private final String labelFrench;

    public WikidataEntity(String id, String label, String labelFrench) {
        this.id = id;
        this.url = "https://www.wikidata.org/wiki/" + id;
        this.label = label;
        this.labelFrench = labelFrench;
    }

    ArrayList<String> getInfo() {
        String [] info = {id, url, label, labelFrench};
        return new ArrayList<>(Arrays.asList(info));
    }

    ArrayList<String> getHeader(int n) {
        String [] strings = {"id" + n, "url" + n, "label" + n, "label@fr" + n};
        return new ArrayList<>(Arrays.asList(strings));
    }
}
