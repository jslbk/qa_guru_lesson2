package utils;

import com.github.javafaker.Faker;

import static utils.RandomUtils.*;

public class TestData {
    Faker faker = new Faker();
    public String name = getRandomName();
    public String surname = getRandomSurname();
    public String email = getRandomEmail();
    public String address = getRandomAddress();
    public String phone = getRandomPhone(10);
    public String gender = getRandomGenderFromArray();
    public String month = getRandomMonth();
    public String day = getRandomDay(1, 28);
    public String year = getRandomYear(1990, 2015);
    public String hobby = getRandomHobbyFromArray();
    public String subject = getRandomSubjectFromArray();
    public String state = getRandomStateFromArray();
    public String city = getRandomCityOption(state);
    public String image = "picture.jpg";
    public String today = getTodayDate();
}

