package com.epam.training.student_nadiia_roman.task3;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;
import java.util.function.Consumer;

public class ComputeEnginePage {
    private final WebDriver driver;

    @FindBy(xpath = "//input[@value='1']")
    private WebElement instancesNumberArea;

    @FindBy(xpath = "//div[contains(@class, 'VfPpkd-TkwUic')]//span[contains(text(), 'Operating System / Software')]")
    private WebElement ocArea;

    @FindBy(xpath = "//div[contains(@class, 'VfPpkd-TkwUic')]//span[contains(text(), 'Machine Family')]")
    private WebElement machineFamilyArea;

    @FindBy(xpath = "//div[contains(@class, 'VfPpkd-TkwUic')]//span[contains(text(), 'Series')]")
    private WebElement seriesArea;

    @FindBy(xpath = "//div[contains(@class, 'VfPpkd-TkwUic')]//span[contains(text(), 'Machine type')]")
    private WebElement machineTypeArea;

    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    private WebElement addGPUsButton;

    @FindBy(xpath = "//div[contains(@class, 'VfPpkd-TkwUic')]//span[contains(text(), 'GPU Model')]")
    private WebElement gpuModelArea;

    @FindBy(xpath = "//div[contains(@class, 'VfPpkd-TkwUic')]//span[contains(text(), 'Number of GPU')]")
    private WebElement numbOfGPUsArea;

    @FindBy(xpath = "//div[contains(@class, 'VfPpkd-TkwUic')]//span[contains(text(), 'Local SSD')]")
    private WebElement localSSDArea;

    @FindBy(xpath = "//div[contains(@class, 'VfPpkd-TkwUic')]//span[contains(text(), 'Region')]")
    private WebElement regionArea;

    @FindBy(xpath = "//span[@class='FOBRw-RLmnJb']")
    private WebElement shareButton;

    @FindBy(xpath = "//a[text()='Open estimate summary']")
    private WebElement openEstimateSummaryButton;


    public ComputeEnginePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public ComputeEnginePage computeNewEngine(String instancesNumber, String oc,
                                              String buttonNameOfProvisioningModel, String machineFamily,
                                              String series, String machineType, String gpuModel,
                                              String numbOfGPU, String localSSD, String region) {

        waitAndPerform(instancesNumberArea, element -> {
            element.clear();
            element.sendKeys(instancesNumber);
        });

        clickDropDownOption(ocArea, oc);
        clickElementByName(buttonNameOfProvisioningModel);
        clickDropDownOption(machineFamilyArea, machineFamily);
        clickDropDownOption(seriesArea, series);
        clickDropDownOption(machineTypeArea, machineType);
        waitAndPerform(addGPUsButton, WebElement::click);

        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(gpuModelArea));
        clickDropDownOption(gpuModelArea, gpuModel);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(numbOfGPUsArea));
        clickDropDownOption(numbOfGPUsArea, numbOfGPU);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(localSSDArea));
        clickDropDownOption(localSSDArea, localSSD);

        clickDropDownOption(regionArea, region);

        return this;
    }


    public CostEstimateSummaryPage openEstimateSummary() {

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", shareButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(openEstimateSummaryButton));
        wait.until(ExpectedConditions.elementToBeClickable(openEstimateSummaryButton));

        openEstimateSummaryButton.click();

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        return new CostEstimateSummaryPage(driver);
    }


    private void clickElementByName(String buttonName) {
        String xpathForElement = String.format("//*[text()='%s']", buttonName);
        WebElement element = driver.findElement(By.xpath(xpathForElement));
        element.click();
    }


    private void waitAndPerform(WebElement element, Consumer<WebElement> action) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));

        action.accept(element);
    }


    private void clickDropDownOption(WebElement element, String option) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        String xpathForOcOption = String.format("//*[contains(text(),'%s')]", option);

        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath(xpathForOcOption)));

        WebElement optionElement = driver.findElement(By.xpath(xpathForOcOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);

        try {
            Thread.sleep(300);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
