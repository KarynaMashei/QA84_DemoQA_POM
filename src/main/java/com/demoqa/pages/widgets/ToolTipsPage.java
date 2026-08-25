package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ToolTipsPage extends BasePage {
    public ToolTipsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "toolTipButton")
    WebElement toolTipButton;

    public ToolTipsPage hoversOnToolTips() {
        waitIsElementVisibility(toolTipButton,10);
        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                toolTipButton
        );
        actions.moveToElement(toolTipButton).perform();
        return this;
    }

    @FindBy(css = "[aria-describedby='buttonToolTip']")
    WebElement buttonToolTip;

    public ToolTipsPage verifyToolTips(String value) {
        waitIsElementVisibility(buttonToolTip,5);
        Assertions.assertEquals(
                value,
                getValue(toolTipButton, "aria-describedby")
        );
        return this;
    }
}
