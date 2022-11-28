package com.issart.rig.parsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Parser {

    protected WebDriver driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true)); //new FirefoxOptions().setHeadless(true)
    protected String linkBuilder(String phoneNumber) {
        String baseUrl = "https://receive-sms.cc/US-Phone-Number/";
        return baseUrl + phoneNumber.replaceAll("\\s", "").replaceAll("[-()+ ]", "");
    }
}
