package Deserializers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

public class TxtStringDeserializer {

    public String deserialize(String file) throws IOException {
        String expected = Files.readString(Path.of(file)).toUpperCase();

        int length = expected.length();
        int trimFactor = length%(23*3);
        length -=trimFactor;
        expected = expected.substring(0, length);
        return expected;
    }
}