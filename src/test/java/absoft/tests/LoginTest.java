package absoft.tests;

import absoft.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private By usernameInput = By.xpath("//input[@placeholder='Username']");
    private By passwordInput = By.xpath("//input[@placeholder='Password']");
    private By loginButton = By.xpath("//button[@id='login-button']");

    @Test
    public void verifyAuthWithEmptyPath() {
        driver.get("http://webdriveruniversity.com/Login-Portal/index.html?");
        driver.findElement(usernameInput).sendKeys("Denys");
        driver.findElement(loginButton).click();
        Assert.assertEquals(driver.switchTo().alert().getText(), "validation failed",
                "Login without password is possible");
    }
}