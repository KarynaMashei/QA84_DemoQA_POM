package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NestedFramesPage extends BasePage {
    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "frame1")
    WebElement frame1;

    @FindBy(css = "body")
    WebElement body;

    @FindBy(css = "iframe")
    List<WebElement> iframes;

    public NestedFramesPage verifyNestedFrames() {
        driver.switchTo().frame(frame1);
        softly.assertThat(isContainsText("Parent frame",body));
        softly.assertThat(iframes.size()).isEqualTo(1);

        driver.switchTo().frame(0);
        softly.assertThat(isContainsText("Child Iframe",body));

        driver.switchTo().parentFrame();
        softly.assertThat(isContainsText("Parent frame",body));
        softly.assertAll();
        return this;
    }
}
