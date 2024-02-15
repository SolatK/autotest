package com.pflb.steps;

import com.pflb.pageObjects.PerformanceLabAutotestPage;
import com.pflb.pageObjects.PerformanceLabMainPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

public class PerformanceLabSite {
    @Step("Click autotest link")
    public static void clickAutotestPage() {
        PerformanceLabMainPage performanceLabMainPage = new PerformanceLabMainPage();
        performanceLabMainPage.hoverOnMenu();

        //ждем открытия меню перед кликом
        $("#mega-menu-item-317 > a:nth-child(1)").shouldHave(attribute("aria-expanded", "true"));

        performanceLabMainPage.clickAutoTestLink();
    }

    @Step("Get button color")
    public static String getButtonColor() {
        PerformanceLabAutotestPage performanceLabAutotestPage = new PerformanceLabAutotestPage();
        return performanceLabAutotestPage.getButtonColor();
    }
}
