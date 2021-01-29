package web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.concurrent.Semaphore;

public class URLCrawler {
    private static final int maxRequestPerSec = 1;
    private static final Semaphore mutex = new Semaphore(1);

    private static int exceptionCounter = 0;
    private static long lastRequestTimeMillis = 0;

    public static Element crawlBody (String url) throws Exception {
        try {
            mutex.acquire();
            if (maxRequestPerSec > 0) {
                long timeSinceLastRequest = System.currentTimeMillis() - lastRequestTimeMillis;
                if (timeSinceLastRequest < 1000 / maxRequestPerSec) Thread.sleep(1000 / maxRequestPerSec - timeSinceLastRequest);
            }
            lastRequestTimeMillis = System.currentTimeMillis();
            mutex.release();

            Document doc = Jsoup.connect(url).get();
            exceptionCounter = Math.max(0, exceptionCounter - 1);
            return doc.body();
        } catch (Exception e) {
            System.out.print(".");
            Thread.sleep(10000L * ++exceptionCounter);
            return crawlBody(url);
        }
    }
}
