package com.pflb.redButton.pageObjects;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.fail;

public class PerformanceLabMainPage {
    public void hoverOnMenu() {
        $("#mega-menu-item-317 > a:nth-child(1) > span:nth-child(1)").hover();
        $("#mega-menu-item-317 > a:nth-child(1)").shouldHave(attribute("aria-expanded", "true"));
    }

    public void clickAutoTestLink() {
        $("#mega-menu-item-141 > a:nth-child(1)").click();
    }
}