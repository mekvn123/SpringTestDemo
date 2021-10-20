package com.example.myspringtestdemo.unit;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonEquals {
    public static boolean equalToJsonFile(String filename, String actualValue) {
        String expectValue = readJsonFromClasspath(filename).replaceAll("\\s*", "");
        return expectValue.equals(actualValue);
    }

    private static String readJsonFromClasspath(String fileName) {
        try {
            String path = Resources.getResource(fileName).getPath();
            return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
