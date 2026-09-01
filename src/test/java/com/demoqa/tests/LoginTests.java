package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.bookStore.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @BeforeEach
    public void precondition(){
        new HomePage(driver).getBookStore();
        new SidePanel(driver).getLogin();
    }
    @Test
    @Tag("smoky")
    public void loginPositiveTest(){
        new LoginPage(driver).enterUserData("Kris","Aa1234567!")
                .clickOnLoginButton()
                .verifyUserName("Kris");
    }

    @Test
    @Tag("parameters")
    public void loginPositiveTestWithParameters(){
        String userName = System.getProperty("userName","Kris");
        String password = System.getProperty("password","Aa1234567!");
        new LoginPage(driver).enterUserData(userName,password)
                .clickOnLoginButton()
                .verifyUserName(userName);
    }
}
