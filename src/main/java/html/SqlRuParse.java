package html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import post.Post;
import utils.DateTimeParser;
import utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws Exception {
//        for (int i = 1; i < 6; i++) {
//            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
//            Elements row = doc.select(".postslisttopic");
//            for (Element td : row) {
//                Element parent = td.parent();
//                Element href = td.child(0);
//                System.out.println(href.attr("href"));
//                System.out.println(href.text());
//                System.out.println(parent.child(5).text());
//            }
//        }
        SqlRuParse parse = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> lst = parse.list("https://www.sql.ru/forum/job-offers/");
        System.out.println(lst);
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            try {
                Document doc = Jsoup.connect(link + i).get();

                Elements row = doc.select(".postslisttopic");
                for (Element td : row) {
                    Element parent = td.parent();
                    Element href = td.child(0);
                    if (href.text().toLowerCase().contains("java")) {
                        list.add(detail(href.attr("href")));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Post detail(String link) {
        Post post = null;
        try {
            Document doc = Jsoup.connect(link).get();

            Elements msgBody = doc.select(".msgBody");
            String msgFooter = msgBody.get(0).parent().parent().child(2).text();
            String strDate = msgFooter.substring(0, msgFooter.indexOf(" ["));
            post = new Post(
                    msgBody.get(0).parent().parent().child(0).text(),
                    msgBody.get(0).child(0).attr("href"),
                    msgBody.get(1).text(),
                    dateTimeParser.parse(strDate));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }
}