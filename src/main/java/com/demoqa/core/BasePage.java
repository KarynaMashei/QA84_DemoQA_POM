package com.demoqa.core;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URI;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    public static JavascriptExecutor js;
    public static SoftAssertions softly;
    public static Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        softly = new SoftAssertions();
        actions = new Actions(driver);
    }

    public void scrollWithJS(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public void scrollToElement(WebElement element) {
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
                element
        );
    }

    public void clickWithJS(WebElement element, int x, int y) {
        scrollWithJS(x, y);
        js.executeScript("arguments[0].click();", element);
    }

    public void typeWithJS(WebElement element, String text, int x, int y) {
        if (text != null) {
            scrollToElement(element);
            js.executeScript("arguments[0].focus();", element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void click(WebElement element) {
        scrollToElement(element);
        try {
            getWait(5).until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            scrollToElement(element);
            js.executeScript("arguments[0].click();", element);
        }
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public boolean isAlertPresent(int time) {
        Alert alert = getWait(time)
                .until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert().accept();
            return true;
        }
    }

    public WebDriverWait getWait(int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time));
    }

    public boolean isContainsText(String text, WebElement element) {
        return element.getText().contains(text);
    }

    public boolean shouldHaveText(WebElement element, String text, int time) {
        return getWait(time).until(
                ExpectedConditions.textToBePresentInElement(element, text)
        );
    }

    public boolean isContainsCssValue(
            String color,
            WebElement element,
            String property
    ) {
        return element.getCssValue(property).contains(color);
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getValue(WebElement element, String attribute) {
        return element.getDomAttribute(attribute);
    }

    public void waitIsElementVisibility(WebElement element, int time) {
        getWait(time).until(ExpectedConditions.visibilityOf(element));
    }

    public int getStatusCode(String url) {
        if (url == null || url.isBlank()) {
            return -1;
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(url)
                    .toURL()
                    .openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            return connection.getResponseCode();
        } catch (Exception e) {
            return -1;
        }
    }

    public void verifyLinks(String url) {
        int statusCode = getStatusCode(url);
        softly.assertThat(statusCode)
                .as("Response code for " + url)
                .isBetween(200,399);
    }

    public void clickWithRectangle(WebElement element) {
        scrollToElement(element);
        Rectangle rectangle = element.getRect();
        int xOffset = rectangle.getWidth() / 4;
        int yOffset = rectangle.getHeight() / 4;
        actions.moveToElement(element)
                .moveByOffset(-xOffset,-yOffset)
                .click()
                .perform();
    }

}
