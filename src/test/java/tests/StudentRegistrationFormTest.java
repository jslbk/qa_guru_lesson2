package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.RandomUtils.*;

public class StudentRegistrationFormTest extends TestBase {
    StudentRegistrationFormPage registrationPage = new StudentRegistrationFormPage();
    private final String STUDENT_NAME = getRandomName(),
            STUDENT_SURNAME = getRandomSurname(),
            STUDENT_EMAIL = getRandomEmail(),
            STUDENT_GENDER = getRandomGenderFromArray(),
            STUDENT_PHONE = getRandomPhone(),
            STUDENT_SUBJECT = getRandomSubjectFromArray(),
            STUDENT_HOBBY = getRandomHobbyFromArray(),
            IMAGE_NAME = "picture.jpg",
            STUDENT_ADDRESS = getRandomAddress(),
            STUDENT_STATE = getRandomStateFromArray(),
            STUDENT_CITY = getRandomCityFromArray();

    @BeforeEach
    void openRegistrationFormAndRemoveBanners() {
        // Open form by URL and remove banners that block form elements
        registrationPage.openRegistrationFormPage();
    }

    @Test
    void submitStudentRegistrationFormTest() {
        // Fill all the fields of the student registration form and check result table automatically displayed
        registrationPage.setFirstName(STUDENT_NAME)
                .setLastName(STUDENT_SURNAME)
                .setEmail(STUDENT_EMAIL)
                .setGender(STUDENT_GENDER)
                .setPhoneNumber(STUDENT_PHONE)
                .setDateOfBirth("1994", "October", "20")
                .setSubjects(STUDENT_SUBJECT)
                .setHobbies(STUDENT_HOBBY)
                .uploadFile(IMAGE_NAME)
                .setAddress(STUDENT_ADDRESS)
                .selectStateFromDropdown(STUDENT_STATE, STUDENT_CITY);

        registrationPage.checkResultTableIsDisplayed();
    }

    @Test
    void submitStudentRegistrationFormWithOnlyRequiredDataTest() {
        // Fill all the required fields of the student registration form and check form can be submitted
        registrationPage
                .setFirstName(STUDENT_NAME)
                .setLastName(STUDENT_SURNAME)
                .setEmail(STUDENT_EMAIL)
                .setGender(STUDENT_GENDER)
                .setPhoneNumber(STUDENT_PHONE)
                .clickSubmitButton();

        registrationPage.checkResultTableIsDisplayed();
    }

    @Test
    void submitStudentRegistrationFormWithNegativeTest() {
        registrationPage.clickSubmitButton();
        int actualRequiredFields = registrationPage.getRequiredFieldsCount();
        assertEquals(6, actualRequiredFields, "Number of required fields doesn't match actual count");

        registrationPage.checkResultTableIsNotDisplayed();
    }

}