package com.pflb.steps;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import com.pflb.pageObjects.GooglePage;
import com.pflb.pageObjects.SearchResultsPage;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

public class GoogleSearch {
    @Step("Search in google.com and check results")
    public static SelenideElement openGoogleAndSearch(String needle) {
        open("https://google.com");
        new GooglePage().searchFor(needle);
        return new SearchResultsPage().getResults();
    }

    @Step("Open site and page")
    public static void clickLinkFromGoogle(SelenideElement searchResult, String selector) {
        try {
            searchResult
                    .find(selector)
                    .click(
                            ClickOptions.withTimeout(Duration.ofSeconds(2))//таймаут так как страница никогда не загрузится
                    );
        } catch (UIAssertionError ignore) {
            //инор таймаута так как страница не грузится из-за мусора
        }
    }
}