package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FramesPage extends BasePage {
    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "frame1")
    WebElement frame1;

    @FindBy(css = "h1")
    WebElement title;

    @FindBy(css = "iframe")
    List<WebElement> iframes;

    public FramesPage switchToFrameById() {
        System.out.println(iframes.size());
        driver.switchTo().frame(frame1);
        return this;
    }

    public FramesPage verifyFrameByTitle(String text) {
        Assertions.assertTrue(isContainsText(text,title));
        return this;
    }

    public FramesPage switchToHomePage() {
        driver.switchTo().defaultContent();
        return this;
    }

    @FindBy(css = ".text-center")
    WebElement mainTitle;

    public FramesPage verifyMainPageByTitle(String text) {
        Assertions.assertTrue(isContainsText(text,mainTitle));
        return this;
    }
}
