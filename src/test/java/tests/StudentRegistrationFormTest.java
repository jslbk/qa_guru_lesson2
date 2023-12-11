package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;
import pages.components.ResultTableComponent;
import utils.TestData;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("demotag")
public class StudentRegistrationFormTest extends TestBase {
    StudentRegistrationFormPage registrationPage = new StudentRegistrationFormPage();
    ResultTableComponent resultTableComponent = new ResultTableComponent();
    TestData testData = new TestData();

    @BeforeEach
    void openRegistrationFormAndRemoveBanners() {
        step("Open student registration form", () -> {
            registrationPage.openRegistrationFormPage();
        });
    }

    @Test
    void submitStudentRegistrationFormTest() {
        step("Fill student registration form", () -> {
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
        });
        step("Check result table displayed", () -> {
            registrationPage.checkResultTableIsDisplayed();
        });
        step("Check result table data", () -> {
            resultTableComponent.checkResult("Student Name", testData.name + "\n" + testData.surname)
                    .checkResult("Student Email", testData.email)
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.phone)
                    .checkResult("Date of Birth", testData.birthday)
                    .checkResult("Subjects", testData.subject)
                    .checkResult("Hobbies", testData.hobby)
                    .checkResult("Picture", testData.image)
                    .checkResult("Address", testData.address)
                    .checkResult("State and City",testData.state + "\n" + testData.city);
        });
    }

    @Test
    void submitStudentRegistrationFormWithOnlyRequiredDataTest() {
        step("Fill student registration form", () -> {
            registrationPage
                    .setFirstName(testData.name)
                    .setLastName(testData.surname)
                    .setEmail(testData.email)
                    .setGender(testData.gender)
                    .setPhoneNumber(testData.phone)
                    .clickSubmitButton();
        });
        step("Check result table displayed", () -> {
            registrationPage.checkResultTableIsDisplayed();
        });
        step("Check result table data", () -> {
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
        });
    }

    @Test
    void submitStudentRegistrationFormWithNegativeTest() {
        step("Submit student registration form", () -> {
            registrationPage.clickSubmitButton();
        });
        step("Check required field number", () -> {
            int actualRequiredFields = registrationPage.getRequiredFieldsCount();
            assertEquals(6, actualRequiredFields, "Number of required fields doesn't match actual count");

            registrationPage.checkResultTableIsNotDisplayed();
        });
    }

}