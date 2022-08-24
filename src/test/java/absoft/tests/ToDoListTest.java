package absoft.tests;

import absoft.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ToDoListTest extends BaseTest {
    @Test
    public void addAndCheckListItem() {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");
        driver.findElement(By.xpath("//input[@placeholder='Add new todo']"))
                .sendKeys("new item", Keys.ENTER);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='container']//li[4]")).getText(),
                "new item");
    }

    @Test
    public void removeFirstItemAndCheckList() {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");
        By goToPotionClassItem = By.xpath("//*[@id='container']//*[contains(text(),'Go to potion class')]" +
                "//*[@class='fa fa-trash']");
        driver.findElement(goToPotionClassItem)
                .click();
        Boolean wait = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(goToPotionClassItem));
        Assert.assertTrue(wait);
    }
}