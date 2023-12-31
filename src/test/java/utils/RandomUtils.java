package utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.String.format;

public class RandomUtils {
    static Faker faker = new Faker();

    public static String getTodayDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

        return date.format(formatter);
    }

    public static String getFullBirthdayValue(String day, String month, String year) {
        return format("%02d", Integer.parseInt(day)) + "\n" + month + "," + year;
    }

    public static String getRandomIntBetween(int min, int max) {
        return String.valueOf(faker.number().numberBetween(min, max));
    }

    public static String getRandomGenderFromArray() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomOption(genders);
    }

    public static String getRandomYear(int min, int max) {
        return getRandomIntBetween(min, max);
    }

    public static String getRandomDay(int min, int max) {
        return getRandomIntBetween(min, max);
    }

    public static String getRandomMonth() {
        String[] months = {"January", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        return getRandomOption(months);
    }

    public static String getRandomStateFromArray() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return getRandomOption(states);
    }

    public static String setRandomCityDependingOnState(String state) {
        if ("NCR".equals(state)) {
            return faker.options().option("Delhi", "Gurgaon", "Noida");
        } else if ("Uttar Pradesh".equals(state)) {
            return faker.options().option("Agra", "Lucknow", "Merrut");
        } else if ("Haryana".equals(state)) {
            return faker.options().option("Karnal", "Panipat");
        } else if ("Rajasthan".equals(state)) {
            return faker.options().option("Jaipur", "Jaiselmer");
        } else {
            return state;
        }
    }

    public static String getRandomSubjectFromArray() {
        String[] subjects = {"Maths", "English", "Physics", "Chemistry", "Computer Science", "Commerce",
                "Economics", "Arts", "Social Studies", "History", "Civics", "Accounting", "Biology", "Hindi"};

        return getRandomOption(subjects);
    }

    private static String getRandomOption(String[] array) {
        return faker.options().option(array);
    }

    public static String getRandomHobbyFromArray() {
        String[] hobbies = {"Sports", "Reading", "Music"};

        return getRandomOption(hobbies);
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

    public static String getRandomPhone(int length) {
        return faker.phoneNumber().subscriberNumber(length);
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }
}
