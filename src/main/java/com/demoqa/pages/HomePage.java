package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href$='/books']")
    WebElement bookStore;

    public SidePanel getBookStore() {
        clickWithJS(bookStore,0,400);
        return new SidePanel(driver);
    }

    @FindBy(css = "a[href$='/alertsWindows']")
    WebElement alertsWindows;

    public SidePanel getAlertsFrameWindows() {
        clickWithJS(alertsWindows,0,300);
        return new SidePanel(driver);
    }

    @FindBy(css = "a[href$='/widgets']")
    WebElement widgets;

    public SidePanel getWidgets() {
        click(widgets);
        return new SidePanel(driver);
    }
}
