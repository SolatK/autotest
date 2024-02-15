package com.pflb.pageObjects;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {
    public SelenideElement getResults() {
        return $("html div#search");
    }
}