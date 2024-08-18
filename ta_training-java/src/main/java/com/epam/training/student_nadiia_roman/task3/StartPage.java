package com.epam.training.student_nadiia_roman.task3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage {
    private final WebDriver driver;

    @FindBy(xpath = "//span[text()='Add to estimate']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//div[@class='aHij0b-aGsRMb' and descendant::*[contains(text(), 'Compute Engine')]]")
    private WebElement computeEngineButton;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ComputeEnginePage followToComputeEnginePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(addToEstimateButton));
        wait.until(ExpectedConditions.elementToBeClickable(addToEstimateButton));
        addToEstimateButton.click();

        wait.until(ExpectedConditions.visibilityOf(computeEngineButton));
        wait.until(ExpectedConditions.elementToBeClickable(computeEngineButton));
        computeEngineButton.click();

        return new ComputeEnginePage(driver);
    }

}
