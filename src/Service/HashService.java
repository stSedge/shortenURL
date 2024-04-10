package Service;

import java.util.Base64;

public class HashService {

    public String toHash(String longUrl) {
        String shortURL = Base64.getEncoder().encodeToString(longUrl.getBytes());
        return shortURL;
    }
}
