package pages.components;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {
    public ResultTableComponent checkResult(String label, String value) {
        $(".table-responsive").$(byText(label)).parent().$("td", 1)
                .shouldHave(exactText(value));

        return this;
    }

}

