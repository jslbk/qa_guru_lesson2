package pages.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.StudentRegistrationFormPage;

import java.io.File;

public class UploadFileComponent {

    public void uploadFile(String fileName) {
        // Locate the file input element by CSS selector
        String fileInputSelector = "#uploadPicture";

        // Upload the file by setting the input element's value to the {fileName} path
        SelenideElement fileInput = Selenide.$(fileInputSelector);
        fileInput.uploadFile(getFilePath(fileName));
    }

    private static File getFilePath(String fileName) {
        // Get the path of the {fileName} in the "resources" directory
        ClassLoader classLoader = StudentRegistrationFormPage.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getPath());
    }
}
