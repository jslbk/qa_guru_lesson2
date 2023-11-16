package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class RandomUtils {
    static Faker faker = new Faker();

    public static String firstName = getRandomName(),
            lastName = getRandomSurname(),
            userEmail = getRandomEmail(),
            gender = getRandomGenderFromArray(),
            userPhone = getRandomPhone(),
            month = getRandomMonth(),
            year = String.valueOf(getRandomYear()),
            subject = getRandomSubjectFromArray(),
            hobby = getRandomHobbyFromArray(),
            address = getRandomAddress(),
            state = getRandomStateFromArray(),
            city = getRandomCityOption(state),
            today = getTodayDate();
    public static int day = getRandomDay();

    private static String getTodayDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

        return date.format(formatter);
    }

    public static int getRandomIntBetween(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static String getRandomGenderFromArray() {
        String[] genders = {"Male", "Female", "Other"};

        return gerRandomItem(genders);
    }

    public static int getRandomYear() {
        return getRandomIntBetween(1990, 2015);
    }

    public static int getRandomDay() {
        return getRandomIntBetween(1, 9);
    }

    public static String getRandomMonth() {
        String[] months = {"January", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        return gerRandomItem(months);
    }

    public static String getRandomStateFromArray() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return gerRandomItem(states);
    }

    public static String getRandomCityOption(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> state;
        };
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

        return gerRandomItem(hobbies);
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
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String gerRandomItem(String[] array) {
        int i = getRandomIntBetween(0, array.length - 1);

        return array[i];
    }
}
