package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;
import pages.components.ResultTableComponent;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.RandomUtils.*;

public class StudentRegistrationFormTest extends TestBase {
    StudentRegistrationFormPage registrationPage = new StudentRegistrationFormPage();
    ResultTableComponent resultTableComponent = new ResultTableComponent();
    public static final String IMAGE_NAME = "picture.jpg";

    @BeforeEach
    void openRegistrationFormAndRemoveBanners() {
        // Open form by URL and remove banners that block form elements
        registrationPage.openRegistrationFormPage();
    }

    @Test
    void submitStudentRegistrationFormTest() {
        // Fill all the fields of the student registration form and check result table automatically displayed
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setPhoneNumber(userPhone)
                .setDateOfBirth(year, month, String.valueOf(day))
                .setSubjects(subject)
                .setHobbies(hobby)
                .uploadFile(IMAGE_NAME)
                .setAddress(address)
                .selectStateAndCityFromDropdown(state, city);
        registrationPage.checkResultTableIsDisplayed();
        resultTableComponent.checkResult("Student Name", firstName + "\n" + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userPhone)
                .checkResult("Date of Birth", format("%02d", day) + "\n" + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", IMAGE_NAME)
                .checkResult("Address", address)
                .checkResult("State and City", "");
    }

    @Test
    void submitStudentRegistrationFormWithOnlyRequiredDataTest() {
        // Fill all the required fields of the student registration form and check form can be submitted
        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setPhoneNumber(userPhone)
                .clickSubmitButton();
        registrationPage.checkResultTableIsDisplayed();
        resultTableComponent.checkResult("Student Name", firstName + "\n" + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userPhone)
                .checkResult("Date of Birth", today)
                .checkResult("Subjects", "")
                .checkResult("Hobbies", "")
                .checkResult("Picture", "")
                .checkResult("Address", "")
                .checkResult("State and City", "");
    }

    @Test
    void submitStudentRegistrationFormWithNegativeTest() {
        registrationPage.clickSubmitButton();
        int actualRequiredFields = registrationPage.getRequiredFieldsCount();
        assertEquals(6, actualRequiredFields, "Number of required fields doesn't match actual count");

        registrationPage.checkResultTableIsNotDisplayed();
    }

}