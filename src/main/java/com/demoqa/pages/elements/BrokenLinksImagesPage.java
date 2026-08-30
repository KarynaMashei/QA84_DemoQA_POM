package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrokenLinksImagesPage extends BasePage {
    public BrokenLinksImagesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.col-md-6 a")
    List<WebElement> allLinks;

    public BrokenLinksImagesPage getAllLinks() {
        System.out.println("Total links on the page = " + allLinks.size());
        for (WebElement link : allLinks) {
            System.out.println(link.getText());
        }
        return this;
    }

    public BrokenLinksImagesPage checkBrokenLinks() {
        int validLinks = 0;
        int brokenLinks = 0;
        for (WebElement link : allLinks) {
            int statusCode = getStatusCode(link.getDomAttribute("href"));
            if (statusCode >= 400 || statusCode == -1) {
                brokenLinks++;
            } else {
                validLinks++;
            }
        }
        softly.assertThat(validLinks).as("Valid links").isGreaterThan(0);
        softly.assertThat(brokenLinks).as("Broken links").isGreaterThan(0);
        softly.assertAll();
        return this;
    }

    @FindBy(css = "img")
    List<WebElement> images;

    public BrokenLinksImagesPage checkBrokenImages() {
        int validImages = 0;
        int brokenImages = 0;
        for (WebElement image : images) {
            boolean imageDisplayed = (Boolean) js.executeScript(
                    "return typeof arguments[0].naturalWidth !== 'undefined' " +
                            "&& arguments[0].naturalWidth > 0;",
                    image
            );
            if (imageDisplayed) {
                validImages++;
            } else {
                brokenImages++;
            }
        }
        softly.assertThat(validImages).as("Valid images").isGreaterThan(0);
        softly.assertThat(brokenImages).as("Broken images").isGreaterThan(0);
        softly.assertAll();
        return this;
    }
}
