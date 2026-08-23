package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.bookStore.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidePanel extends BasePage {
    public SidePanel(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "a[href='/login']")
    WebElement loginLink;
    public LoginPage getLogin() {
        clickWithJS(loginLink,0,500);
        return new LoginPage(driver);
    }

    @FindBy(css = "a[href='/alerts']")
    WebElement alertItem;
    public AlertsPage getAlerts() {
        clickWithJS(alertItem,0,200);
        return new AlertsPage(driver);
    }
}