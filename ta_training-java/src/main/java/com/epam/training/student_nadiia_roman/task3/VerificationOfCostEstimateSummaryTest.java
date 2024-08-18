package com.epam.training.student_nadiia_roman.task3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerificationOfCostEstimateSummaryTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://cloud.google.com/products/calculator");
        driver.manage().window().maximize();
    }

    @Test
    public void testEqualityOfParametersOnPages() {
        StartPage startPage = new StartPage(driver);
        ComputeEnginePage computeEnginePage = startPage.followToComputeEnginePage();
        computeEnginePage.computeNewEngine("4",
                "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)",
                "Regular", "General Purpose", "N1", "n1-standard-8",
                "NVIDIA V100", "1", "2x375 GB", "Netherlands (europe-west4)");

        CostEstimateSummaryPage costEstimateSummaryPage =
                computeEnginePage.openEstimateSummary();

        assertEquals("4", costEstimateSummaryPage.getInstancesNumber());
        assertEquals("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)",
                costEstimateSummaryPage.getOc());
        assertEquals("Regular", costEstimateSummaryPage.getProvisioningModel());
        assertEquals("n1-standard-8, vCPUs: 8, RAM: 30 GB", costEstimateSummaryPage.getMachineType());
        assertEquals("true", costEstimateSummaryPage.getAddGPUs());
        assertEquals("NVIDIA V100", costEstimateSummaryPage.getGpuModel());
        assertEquals("1", costEstimateSummaryPage.getNumbOfGPUs());
        assertEquals("2x375 GB", costEstimateSummaryPage.getLocalSSD());
        assertEquals("Netherlands (europe-west4)", costEstimateSummaryPage.getRegion());
    }

    @AfterAll
    public static void setDown() {
        driver.quit();
    }
}
