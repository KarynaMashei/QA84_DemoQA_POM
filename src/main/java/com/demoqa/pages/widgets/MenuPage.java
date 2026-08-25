package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends BasePage {
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Main Item 2']")
    WebElement mainItem2;

    @FindBy(xpath = "//a[.='SUB SUB LIST »']")
    WebElement subList;

    public MenuPage hoverMouseOnMenu() {
        waitIsElementVisibility(mainItem2, 5);
        actions.moveToElement(mainItem2).perform();
        waitIsElementVisibility(subList, 5);
        actions.moveToElement(subList).perform();
        return this;
    }

    @FindBy(xpath = "//a[.='Sub Sub Item 1']")
    WebElement subItem1;

    public MenuPage verifySubMenu() {
        Assertions.assertTrue(isElementVisible(subItem1));
        return this;
    }
}
