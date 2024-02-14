package com.pflb.redButton;

import dev.failsafe.internal.util.Assert;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.fail;

public class PerfomanceLabPage {
    public void hoverOnMenu() {
        $("#mega-menu-item-317 > a:nth-child(1) > span:nth-child(1)").hover();
        $("#mega-menu-item-317 > a:nth-child(1)").shouldHave(attribute("aria-expanded", "true"));
    }

    public void clickAutoTestLink() {
        $("#mega-menu-item-141 > a:nth-child(1)").click();
    }

    public void checkButtonIsRed() {
        $(".elementor-element-33a93a8c > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)")
                .shouldHave(cssValue("background-color", "rgb(255, 89, 89)"));
    }
}