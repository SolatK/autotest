package com.pflb.redButton;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.UIAssertionError;
import com.pflb.pageObjects.GooglePage;
import com.pflb.pageObjects.PerformanceLabAutotestPage;
import com.pflb.pageObjects.PerformanceLabMainPage;
import com.pflb.pageObjects.SearchResultsPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.net.UrlChecker;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class RedButtonTest {
    @DisplayName("Тест на красную кнопку")
    @Test
    public void isButtonRed() {
        WebDriver driver = new FirefoxDriver();
        WebDriverRunner.setWebDriver(driver);

        //step 1
        SelenideElement searchResult = openGoogleAndSearch("Перфоманс лаб");
        searchResult.shouldHave(text("https://www.performance-lab.ru"));

        //step 2
        clickLinkFromGoogle(searchResult, "div>div>div>div>div>div>div>div>span>a");//селектор первой ссылки в гугле

        //step 3
        clickAutotestPage();

        //step 4
        Assertions.assertEquals("rgb(255, 89, 89)", getButtonColor());
    }

    @Step("Search in google.com and check results")
    public SelenideElement openGoogleAndSearch(String needle) {
        open("https://google.com");
        new GooglePage().searchFor(needle);
        return new SearchResultsPage().getResults();
    }

    @Step("Open site and page")
    public void clickLinkFromGoogle(SelenideElement searchResult, String selector) {
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

    @Step("Click autotest link")
    public void clickAutotestPage() {
        PerformanceLabMainPage performanceLabMainPage = new PerformanceLabMainPage();
        performanceLabMainPage.hoverOnMenu();

        //ждем открытия меню перед кликом
        $("#mega-menu-item-317 > a:nth-child(1)").shouldHave(attribute("aria-expanded", "true"));

        performanceLabMainPage.clickAutoTestLink();
    }


    @Step("Get button color")
    public String getButtonColor() {
        PerformanceLabAutotestPage performanceLabAutotestPage = new PerformanceLabAutotestPage();
        return performanceLabAutotestPage.getButtonColor();
    }

    @AfterAll
    public static void closeBrowser() {
        closeWebDriver();
    }
}
