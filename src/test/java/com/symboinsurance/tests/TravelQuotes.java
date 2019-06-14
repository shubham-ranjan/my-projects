package com.symboinsurance.tests;

import com.symboinsurance.pages.HomePage;
import com.symboinsurance.pages.TravelInputDataPage;
import com.symboinsurance.pages.TravelQuotesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TravelQuotes {

    WebDriver driver;
    HomePage homePage;
    TravelInputDataPage travelInputDataPage;
    TravelQuotesPage travelQuotesPage;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.symboinsurance.com/");
    }

    @Test
    public void getTravelQuotes(){
        homePage = new HomePage(driver);
        travelInputDataPage = new TravelInputDataPage(driver);
        travelQuotesPage = new TravelQuotesPage(driver);
        homePage.clickTravelViewPlanLink();
        travelInputDataPage.fillFormDetails();
        travelQuotesPage.displayInsuranceDetails();
    }

    @AfterTest
    public void teardown(){
        if(driver!=null)
            driver.quit();
    }
}
