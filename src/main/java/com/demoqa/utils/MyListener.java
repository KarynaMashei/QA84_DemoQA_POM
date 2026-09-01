package com.demoqa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

public class MyListener implements WebDriverListener {

    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);
    private static final DateTimeFormatter FILE_TIME =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private final WebDriver driver;

    public MyListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onError(
            Object target,
            Method method,
            Object[] args,
            InvocationTargetException error
    ) {
        if (error.getTargetException() instanceof NoAlertPresentException) {
            return;
        }

        logger.error("The test has a problem");
        logger.error("Method: {}",method.getName());
        logger.error("Target exception",error.getTargetException());
        saveScreenshot();
    }

    private void saveScreenshot() {
        Path screenshotsDirectory = Path.of("screenshots");
        Path screenshot = screenshotsDirectory.resolve(
                "screen_" + LocalDateTime.now().format(FILE_TIME) + ".png"
        );

        try {
            Files.createDirectories(screenshotsDirectory);
            File temporaryFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            Files.copy(
                    temporaryFile.toPath(),
                    screenshot,
                    StandardCopyOption.REPLACE_EXISTING
            );
            logger.error("Screenshot with error: {}",screenshot.toAbsolutePath());
        } catch (IOException | RuntimeException exception) {
            logger.error("Failed to save screenshot",exception);
        }
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logger.info("We opened the site {}",url);
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        logger.info("{} contains {}",element,result);
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("We clicked on {}",element);
    }

    @Override
    public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
        logger.info("Actions: {}",actions);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info(
                "We enter {} to element {}",
                Arrays.toString(keysToSend),
                element
        );
    }
}
