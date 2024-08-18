package com.epam.training.student_nadiia_roman.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePageAddPasteTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://pastebin.com/");
    }

    @Test
    public void testAddingNewPaste() {
        HomePage homePage = new HomePage(driver);
        PastePage pastePage
                = homePage.addNewPaste("Hello from WebDriver", "10 Minutes", "helloweb");
        String expectedTitle = "helloweb - Pastebin.com";
        assertEquals(expectedTitle, driver.getTitle());
    }

    @AfterAll
    public static void setDown() {
        driver.quit();
    }
}
