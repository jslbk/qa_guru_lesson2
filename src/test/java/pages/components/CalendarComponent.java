package pages.components;

import pages.StudentRegistrationFormPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CalendarComponent {
    public void setDate(String dateOfBirth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            // Parse the {dateOfBirth} string into a Date object
            Date date = dateFormat.parse(dateOfBirth);

            // Extract day, month, and year components
            int day = date.getDate();
            int month = date.getMonth();
            int year = date.getYear() + 1900;

            // Selectors for date input fields
            String monthSelector = ".react-datepicker__month-select";
            String yearSelector = ".react-datepicker__year-select";

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

}
