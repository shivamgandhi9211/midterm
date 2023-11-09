package com.georgiancollege.test1;

import java.nio.file.Files;
import java.nio.file.Path;

public class DBCreds {
    public static String findUser() {
        try {
            Path filePath = Path.of("src/main/java/com/georgiancollege/test1/user.txt");
            return Files.readString(filePath);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String findPass() {
        try {
            Path filePath = Path.of("src/main/java/com/georgiancollege/test1/pass.txt");
            return Files.readString(filePath);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
