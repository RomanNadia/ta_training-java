package com.epam.training.student_nadiia_roman.task3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CostEstimateSummaryPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[text()='Number of Instances']/following-sibling::node()")
    private WebElement instancesNumber;

    @FindBy(xpath = "//span[text()='Operating System / Software']/following-sibling::node()")
    private WebElement oc;

    @FindBy(xpath = "//span[text()='Machine type']/following-sibling::node()")
    private WebElement machineType;

    @FindBy(xpath = "//span[text()='Add GPUs']/following-sibling::node()")
    private WebElement addGPUs;

    @FindBy(xpath = "//span[text()='GPU Model']/following-sibling::node()")
    private WebElement gpuModel;

    @FindBy(xpath = "//span[text()='Number of GPUs']/following-sibling::node()")
    private WebElement numbOfGPUs;

    @FindBy(xpath = "//span[text()='Local SSD']/following-sibling::node()")
    private WebElement localSSD;

    @FindBy(xpath = "//span[text()='Region']/following-sibling::node()")
    private WebElement region;

    @FindBy(xpath = "//span[text()='Provisioning Model']/following-sibling::node()")
    private WebElement provisioningModel;


    public CostEstimateSummaryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getInstancesNumber() {
        return instancesNumber.getText();
    }

    public String getOc() {
        return oc.getText();
    }

    public String getMachineType() {
        return machineType.getText();
    }

    public String getLocalSSD() {
        return localSSD.getText();
    }

    public String getNumbOfGPUs() {
        return numbOfGPUs.getText();
    }

    public String getGpuModel() {
        return gpuModel.getText();
    }

    public String getAddGPUs() {
        return addGPUs.getText();
    }

    public String getRegion() {
        return region.getText();
    }

    public String getProvisioningModel() {
        return provisioningModel.getText();
    }

}
