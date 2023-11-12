package tests;

import org.junit.jupiter.api.*;
import pages.StudentRegistrationFormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRegistrationFormTest extends TestBase {

    StudentRegistrationFormPage registrationPage = new StudentRegistrationFormPage();

    private static final String STUDENT_NAME = "Name",
    STUDENT_SURNAME = "Surname",
    STUDENT_EMAIL = "name@name.com",
    STUDENT_GENDER = "Male",
    STUDENT_PHONE = "0123456789",
    STUDENT_SUBJECT = "Math",
    STUDENT_HOBBY = "Reading",
    IMAGE_NAME = "picture.jpg",
    STUDENT_ADDRESS = "Address, 1/22-333",
    STUDENT_STATE = "Uttar Pradesh",
    STUDENT_CITY = "Merrut";


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
                .setDateOfBirth()
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