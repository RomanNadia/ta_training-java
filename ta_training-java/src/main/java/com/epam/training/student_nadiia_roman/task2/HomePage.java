package com.epam.training.student_nadiia_roman.task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    @FindBy(tagName = "textarea")
    private WebElement codeArea;

    @FindBy(xpath = "//input[@aria-label='Filename']")
    private WebElement fileNameArea;

    @FindBy(xpath = "//div[@class='nice-select el-select form-control  ']")
    private WebElement syntaxArea;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement titleArea;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createPasteButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastePage addNewPaste(String codeValue, String fileNameValue, String titleValue, String syntaxValue) {
        codeArea.sendKeys(codeValue);
        fileNameArea.sendKeys(fileNameValue);
        titleArea.sendKeys(titleValue);

        syntaxArea.click();
        String xpathForExpirationValue = String.format("//li[contains(text(),'%s')]", syntaxValue);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(xpathForExpirationValue))));
        driver.findElement(By.xpath(xpathForExpirationValue)).click();

        createPasteButton.click();
        return new PastePage(driver);
    }

}

