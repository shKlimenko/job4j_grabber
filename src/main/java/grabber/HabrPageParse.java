package grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HabrPageParse {
    public String retrieveDescription(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        Elements msgBody = doc.select(".job_show_description__vacancy_description");
        return msgBody.text();
    }
}
