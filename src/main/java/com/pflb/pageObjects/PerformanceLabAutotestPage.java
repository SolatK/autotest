package com.pflb.pageObjects;

import static com.codeborne.selenide.Selenide.$;

public class PerformanceLabAutotestPage {
    public String getButtonColor() {
        String buttonColor = $(".elementor-element-33a93a8c > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)")
                .getCssValue("background-color");
        return buttonColor;
    }
}
