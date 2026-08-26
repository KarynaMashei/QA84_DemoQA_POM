package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ButtonPage extends BasePage {
    public ButtonPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "doubleClickBtn")
    WebElement doubleClickBtn;

    public ButtonPage doubleClick() {
        scrollToElement(doubleClickBtn);
        waitIsElementVisibility(doubleClickBtn,10);
        actions.pause(Duration.ofMillis(300))
                .doubleClick(doubleClickBtn)
                .perform();
        return this;
    }

    @FindBy(id = "doubleClickMessage")
    WebElement doubleClickMessage;

    public ButtonPage verifyDoubleClick(String text) {
        Assertions.assertTrue(shouldHaveText(doubleClickMessage,text,10));
        return this;
    }

    @FindBy(id = "rightClickBtn")
    WebElement rightClickBtn;

    public ButtonPage rightClick() {
        scrollToElement(rightClickBtn);
        waitIsElementVisibility(rightClickBtn,10);
        actions.pause(Duration.ofMillis(300))
                .contextClick(rightClickBtn)
                .perform();
        return this;
    }

    @FindBy(id = "rightClickMessage")
    WebElement rightClickMessage;

    public ButtonPage verifyRightClick(String text) {
        Assertions.assertTrue(shouldHaveText(rightClickMessage,text,10));
        return this;
    }
}
