package com.pflb.redButton;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RedButtonTest {
    @Test
    public void userCanSearch() {
        WebDriver driver = new FirefoxDriver();
        WebDriverRunner.setWebDriver(driver);
        open("https://google.com");
        new GooglePage().searchFor("перфоманс лаб");

        SearchResultsPage results = new SearchResultsPage();

        results.getResults().shouldHave(text("https://www.performance-lab.ru"));



        try {
            driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);//страница никогда не загрузится, нужен таймаут
            open("https://www.performance-lab.ru/");//с click() так почему-то не работает
        } catch (TimeoutException ignore) {
            //инор таймаута так как страница не грузится из-за мусора
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);//30 секунд обратно
        }

        PerfomanceLabPage perfomanceLabPage = new PerfomanceLabPage();

        perfomanceLabPage.hoverOnMenu();
        perfomanceLabPage.clickAutoTestLink();
        perfomanceLabPage.checkButtonIsRed();
        closeWindow();
    }
}
