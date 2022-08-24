package absoft.tests;

import absoft.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DropdownCheckboxRadioButtonTest extends BaseTest {

    private String checkBoxes = "//input[@value='option-#number#']";

    @Test
    public void selectItemsAndCheckSelectedOrDisabledTest() {
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        chooseItemsInProgrammingSelects();
        selectCheckBoxes(1, 4);
        driver.findElement(By.xpath("//input[@value='orange']")).click();
        checkingForSelected();
        checkingForDisabled();
    }

    private void chooseItemsInProgrammingSelects() {
        Select programmingLanguagesSelect = new Select(driver.findElement(By.xpath("//select[@id='dropdowm-menu-1']")));
        Select backendDevelopmentToolsSelect = new Select(driver.findElement(By.xpath("//select[@id='dropdowm-menu-2']")));
        Select frontendDevelopmentToolsSelect = new Select(driver.findElement(By.xpath("//select[@id='dropdowm-menu-3']")));
        programmingLanguagesSelect.selectByVisibleText("JAVA");
        backendDevelopmentToolsSelect.selectByVisibleText("TestNG");
        frontendDevelopmentToolsSelect.selectByVisibleText("HTML");
    }

    private void selectCheckBoxes(int... checkBoxNumbers) {
        List<Integer> targetCheckboxes = new ArrayList<>();
        for (int checkBoxNumber : checkBoxNumbers) {
            targetCheckboxes.add(checkBoxNumber);
        }
        for (int i = 1; i <= 4; i++) {
            By locatorOfCheckbox = By.xpath(checkBoxes.replace("#number#", String.valueOf(i)));
            if (driver.findElement(locatorOfCheckbox).isSelected() &&
                    targetCheckboxes.contains(i)) {
                continue;
            } else if (!driver.findElement(locatorOfCheckbox).isSelected() &&
                    targetCheckboxes.contains(i)) {
                driver.findElement(locatorOfCheckbox).click();
            } else if (driver.findElement(locatorOfCheckbox).isSelected() &&
                    !targetCheckboxes.contains(i)) {
                driver.findElement(locatorOfCheckbox).click();
            }
        }
    }

    private void checkingForSelected() {
        String pumpkinAttribute = driver.findElement(By.xpath("//input[@value='pumpkin']")).getAttribute("checked");
        Assert.assertTrue(pumpkinAttribute.equalsIgnoreCase("true"), "Pumpkin is not selected");
        Select fruitSelect = new Select((driver.findElement(By.xpath("//select[@id='fruit-selects']"))));
        WebElement option = fruitSelect.getFirstSelectedOption();
        String defaultFruitSelectItem = option.getText();
        Assert.assertTrue(defaultFruitSelectItem.equals("Grape"));
    }

    private void checkingForDisabled() {
        String cabbageAttribute = driver.findElement(By.xpath("//input[@value='cabbage']")).getAttribute("disabled");
        Assert.assertTrue(cabbageAttribute.equalsIgnoreCase("true"), "Cabbage is not prohibited");
        String orangeSelectAttribute = driver.findElement(By.xpath("//option[@value='orange']"))
                .getAttribute("disabled");
        Assert.assertTrue(orangeSelectAttribute.equals("true"), "Orange is not disabled");
    }
}