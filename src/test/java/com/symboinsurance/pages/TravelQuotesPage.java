package com.symboinsurance.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class TravelQuotesPage {
    WebDriver driver;

    @FindBy(xpath = "//a[text()='Sum insured']/parent::div/following-sibling::div//button")
    WebElement sumInsuredButton;

    @FindBy(xpath = "//a[text()='Sum insured']/parent::div/following-sibling::div//li")
    List<WebElement> sumInsuredList;

    @FindBy(xpath = "//div[@class='quote-card']//img")
    List<WebElement> insuranceProviderNameList;

    @FindBy(xpath = "//div[@class='quote-card']//p")
    List<WebElement> insurancePlanNameList;

    @FindBy(xpath = "//div[@class='quote-card']//div[text()='Medical Coverage']/span")
    List<WebElement> medicalCoverageAmountList;

    @FindBy(xpath = "//div[@class='quote-card']//div[text()='Premium']/span")
    List<WebElement> premiumAmountList;



    public TravelQuotesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public List<Map<String, String>> getInsurancePlanDetails(String medicalCoverageAmount){
        Map<String, String> insurancePlanDetail ;//= new HashMap<>();
        List<Map<String, String>>  insurancePlanDetails = new ArrayList<>();
        for(int i = medicalCoverageAmountList.size();i-->0;) {
            insurancePlanDetail = new LinkedHashMap<>();
            if (medicalCoverageAmountList.get(i).getText().equals(medicalCoverageAmount)) {
                insurancePlanDetail.put("Insurer Name",insuranceProviderNameList.get(i).getAttribute("src").substring(38).replaceFirst(".png",""));
                insurancePlanDetail.put("Plan name",insurancePlanNameList.get(i).getText());
                insurancePlanDetail.put("Premium Amount", premiumAmountList.get(i).getText().replace("\\₹","\u20B9"));
                //insurancePlanDetail.put("Coverage", medicalCoverageAmountList.get(i).getText());
                insurancePlanDetails.add(insurancePlanDetail);
            }
        }
        return insurancePlanDetails;
    }

    public void displayInsuranceDetails(){
        this.getInsurancePlanDetails("$ 50,000").forEach(insurancePlanDetail -> {
            insurancePlanDetail.forEach((key,value) ->
                        System.out.print(key+" = "+value+"\t"));
            System.out.println();});
    }
}
