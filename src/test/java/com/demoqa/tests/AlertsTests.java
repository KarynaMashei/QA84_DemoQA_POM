package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.pages.alertsFrameWindows.NestedFramesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlertsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;
    FramesPage frames;
    NestedFramesPage nestedFrames;

    @BeforeEach
    public void precondition(){
        new HomePage(driver).getAlertsFrameWindows();
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
        frames = new FramesPage(driver);
        nestedFrames = new NestedFramesPage(driver);
    }
    @Test
    public void waitAlertTest(){
        sidePanel.getAlerts();
        alerts.verifyAlertWithTimer();
    }
    @Test
    public void alertWithSelectResult(){
        sidePanel.getAlerts();
        alerts.clickOnResult("Cancel")
                .verifyResult("Cancel");

    }

    @Test
    public void sentMessageToAlertTest(){
        sidePanel.getAlerts();
        alerts.clickOnPromptButton()
                .sendMessageToAlert("Hello world!")
                .clickOnResult("Ok")
                .verifyMessage("Hello world!");
    }

    @Test
    public void newTabTest(){
        sidePanel.getBrowserWindows();
        new WindowsPage(driver).clickOnNewTabButton()
                .switchToNewTab(1)
                .verifyToTabTitle("This is a sample page");
    }

    @Test
    public void frameByIdTest(){
        sidePanel.getFrames();
        frames.switchToFrameById()
                .verifyFrameByTitle("This is a sample page")
                .switchToHomePage()
                .verifyMainPageByTitle("Frames");
    }

    @Test
    public void nestedFramesTest(){
        sidePanel.getNestedFrames();
        nestedFrames.verifyNestedFrames();
    }
}
