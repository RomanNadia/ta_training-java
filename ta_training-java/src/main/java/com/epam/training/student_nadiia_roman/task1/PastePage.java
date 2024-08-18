package com.epam.training.student_nadiia_roman.task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PastePage {
    private final WebDriver driver;

    public PastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
