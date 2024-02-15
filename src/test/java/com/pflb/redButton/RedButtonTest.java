package com.pflb.redButton;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.pflb.steps.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class RedButtonTest {
    @DisplayName("Тест на красную кнопку")
    @Test
    public void isButtonRed() {
        WebDriver driver = new FirefoxDriver();
        WebDriverRunner.setWebDriver(driver);

        //step 1
        SelenideElement searchResult = GoogleSearch.openGoogleAndSearch("Перфоманс лаб");
        searchResult.shouldHave(text("https://www.performance-lab.ru"));

        //step 2
        GoogleSearch.clickLinkFromGoogle(searchResult, "a");//селектор первой ссылки в гугле

        //step 3
        PerformanceLabSite.clickAutotestPage();

        //step 4
        Assertions.assertEquals("rgb(255, 89, 89)", PerformanceLabSite.getButtonColor());
    }

    @AfterAll
    public static void closeBrowser() {
        closeWebDriver();
    }
}
