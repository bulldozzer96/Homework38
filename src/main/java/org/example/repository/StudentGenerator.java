package org.example.repository;

import java.util.Random;

public class StudentGenerator {
static String name;

    public static String studentName() {

        name = "";
        Random random = new Random();
        int letter_Selector = random.nextInt(4, 8);
        String studentName = "abcdefghijklmnopqrstuvxyz";

        for (int i = 0; i <= letter_Selector; i++) {
            int randomS = (studentName.length());
            name = name + (studentName.charAt(random.nextInt(randomS)));
        }
        return Character.toUpperCase(name.charAt(random.nextInt(0, name.length()))) + name;
    }
}

