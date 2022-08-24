package absoft.tests;

import absoft.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class DatepickerTest extends BaseTest {

    private final By datepickerButton = By.xpath("//*[@class='glyphicon glyphicon-calendar']");
    private final By datepickerDaysChoice = By.xpath("//*[@class='datepicker-days']//*[@class='datepicker-switch']");
    private final By datepickerMonthsChoice = By.xpath("//*[@class='datepicker-months']//*[@class='datepicker-switch']");
    private final By previousYearsButton = By.xpath("//*[@class='datepicker-years']//*[@class='prev']");
    private final String allYears = "//span[@class='year'][text()='#year#']";
    private final String allMonths = "//span[@class='month'][text()='#month#']";
    private final String allDays = "//*[@class=' table-condensed']//td[text()='#day#']";

    @Test
    public void datePickTest() {
        driver.get("http://webdriveruniversity.com/Datepicker/index.html");
        driver.findElement(datepickerButton).click();
        driver.findElement(datepickerDaysChoice).click();
        driver.findElement(datepickerMonthsChoice).click();
        chooseYear("1994");
        chooseMonth("August");
        chooseDay("24");
    }

    private void chooseYear(String searchingYear) {
        String targetLocator = allYears.replace("#year#", searchingYear);
        boolean isVisible = false;
        WebElement webElement;
        boolean isPresent = driver.findElements(By.xpath(targetLocator)).size() > 0;
        if (isPresent) {
            webElement = new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(targetLocator))));
            isVisible = webElement.isDisplayed();
        }
        int counter = 0;
        while (!isVisible && counter < 10) {
            driver.findElement(previousYearsButton).click();
            isPresent = driver.findElements(By.xpath(targetLocator)).size() > 0;
            if (isPresent) {
                webElement = new WebDriverWait(driver, Duration.ofSeconds(2))
                        .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(targetLocator))));
                isVisible = webElement.isDisplayed();
            }
            counter++;
        }
        driver.findElement(By.xpath(targetLocator)).click();
    }

    private void chooseMonth(String searchingMonth) {
        String targetLocator = allMonths.replace("#month#", monthTransform(searchingMonth));
        driver.findElement(By.xpath(targetLocator)).click();
    }

    private String monthTransform(String inputMonth) {
        return inputMonth.substring(0, 3);
    }

    public void chooseDay(String searchingDay) {
        String targetLocator = allDays.replace("#day#", searchingDay);
        driver.findElement(By.xpath(targetLocator)).click();
    }
}