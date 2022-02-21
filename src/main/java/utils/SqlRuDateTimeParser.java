package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12")
    );

    public static void main(String[] args) {
        SqlRuDateTimeParser srdtp = new SqlRuDateTimeParser();
        System.out.println(srdtp.parse("сегодня, 22:15"));
    }

    @Override
    public LocalDateTime parse(String parse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yy, HH:mm");
        LocalDateTime date;
        if (parse.toLowerCase().contains("сегодня")) {
            date = LocalDateTime.now().withSecond(0).withNano(0);
        } else if (parse.toLowerCase().contains("вчера")) {
            date = LocalDateTime.now().minusDays(1).withSecond(0).withNano(0);
        } else {
            String[] strings = parse.split(" ");
            parse = parse.replace(strings[1], MONTHS.get(strings[1]));
            date = LocalDateTime.parse(parse, formatter);
        }
        return date;
    }
}