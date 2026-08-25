package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SelectPage extends BasePage {
    public SelectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "oldSelectMenu")
    WebElement oldSelectMenu;

    public SelectPage oldStyleSelect(String color) {
        new Select(oldSelectMenu).selectByVisibleText(color);
        return this;
    }

    public SelectPage verifyColor() {
        String selectedColor = new Select(oldSelectMenu)
                .getFirstSelectedOption()
                .getText();
        Assertions.assertTrue(shouldHaveText(oldSelectMenu, selectedColor, 5));
        return this;
    }

    @FindBy(id = "react-select-4-input")
    WebElement input;

    @FindBy(css = "html")
    WebElement space;

    public SelectPage multiSelect(String[] colors) {
        for (String text: colors){
            input.sendKeys(text);
            input.sendKeys(Keys.ENTER);
        }
        click(space);
        return this;
    }

    public SelectPage verifyMultiSelect(String[] colors) {
        for (String text: colors){
            WebElement element = driver.findElement(
                    By.xpath("//*[.='" + text + "']")
            );
            softly.assertThat(shouldHaveText(element,text,2));
        }
        softly.assertAll();
        return this;
    }

    public SelectPage verifySelectedCar(String car, String color) {
        WebElement selectedCar = driver.findElement(
                By.cssSelector("[value='" + car + "']")
        );
        click(selectedCar);
        String actualColor = selectedCar.getCssValue("background-color");
        Assertions.assertTrue(
                actualColor.contains(color),
                "Expected selected color " + color + ", but was " + actualColor
        );
        return this;
    }
}
