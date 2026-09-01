package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.BrokenLinksImagesPage;
import com.demoqa.pages.elements.ButtonPage;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.pages.elements.UploadPage;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.nio.file.Path;

public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonPage buttons;
    TextBoxPage textBox;
    BrokenLinksImagesPage brokenLinks;
    UploadPage upload;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        buttons = new ButtonPage(driver);
        textBox = new TextBoxPage(driver);
        brokenLinks = new BrokenLinksImagesPage(driver);
        upload = new UploadPage(driver);
        new HomePage(driver).getElements();
    }

    @Test
    public void doubleClickTest() {
        sidePanel.getButtons();
        buttons.doubleClick()
                .verifyDoubleClick("You have done a double click");
    }

    @Test
    public void rightClickTest() {
        sidePanel.getButtons();
        buttons.rightClick()
                .verifyRightClick("You have done a right click");
    }

    @Test
    public void copyPastTest() {
        sidePanel.getTextBox();
        textBox.copyPast("Berlin,12435")
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    public void textBoxWithParameterTest(String name,String email,String address) {
        sidePanel.getTextBox();
        textBox.enterPersonalData(name,email,address)
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data84.csv")
    public void textBoxFromCsvFileParameterTest(String name,String email,String address) {
        sidePanel.getTextBox();
        textBox.enterPersonalData(name,email,address)
                .clickOnSubmitButton()
                .verifyAddress();
        logger.info("TEST FOR '{}' TEST",name);
    }

    @Test
    public void javaScriptExecutorTest() {
        sidePanel.getTextBox();
        textBox.enterPersonalDataWithJS("Kris Tomash","test123@gmail.com")
                .clickOnSubmitWithJS()
                .getInnerText()
                .verifyUrl()
                .refreshWithJS()
                .navigateWithJS("https://icarro-v1.netlify.app/let-car-work")
                .verifyTitleIsNotEmpty();
    }

    @Test
    @Tag("smoky")
    public void getAllLinksTest() {
        sidePanel.getBrokenLinkImages();
        brokenLinks.getAllLinks();
    }

    @Test
    public void checkBrokenLinksTest() {
        sidePanel.getBrokenLinkImages();
        brokenLinks.checkBrokenLinks();
    }

    @Test
    public void checkBrokenImagesTest() {
        sidePanel.getBrokenLinkImages();
        brokenLinks.checkBrokenImages();
    }

    @Test
    public void uploadFileTest() {
        String filePath = Path.of("src","test","resources","D1.txt")
                .toAbsolutePath()
                .toString();
        sidePanel.getUpload();
        upload.selectFile(filePath)
                .verifyFilePath("D1.txt");
    }
}
