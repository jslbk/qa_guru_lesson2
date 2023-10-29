import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void openRegistrationFormAndRemoveBanners() {
        // Open form by URL and remove banners that block form elements
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    @Test
    void submitStudentRegistrationFormTest() {
        // Fill all the fields of the student registration form
        fillStudentRegistrationForm();

        // Click 'Submit' button and check if modal pop-up is displayed
        $("#submit").click();
        $(".modal-content").shouldBe(visible);
    }

    void fillStudentRegistrationForm() {
        $("#firstName").setValue("userName");
        $("#lastName").setValue("Surname");
        $("#userEmail").setValue("name@name.com");
        setGenderByValue();
        $("#userNumber").setValue("0123456789");
        setDateOfBirth("20.10.1994");
        setSubjectsByValue("Math");
        setHobbiesByValue("Reading");
        uploadFile("picture.jpg");
        $("#currentAddress").setValue("Address, 1/22-333");
        selectStateFromDropdownByValue("Uttar Pradesh", "Merrut");
    }

    void setSubjectsByValue(String subjectValue) {
        $("#subjectsContainer").click();
        $(".subjects-auto-complete__input input").setValue(subjectValue).pressEnter();
    }

    void selectStateFromDropdownByValue(String state, String city) {
        $("#state").click();
        $("#state input").setValue(state).pressEnter();
        $("#city").click();
        $("#city input").setValue(city).pressEnter();
    }

    void uploadFile(String fileName) {
        // Locate the file input element by CSS selector
        String fileInputSelector = "#uploadPicture";

        // Upload the file by setting the input element's value to the {fileName} path
        SelenideElement fileInput = Selenide.$(fileInputSelector);
        fileInput.uploadFile(getFilePath(fileName));
    }

    private File getFilePath(String fileName) {
        // Get the path of the {fileName} in the "resources" directory
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getPath());
    }

    private void setHobbiesByValue(String typeOfHobby) {
        $x("//label[text()='" + typeOfHobby + "']").click();
    }

    void setDateOfBirth(String dateOfBirth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            // Parse the {dateOfBirth} string into a Date object
            Date date = dateFormat.parse(dateOfBirth);

            // Extract day, month, and year components
            int day = date.getDate();
            int month = date.getMonth();
            int year = date.getYear() + 1900;

            // Selectors for date input fields
            String dateInputField = "#dateOfBirthInput";
            String monthSelector = ".react-datepicker__month-select";
            String yearSelector = ".react-datepicker__year-select";

            // Click the date input field to open the date picker
            $(dateInputField).click();

            // Select the month and year from their respective dropdowns
            $(monthSelector).click();
            $(monthSelector).selectOptionByValue(String.valueOf(month));
            $(yearSelector).click();
            $(yearSelector).selectOptionByValue(String.valueOf(year));

            // Select the first date matching the specified text in the date picker
            String daySelector = String.format("(//div[text()='" + day + "'])[1]");
            $x(daySelector).click();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    void setGenderByValue() {
        $("#gender-radio-2").parent().click();
    }

}