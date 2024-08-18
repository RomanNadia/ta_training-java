package com.epam.training.student_nadiia_roman.task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PastePage {
    private final WebDriver driver;

    @FindBy(xpath = "//a[contains(@href, 'syntax')]")
    private WebElement syntax;

    @FindBy(xpath = "//table")
    private WebElement code;

    public PastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSyntaxText() {
        return syntax.getText();
    }

    public String getCodeText() {
        String codeText = "";
        List<WebElement> rows = code.findElements(By.xpath(".//tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td[@class='hljs-ln-code']//div[@class='hljs-ln-line']"));
            for (WebElement cell : cells) {
                 codeText += cell.getText() + "\n";
            }
        }
        return codeText;
    }

}
