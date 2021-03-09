package web.wikidata;

public class WikidataEntity {
    private final String url;

    public WikidataEntity(String url) {
        this.url = url;
    }

    public String getId() {
        return url.replace("http://www.wikidata.org/entity/", "");
    }

    public String getUrl() {
        return url;
    }
}
