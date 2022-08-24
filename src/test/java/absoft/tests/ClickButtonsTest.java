package absoft.tests;

import absoft.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ClickButtonsTest extends BaseTest {

    private final By webElementClickButton = By.xpath("//*[@id='button1']");
    private final By javaScriptClickButton = By.cssSelector("#button2");
    private final By actionMoveAndClickButton = By.xpath("//*[@id='button3']");
    private final By popUpWebElementCloseButton = By.xpath("//div[@id='myModalClick']//button[text()='Close']");
    private final By popUpJavaScriptCloseButton = By.xpath("//div[@id='myModalJSClick']//button[text()='Close']");
    private final By popUpActionCloseButton = By.xpath("//div[@id='myModalMoveClick']//button[text()='Close']");
    private WebElement waitForCloseButton;

    @Test
    public void clickButtonsByDifferentWays() {
        driver.get("http://webdriveruniversity.com/Click-Buttons/index.html");
        clickWebElementButton(webElementClickButton);
        closePopUp(popUpWebElementCloseButton);
        clickByJavaScript(javaScriptClickButton);
        closePopUp(popUpJavaScriptCloseButton);
        Actions action = new Actions(driver);
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.invisibilityOf(driver.findElement(popUpJavaScriptCloseButton)));
        action.moveToElement(driver.findElement(actionMoveAndClickButton)).click().perform();
        closePopUp(popUpActionCloseButton);
    }

    private void closePopUp(By elementLocator) {
        waitForCloseButton = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(driver.findElement(elementLocator)));
        driver.findElement(elementLocator).click();
    }

    private void clickWebElementButton(By elementLocator) {
        driver.findElement(elementLocator).click();
    }

    private void clickByJavaScript(By elementLocator) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(elementLocator));
    }
}