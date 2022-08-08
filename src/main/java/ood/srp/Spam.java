package ood.srp;

import java.io.IOException;
import java.util.List;

public interface Spam {
    String generateMessage() throws IOException;
    List<Phone> generateWorkingPhoneNumbers(List<Phone> numbers);
    void sendMessage(List<Phone> phones, String message);
}
