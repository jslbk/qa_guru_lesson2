package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;
import pages.components.ResultTableComponent;
import utils.TestData;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRegistrationFormTest extends TestBase {
    StudentRegistrationFormPage registrationPage = new StudentRegistrationFormPage();
    ResultTableComponent resultTableComponent = new ResultTableComponent();
    TestData testData = new TestData();

    @BeforeEach
    void openRegistrationFormAndRemoveBanners() {
        // Open form by URL and remove banners that block form elements
        registrationPage.openRegistrationFormPage();
    }

    @Test
    void submitStudentRegistrationFormTest() {
        registrationPage.setFirstName(testData.name)
                .setLastName(testData.surname)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setPhoneNumber(testData.phone)
                .setDateOfBirth(testData.year, testData.month, testData.day)
                .setSubjects(testData.subject)
                .setHobbies(testData.hobby)
                .uploadFile(testData.image)
                .setAddress(testData.address)
                .setState(testData.state)
                .setCity(testData.city)
                .clickSubmitButton();
        registrationPage.checkResultTableIsDisplayed();

        resultTableComponent.checkResult("Student Name", testData.name + "\n" + testData.surname)
                .checkResult("Student Email", testData.email)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phone)
                .checkResult("Date of Birth", format("%02d", Integer.parseInt(testData.day))
                        + "\n" + testData.month + "," + testData.year)
                .checkResult("Subjects", testData.subject)
                .checkResult("Hobbies", testData.hobby)
                .checkResult("Picture", testData.image)
                .checkResult("Address", testData.address)
                .checkResult("State and City",testData.state + "\n" + testData.city);
    }

    @Test
    void submitStudentRegistrationFormWithOnlyRequiredDataTest() {
        registrationPage
                .setFirstName(testData.name)
                .setLastName(testData.surname)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setPhoneNumber(testData.phone)
                .clickSubmitButton();
        registrationPage.checkResultTableIsDisplayed();

        resultTableComponent.checkResult("Student Name", testData.name + "\n" + testData.surname)
                .checkResult("Student Email", testData.email)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phone)
                .checkResult("Date of Birth", testData.today)
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