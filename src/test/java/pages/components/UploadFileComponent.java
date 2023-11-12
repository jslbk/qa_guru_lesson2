package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class UploadFileComponent {
    private final SelenideElement uploadPictureInput = $("#uploadPicture");

    public void uploadPicture(String filename) {
        uploadPictureInput.uploadFromClasspath(filename);
    }

}
