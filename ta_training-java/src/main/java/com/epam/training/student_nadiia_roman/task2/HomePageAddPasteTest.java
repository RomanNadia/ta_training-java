package com.epam.training.student_nadiia_roman.task2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageAddPasteTest {
    private static WebDriver driver;

    private final String codeInput = "git config --global user.name \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force\n";

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://pastecode.dev/");
    }

    //This is not a task description site, so some fields have different naming
    @Test
    public void testAddingNewPaste() {
        HomePage homePage = new HomePage(driver);
        PastePage pastePage
                = homePage.addNewPaste(codeInput, "10 Minutes",
                "how to gain dominance among developers", "Bash");

        assertEquals("how to gain dominance among developers - PasteCode.dev", driver.getTitle());
        assertEquals("Bash", pastePage.getSyntaxText());
        assertEquals(codeInput, pastePage.getCodeText());
    }

    @AfterAll
    public static void setDown() {
        driver.quit();
    }
}
