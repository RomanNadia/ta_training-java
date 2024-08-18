package com.epam.training.student_nadiia_roman.task1;

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

    @FindBy(id="postform-text")
    private WebElement codeArea;

    @FindBy(id="select2-postform-expiration-container")
    private WebElement pasteExpirationArea;

    @FindBy(id="postform-name")
    private WebElement pasteNameArea;

    @FindBy(xpath="//button[text()='Create New Paste']")
    private WebElement createNewPasteButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public PastePage addNewPaste(String codeValue, String expirationValue, String nameValue) {
        codeArea.sendKeys(codeValue);

        pasteExpirationArea.click();

        String xpathForExpirationValue = String.format("//li[contains(text(),'%s')]", expirationValue);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath(xpathForExpirationValue))));
        WebElement expirationOption = driver.findElement(By.xpath(xpathForExpirationValue));
        expirationOption.click();

        pasteNameArea.sendKeys(nameValue);
        createNewPasteButton.click();

        return new PastePage(driver);
    }

}
