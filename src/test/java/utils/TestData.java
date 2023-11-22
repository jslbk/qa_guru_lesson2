package utils;

import com.github.javafaker.Faker;

import static utils.RandomUtils.*;

public class TestData {

    public String name = getRandomName(),
            surname = getRandomSurname(),
            email = getRandomEmail(),
            address = getRandomAddress(),
            phone = getRandomPhone(10),
            gender = getRandomGenderFromArray(),
            month = getRandomMonth(),
            day = getRandomDay(1, 28),
            year = getRandomYear(1990, 2015),
            hobby = getRandomHobbyFromArray(),
            subject = getRandomSubjectFromArray(),
            state = getRandomStateFromArray(),
            city = setRandomCityDependingOnState(state),
            image = "picture.jpg",
            today = getTodayDate();

}

