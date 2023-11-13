package utils;

import com.github.javafaker.Faker;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    static Faker faker = new Faker();
    public static String getRandomString() {
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder string = new StringBuilder();
        Random random = new Random();
        while (string.length() == 10) { // length of the random string.
            int index = (int) (random.nextFloat() * CHARS.length());
            string.append(CHARS.charAt(index));
        }
        String randomString = string.toString();
        return randomString;
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomGenderFromArray() {
        String[] genders = {"Male", "Female", "Other"};

        return gerRandomItemFromArray(genders);
    }

    public static String getRandomStateFromArray() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return gerRandomItemFromArray(states);
    }

    public static String getRandomCityFromArray() {
        String[] cities = {"Delhi", "Gurgaon", "Noida"};

        return gerRandomItemFromArray(cities);
    }

    public static String getRandomSubjectFromArray() {
        String[] subjects = {"Maths", "English", "Physics", "Chemistry", "Computer Science", "Commerce",
                "Economics", "Arts", "Social Studies", "History", "Civics", "Accounting", "Biology", "Hindi"};

        return gerRandomItemFromArray(subjects);
    }

    public static String getRandomHobbyFromArray() {
        String[] hobbies = {"Sports", "Reading", "Music"};

        return gerRandomItemFromArray(hobbies);
    }

    public static String getRandomName() {
        return faker.name().firstName();
    }

    public static String getRandomSurname() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPhone() {
        return faker.phoneNumber().toString();
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String gerRandomItemFromArray(String[] array) {
        int i = getRandomInt(0, array.length - 1);

        return array[i];
    }
}
