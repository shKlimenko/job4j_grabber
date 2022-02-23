package grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ForumPageParse {
    public static Post pageParse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements msgBody = doc.select(".msgBody");
        String msgFooter = msgBody.get(0).parent().parent().child(2).text();
        String strDate = msgFooter.substring(0, msgFooter.indexOf(" ["));
        return new Post(
                msgBody.get(0).parent().parent().child(0).text(),
                msgBody.get(0).child(0).attr("href"),
                msgBody.get(1).text(),
                new SqlRuDateTimeParser().parse(strDate)
        );
    }
}
