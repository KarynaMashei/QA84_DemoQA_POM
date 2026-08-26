package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.ButtonPage;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonPage buttons;
    TextBoxPage textBox;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        buttons = new ButtonPage(driver);
        textBox = new TextBoxPage(driver);
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
}
