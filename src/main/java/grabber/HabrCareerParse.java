package grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private final DateTimeParser dateTimeParser;

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);


    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) {
        HabrCareerParse hcp = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> lst = hcp.list("https://career.habr.com/vacancies/java_developer");
        lst.forEach(System.out::println);
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        HabrPageParse hpp = new HabrPageParse();
        for (int i = 1; i < 6; i++) {
            try {
                Document doc = Jsoup.connect(link + "?page=" + i).get();
                Elements rows = doc.select(".vacancy-card__inner");

                rows.forEach(row -> {
                    Element titleElement = row.select(".vacancy-card__title").first();
                    Element basicDate = row.select(".basic-date").first();
                    Element linkElement = titleElement.child(0);
                    String vacancyName = titleElement.text();
                    String dateTimeAttr = basicDate.attr("datetime");
                    String sLink = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                    try {
                        list.add(new Post(vacancyName, sLink, hpp.retrieveDescription(sLink), this.dateTimeParser.parse(dateTimeAttr)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}