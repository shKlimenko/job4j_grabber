package grabber;

import java.time.LocalDateTime;
import java.util.Map;

public class HabrCareerDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(parse.substring(0, parse.indexOf("+")));
    }

}