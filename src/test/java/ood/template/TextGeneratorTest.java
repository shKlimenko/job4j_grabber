package ood.template;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class TextGeneratorTest {

    @Disabled
    @Test
    public void whenProduceThenOk() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Alexey");
        args.put("subject", "you");
        String expected = "I am a Alexey, Who are you? ";
        assertEquals(new TextGenerator().produce(template, args), expected);
    }

    @Disabled
    @Test
    public void whenThereAreNoKeysInTheMapThenException() {
        String template = "I am a ${surname}, Who are ${class}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Alexey");
        args.put("subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            new TextGenerator().produce(template, args);
        });
    }

    @Disabled
    @Test
    public void whenExtraKeysInTheMapThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Alexey");
        args.put("subject", "you");
        args.put("phone", "analog");
        args.put("book", "paper");
        assertThrows(IllegalArgumentException.class, () -> {
            new TextGenerator().produce(template, args);
        });
    }
}