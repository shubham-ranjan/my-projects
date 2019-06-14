package com.symboinsurance.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath = "//div[text()='Travel']/ancestor::div[2]/div[2]//a[text()='View Plans']")
    WebElement travelPlans;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickTravelViewPlanLink(){
        travelPlans.click();
    }
}
