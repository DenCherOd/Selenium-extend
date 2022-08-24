package absoft.tests;

import absoft.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AjaxLoaderTest extends BaseTest {

    private final By ClickMeButton = By.xpath("//*[text()='CLICK ME!']");
    private final By message = By.xpath("//h4[text()='Well Done For Waiting....!!!']");
    private WebElement wait;

    @Test
    public void waitForPopUpTest() {
        driver.get("http://webdriveruniversity.com/Ajax-Loader/index.html");
        waitForVisibilityOfElement(ClickMeButton);
        driver.findElement(ClickMeButton).click();
        waitForVisibilityOfElement(message);
        Assert.assertTrue(driver.findElement(message).isDisplayed(), "Message is not displayed");
        driver.findElement(By.xpath("//*[text()='Close']")).click();
        Boolean stalenessWait = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class='modal-content']"))));
        Assert.assertTrue(stalenessWait, "Pop-up is displayed");
    }

    private WebElement waitForVisibilityOfElement(By xpath) {
        return wait = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(xpath)));
    }
}