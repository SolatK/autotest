package com.pflb.redButton.pageObjects;

import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$;

public class PerformanceLabAutotestPage {
    public void checkButtonIsRed() {
        String buttonColor = $(".elementor-element-33a93a8c > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)")
                .getCssValue("background-color");
        Assertions.assertEquals("rgb(255, 89, 89)", buttonColor);
    }
}
