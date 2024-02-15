package com.pflb.redButton.pageObjects;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {
    //можно использовать для click() из гугла
    public void selectResult(String text) {
        getResults().find(text).click();
    }
    public SelenideElement getResults() {
        return $("html div#search");
    }
}