package com.issart.rig.parsers;

import com.issart.rig.model.SmsCodeResult;
import org.openqa.selenium.*;
import java.util.List;

public class ReceiveForMobile extends Parser {

    public String getLastSmsCodeMobile(String phoneNumber){

        String code = null;
        try {
            driver.get(linkBuilder(phoneNumber));
            List<WebElement> rows = driver.findElements(By.xpath("//div[contains(@class, 'row') and contains(@class, 'table-hover')]"));
            for (WebElement element : rows){
                try {
                    String text = element.findElement(By.xpath("div/div[contains(@class, 'mobile_hide')]")).getText();
                    if(text.contains("44398**")){
                        code = element.findElement(By.xpath("div/span")).getAttribute("data-clipboard-text");
                        break;
                    }
                } catch (NoSuchElementException e){
                }
            }

        } catch (WebDriverException e){
            e.printStackTrace();
            System.out.println("Need vpn or proxy to connect from Russia");
            return new SmsCodeResult(false, null).toString();
        } finally {
            driver.quit();
        }

        return new SmsCodeResult(code != null, code).toString();
    }

}
