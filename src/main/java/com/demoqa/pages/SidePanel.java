package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.pages.alertsFrameWindows.NestedFramesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import com.demoqa.pages.bookStore.LoginPage;
import com.demoqa.pages.elements.BrokenLinksImagesPage;
import com.demoqa.pages.elements.ButtonPage;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.pages.elements.UploadPage;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.pages.widgets.SelectPage;
import com.demoqa.pages.widgets.SliderPage;
import com.demoqa.pages.widgets.ToolTipsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidePanel extends BasePage {
    public SidePanel(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "a[href='/login']")
    WebElement loginLink;
    public LoginPage getLogin() {
        clickWithJS(loginLink,0,500);
        return new LoginPage(driver);
    }

    @FindBy(css = "a[href='/alerts']")
    WebElement alertItem;
    public AlertsPage getAlerts() {
        clickWithJS(alertItem,0,200);
        return new AlertsPage(driver);
    }

    @FindBy(css = "a[href='/browser-windows']")
    WebElement browserWindows;

    public WindowsPage getBrowserWindows() {
        clickWithJS(browserWindows,0,150);
        return new WindowsPage(driver);
    }

    @FindBy(css = "a[href='/frames']")
    WebElement frames;

    public FramesPage getFrames() {
        click(frames);
        return new FramesPage(driver);
    }

    @FindBy(css = "a[href='/nestedframes']")
    WebElement nestedFrames;

    public NestedFramesPage getNestedFrames() {
        clickWithJS(nestedFrames,0,400);
        return new NestedFramesPage(driver);
    }

    @FindBy(css = "a[href='/select-menu']")
    WebElement selectMenu;

    public SelectPage getSelectMenu() {
        clickWithJS(selectMenu,0,600);
        return new SelectPage(driver);
    }

    @FindBy(css = "a[href='/menu']")
    WebElement menu;

    public MenuPage getMenu() {
        clickWithJS(menu,0,550);
        return new MenuPage(driver);
    }

    @FindBy(css = "a[href='/slider']")
    WebElement slider;

    public SliderPage getSlider() {
        clickWithJS(slider,0,400);
        return new SliderPage(driver);
    }

    @FindBy(css = "a[href='/tool-tips']")
    WebElement toolTips;

    public ToolTipsPage getToolTips() {
        clickWithJS(toolTips,0,200);
        return new ToolTipsPage(driver);
    }

    @FindBy(css = "a[href='/buttons']")
    WebElement buttons;

    public ButtonPage getButtons() {
        clickWithJS(buttons,0,400);
        return new ButtonPage(driver);
    }

    @FindBy(css = "a[href='/text-box']")
    WebElement textBox;

    public TextBoxPage getTextBox() {
        clickWithJS(textBox,0,0);
        return new TextBoxPage(driver);
    }

    @FindBy(css = "a[href='/broken']")
    WebElement broken;

    public BrokenLinksImagesPage getBrokenLinkImages() {
        clickWithJS(broken,0,500);
        return new BrokenLinksImagesPage(driver);
    }

    @FindBy(css = "a[href='/upload-download']")
    WebElement upload;

    public UploadPage getUpload() {
        clickWithJS(upload,0,600);
        return new UploadPage(driver);
    }
}
