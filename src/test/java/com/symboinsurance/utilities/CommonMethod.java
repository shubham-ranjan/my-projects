package com.symboinsurance.utilities;

import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonMethod {

    public void selectFromList(List<WebElement> elementList, String optionToSelect){
        elementList.stream().filter(element-> element.getText().equalsIgnoreCase(optionToSelect))
                .findFirst().get().click();
    }

}
