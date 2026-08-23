package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlertsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;

    @BeforeEach
    public void precondition(){
        new HomePage(driver).getAlertsFrameWindows();
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
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
}