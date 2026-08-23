package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertsPage extends BasePage {
    public AlertsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id ="timerAlertButton")
    WebElement timerAlertButton;

    public AlertsPage verifyAlertWithTimer() {
        clickWithJS(timerAlertButton,0,200);
        Assertions.assertTrue(isAlertPresent(5));
        return this;
    }
    @FindBy(id = "confirmButton")
    WebElement confirmButton;
    public AlertsPage clickOnResult(String result) {
        clickWithJS(confirmButton,0,200);
        if (result != null && result.equals("Ok")){
            driver.switchTo().alert().accept();
        }else if (result != null && result.equals("Cancel")){
            driver.switchTo().alert().dismiss();
        }
        return this;
    }
    @FindBy(id = "confirmResult")
    WebElement confirmResult;
    public AlertsPage verifyResult(String text) {
        Assertions.assertTrue(isContainsText(text, confirmResult));
        return this;
    }

}