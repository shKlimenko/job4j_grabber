package ood.srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SendAnnoyingMessages implements Spam {
    public static final String PATH_PHRASES = "src/main/java/ood/srp/phrases.txt";

    @Override
    public String generateMessage() {
        Random random = new Random();
        List<String> text = new ArrayList<>();
        try {
            Files.lines(Paths.get(PATH_PHRASES))
                    .forEach(text::add);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return text.get(random.nextInt(text.size()));
    }

    @Override
    public List<Phone> generateWorkingPhoneNumbers(List<Phone> phones) {
        return phones.stream()
                      .filter(Phone::isActive)
                      .toList();
    }

    @Override
    public void sendMessage(List<Phone> phones, String message) {
        phones.forEach(p -> p.sendMessage(message));
    }
}
