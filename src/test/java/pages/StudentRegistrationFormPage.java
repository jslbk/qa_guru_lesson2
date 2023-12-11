package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.UploadFileComponent;

import java.lang.module.Configuration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class StudentRegistrationFormPage {
    private final SelenideElement

            firstNameLocator = $("#firstName"),
            surnameLocator = $("#lastName"),
            emailLocator = $("#userEmail"),
            phoneNumberLocator = $("#userNumber"),
            addressLocator = $("#currentAddress"),
            genderLocator = $("#genterWrapper"),
            submitButtonLocator = $("#submit"),
            resultTableLocator = $(".modal-content"),
            dateInputLocator = $("#dateOfBirthInput"),
            subjectLocator = $("#subjectsContainer"),
            subjectInputLocator = $(".subjects-auto-complete__input input"),
            stateInputLocator = $("#state input"),
            cityInputLocator = $("#city input");

    CalendarComponent calendarComponent = new CalendarComponent();
    UploadFileComponent uploadFileComponent = new UploadFileComponent();

    public StudentRegistrationFormPage setFirstName(String name) {
        firstNameLocator.setValue(name);

        return this;
    }

    public StudentRegistrationFormPage setLastName(String surname) {
        surnameLocator.setValue(surname);

        return this;
    }

    public StudentRegistrationFormPage setEmail(String email) {
        emailLocator.setValue(email);

        return this;
    }

    public StudentRegistrationFormPage setSubjects(String subjectValue) {
        subjectLocator.click();
        subjectInputLocator.setValue(subjectValue).pressEnter();

        return this;
    }

    public StudentRegistrationFormPage setState(String state) {
        stateInputLocator.setValue(state).pressEnter();

        return this;
    }

    public  StudentRegistrationFormPage setCity(String city) {
        cityInputLocator.setValue(city).pressEnter();

        return this;
    }

    public StudentRegistrationFormPage setHobbies(String typeOfHobby) {
        $x("//label[text()='" + typeOfHobby + "']").click();

        return this;
    }

    public StudentRegistrationFormPage setDateOfBirth(String year, String month, String day) {
        // Click the date input field to open the date picker
        dateInputLocator.click();
        calendarComponent.setDate(year, month, day);

        return this;
    }

    public StudentRegistrationFormPage setGender(String gender) {
        genderLocator.$(byText(gender)).click();

        return this;
    }
    public void openRegistrationFormPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    public StudentRegistrationFormPage setPhoneNumber(String number) {
        phoneNumberLocator.setValue(number);

        return this;
    }

    public StudentRegistrationFormPage setAddress(String address) {
        addressLocator.setValue(address);

        return this;
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }

    public void checkResultTableIsDisplayed() {
        resultTableLocator.shouldBe(visible);
    }

    public void checkResultTableIsNotDisplayed() {
        resultTableLocator.shouldNotBe(visible);
    }

    public StudentRegistrationFormPage uploadFile(String image) {
        uploadFileComponent.uploadPicture(image);

        return this;
    }

    public int getRequiredFieldsCount() {
        return $$("[required]").size();
    }

}
