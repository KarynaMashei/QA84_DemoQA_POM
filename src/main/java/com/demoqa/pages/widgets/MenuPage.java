package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MenuPage extends BasePage {
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#nav > li:first-child > a")
    WebElement mainItem1;

    @FindBy(css = "#nav > li:nth-child(2) > a")
    WebElement mainItem2;

    @FindBy(css = "#nav > li:nth-child(2) > ul > li:nth-child(3) > a")
    WebElement subList;

    public MenuPage hoverMouseOnMenu() {
        getWait(10).until(ExpectedConditions.elementToBeClickable(mainItem2));
        for (int attempt = 0; attempt < 3 && !isElementVisible(subList); attempt++) {
            scrollToElement(mainItem2);
            actions.moveToElement(mainItem1)
                    .pause(Duration.ofMillis(300))
                    .moveToElement(mainItem2)
                    .pause(Duration.ofMillis(700))
                    .perform();
        }
        waitIsElementVisibility(subList, 10);
        for (int attempt = 0; attempt < 3 && !isElementVisible(subItem1); attempt++) {
            actions.moveToElement(mainItem2)
                    .pause(Duration.ofMillis(300))
                    .moveToElement(subList)
                    .pause(Duration.ofMillis(700))
                    .perform();
        }
        waitIsElementVisibility(subItem1, 10);
        return this;
    }

    @FindBy(css = "#nav > li:nth-child(2) > ul > li:nth-child(3) > ul > li:first-child > a")
    WebElement subItem1;

    public MenuPage verifySubMenu() {
        Assertions.assertTrue(isElementVisible(subItem1));
        return this;
    }
}
