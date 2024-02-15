package com.pflb.redButton;

import com.codeborne.selenide.WebDriverRunner;
import com.pflb.pageObjects.GooglePage;
import com.pflb.pageObjects.PerformanceLabAutotestPage;
import com.pflb.pageObjects.PerformanceLabMainPage;
import com.pflb.pageObjects.SearchResultsPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RedButtonTest {
    @Test
    public void isButtonRed() {
        WebDriver driver = new FirefoxDriver();
        WebDriverRunner.setWebDriver(driver);

        //step 1
        openGoogleAndSearch();

        driver
                .manage()
                .timeouts()
                .pageLoadTimeout(2, TimeUnit.SECONDS);//страница никогда не загрузится, нужен таймаут

        //step 2
        openPerformanceLabPage();

        driver
                .manage()
                .timeouts()
                .pageLoadTimeout(30, TimeUnit.SECONDS);//30 секунд обратно

        //step 3
        checkButtonColor();
    }

    @Step("Step 1. Search in google.com and check results")
    public void openGoogleAndSearch() {
        open("https://google.com");

        new GooglePage().searchFor("перфоманс лаб");
        SearchResultsPage results = new SearchResultsPage();

        results
                .getResults()
                .shouldHave(text("https://www.performance-lab.ru"));
    }

    @Step("Step 2. Open site and page")
    public void openPerformanceLabPage() {
        try {
            open("https://www.performance-lab.ru/");//с click() так почему-то не работает
        } catch (TimeoutException ignore) {
            //инор таймаута так как страница не грузится из-за мусора
        }

        PerformanceLabMainPage performanceLabMainPage = new PerformanceLabMainPage();
        performanceLabMainPage.hoverOnMenu();

        //открылось ли выпадающее меню
        $("#mega-menu-item-317 > a:nth-child(1)").shouldHave(attribute("aria-expanded", "true"));

        performanceLabMainPage.clickAutoTestLink();
    }

    @Step("Step 3. Check button color")
    public void checkButtonColor() {
        PerformanceLabAutotestPage performanceLabAutotestPage = new PerformanceLabAutotestPage();
        String buttonColor = performanceLabAutotestPage.getButtonColor();
        Assertions.assertEquals("rgb(255, 89, 89)", buttonColor);
    }

    @AfterAll
    public static void closeBrowser() {
        closeWebDriver();
    }
}
