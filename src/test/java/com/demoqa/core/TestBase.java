package com.demoqa.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    protected WebDriver driver;
    protected ApplicationManager app = new ApplicationManager(
            System.getProperty("browser","chrome")
    );
    public static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeEach
    public void init(){
        driver = app.start();
    }
    @AfterEach
    public void tearDown(){
        app.stop();
    }

}
