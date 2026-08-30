package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextBoxPage extends BasePage {
    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    public TextBoxPage copyPast(String address) {
        typeWithJS(currentAddress,address,0,400);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        actions.sendKeys(Keys.TAB).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        return this;
    }

    @FindBy(id = "submit")
    WebElement submit;

    public TextBoxPage clickOnSubmitButton() {
        clickWithJS(submit,0,200);
        return this;
    }

    @FindBy(css = ".border #currentAddress")
    WebElement currentAddressResult;

    @FindBy(css = ".border #permanentAddress")
    WebElement permanentAddressResult;

    public TextBoxPage verifyAddress() {
        String[] current = currentAddressResult.getText().split(":");
        String[] permanent = permanentAddressResult.getText().split(":");
        Assertions.assertEquals(current[1],permanent[1]);
        return this;
    }

    @FindBy(id = "userName")
    WebElement userName;

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "permanentAddress")
    WebElement permanentAddress;

    public TextBoxPage enterPersonalData(String name, String email, String address) {
        typeWithJS(userName,name,0,300);
        typeWithJS(userEmail,email,0,300);
        typeWithJS(currentAddress,address,0,300);
        typeWithJS(permanentAddress,address,0,300);
        return this;
    }

    public TextBoxPage enterPersonalDataWithJS(String name, String email) {
        js.executeScript("arguments[0].value=arguments[1];",userName,name);
        js.executeScript("arguments[0].value=arguments[1];",userEmail,email);
        return this;
    }

    public TextBoxPage clickOnSubmitWithJS() {
        js.executeScript("arguments[0].click();",submit);
        js.executeScript("arguments[0].style.backgroundColor='red';",submit);
        return this;
    }

    public TextBoxPage getInnerText() {
        String innerText = js.executeScript(
                "return document.documentElement.innerText;"
        ).toString();
        System.out.println(innerText);
        return this;
    }

    public TextBoxPage verifyUrl() {
        String url = js.executeScript("return document.URL;").toString();
        Assertions.assertTrue(url.contains("demoqa.com/text-box"));
        return this;
    }

    public TextBoxPage refreshWithJS() {
        js.executeScript("history.go(0);");
        return this;
    }

    public TextBoxPage navigateWithJS(String url) {
        js.executeScript("window.location=arguments[0];",url);
        return this;
    }

    public TextBoxPage verifyTitleIsNotEmpty() {
        String title = js.executeScript("return document.title;").toString();
        Assertions.assertFalse(title.isBlank());
        return this;
    }
}
