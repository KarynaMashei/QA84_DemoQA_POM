package com.demoqa.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    public static JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
    }
    public void scrollWithJS(int x,int y){
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }
    public void clickWithJS(WebElement element,int x,int y){
        scrollWithJS(x,y);
        js.executeScript("arguments[0].click();", element);
    }
    public void typeWithJS(WebElement element,String text,int x,int y){
        scrollWithJS(x,y);
        type(element,text);
    }

    public void click(WebElement element){
        element.click();
    }
    public void type(WebElement element,String text){
        if (text!=null){
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }
    public boolean isAlertPresent(int time){
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.alertIsPresent());
        if (alert==null){
            return false;
        }else {
            driver.switchTo().alert().accept();
            return true;
        }
    }

    public boolean isContainsText(String text, WebElement element) {
        return element.getText().contains(text);
    }
}