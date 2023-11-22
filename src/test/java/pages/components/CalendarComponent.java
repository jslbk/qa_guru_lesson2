package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement setYear = $(".react-datepicker__year-select"),
            setMonth = $(".react-datepicker__month-select"),
            setDay = $(".react-datepicker__month:not(.react-datepicker__day--outside-month)");

    public void setDate(String year, String month, String day) {
        setYear.selectOption(year);
        setMonth.selectOption(month);
        setDay.$(byText(day)).click();
    }

}