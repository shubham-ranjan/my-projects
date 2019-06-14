package com.symboinsurance.pages;

import com.symboinsurance.utilities.CommonMethod;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TravelInputDataPage {

    WebDriver driver;
    CommonMethod commonMethod = new CommonMethod();
    enum tripType {MULTI,SINGLE}
    Actions actionBuilder;
    WebDriverWait wait;

    @FindBy(xpath = "//div[text()='You are travelling?']/following::button[1]")
    WebElement travelTypeDropDown;

    @FindBy(xpath = "//div[text()='You are travelling?']/parent::div//li")
    List<WebElement> travelTypeList;

    @FindBy(xpath = "//li/div/input")
    WebElement destinationCountry;

    @FindBy(xpath = "//ul[@role='menu']//li")
    List<WebElement> countryList;

    @FindBy(xpath = "//span[text()='Single Trip']")
    WebElement singleTripType;

    @FindBy(xpath = "//span[text()='Multi Trip']")
    WebElement multiTripType;

    @FindBy(className = "react-datepicker__month-select")
    WebElement monthSelectOption;

    @FindBy(className = "react-datepicker__year-select")
    WebElement yearSelectOption;

    @FindBy(xpath = "//div[@class='react-datepicker__month']/div/div[not(contains(@class,'outside'))]")
    List<WebElement> dates;

    @FindBy(xpath = "//div[text()='Journey starts on']/following::i")
    WebElement journeyStartDate;

    @FindBy(xpath = "//div[text()='Journey ends on']/following::i")
    WebElement journeyEndDate;

    @FindBy(name = "email")
    WebElement emailInputTextBox;

    @FindBy(name = "phone")
    WebElement phoneNumberInputTextBox;

    @FindBy(xpath = "//button//span[text()='Next']")
    WebElement nextButton;

    @FindBy(xpath = "(//div[text()='1.']//following-sibling::div//button)[1]")
    WebElement familyMemberOption;

    @FindBy(xpath = "//div[text()='1.']//following-sibling::div//li")
    List<WebElement> familyMemberOptionList;

    @FindBy(xpath = "(//div[text()='1.']//following-sibling::div//button)[2]")
    WebElement familyMemberAgeValue;

    @FindBy(xpath = "//div[text()='1.']//following-sibling::div//li")
    List<WebElement> familyMemberAgeValueList;


    public TravelInputDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        actionBuilder = new Actions(driver);
        wait = new WebDriverWait(driver,60);
    }

    public void setTravelType(String travelTypeName) {
        travelTypeDropDown.click();
        commonMethod.selectFromList(travelTypeList,travelTypeName);
    }

    public void setDestinationCountry(String destinationCountryName) {
        destinationCountry.sendKeys(destinationCountryName);
        commonMethod.selectFromList(countryList,destinationCountryName);
        //destinationCountry.click();
        actionBuilder.moveToElement(destinationCountry).sendKeys(destinationCountry, Keys.ESCAPE).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTripType(tripType type) {
        switch (type) {
            case SINGLE :
                wait.until(ExpectedConditions.visibilityOf(singleTripType)).click();
                break;
            case MULTI:
                wait.until(ExpectedConditions.visibilityOf(multiTripType)).click();
                break;
        }
    }

    public void selectDate(Date inputDate){
        SimpleDateFormat simpleDateFormatFull = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("d");
        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("MMMMM");
        SimpleDateFormat simpleDateFormatYear = new SimpleDateFormat("yyyy");
        try {
            Date currentDate = new Date();
            if(currentDate.before(inputDate)){
                Select year = new Select(yearSelectOption);
                year.selectByVisibleText(simpleDateFormatYear.format(inputDate));
                Select month = new Select(monthSelectOption);
                month.selectByVisibleText(simpleDateFormatMonth.format(inputDate));
                commonMethod.selectFromList(dates,simpleDateFormatDay.format(inputDate));
            } else {
                Assert.fail("Journey date can not be before current date");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setJourneyStartDate(Date date){
        journeyStartDate.click();
        selectDate(date);
    }

    public void setJourneyEndDate(Date date){
        journeyEndDate.click();
        selectDate(date);
    }

    public void setEmailAddress(String emailAddress){
        emailInputTextBox.sendKeys(emailAddress);
    }

    public void setPhoneNumber(String phoneNumber){
        phoneNumberInputTextBox.sendKeys(phoneNumber);
    }

    public void clickNextButton(){
        nextButton.click();
    }

    public void setFamilyMember(String familyMember){
        familyMemberOption.click();
        commonMethod.selectFromList(familyMemberOptionList,familyMember);
    }

    public void setFamilyMemberAge(String familyMemberAge) {
        familyMemberAgeValue.click();
        commonMethod.selectFromList(familyMemberAgeValueList, familyMemberAge);
    }

    public void fillFormDetails(){
        String travelType = "Individually";
        String destinationCountry = "Afghanistan";
        String startDate = "26/06/2019";
        String endDate = "10/07/2019";
        Date jStartDate = null, jEndDate = null;
        SimpleDateFormat simpleDateFormatFull = new SimpleDateFormat("dd/MM/yyyy");
        String emailAddress = "dummay@domain.com";
        String phoneNumber = "9874563210";

        String familyMemberOption = "Your age";
        String familyMemberAge = "21 years";

        try {
            jStartDate = simpleDateFormatFull.parse(startDate);
            jEndDate = simpleDateFormatFull.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        this.setTravelType(travelType);
        this.setDestinationCountry(destinationCountry);
        this.setTripType(tripType.SINGLE);
        if(jStartDate.before(jEndDate)) {
            this.setJourneyStartDate(jStartDate);
            this.setJourneyEndDate(jEndDate);
            this.setEmailAddress(emailAddress);
            this.setPhoneNumber(phoneNumber);
            this.clickNextButton();
            this.setFamilyMember(familyMemberOption);
            this.setFamilyMemberAge(familyMemberAge);
            clickNextButton();
        } else {
            Assert.fail("Journey start date can not be after end date.");
        }

    }
}
