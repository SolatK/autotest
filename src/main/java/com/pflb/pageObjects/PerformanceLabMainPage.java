package com.pflb.pageObjects;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

public class PerformanceLabMainPage {
    public void hoverOnMenu() {
        $("#mega-menu-item-317 > a:nth-child(1) > span:nth-child(1)").hover();
    }

    public void clickAutoTestLink() {
        $("#mega-menu-item-141 > a:nth-child(1)").click();
    }
}