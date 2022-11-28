package com.issart.rig.parsers;

import com.issart.rig.model.SmsCodeResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReceiveForWeb extends Parser {

    public String getLastSmsCodeForWeb(String phoneNumber) {


        try {
            driver.get(linkBuilder(phoneNumber));
            List<WebElement> messages = driver.findElements(By.xpath("//div[@class='col-xs-12 col-md-8']"));
            String code = messages.stream()
                    .filter(m -> m.getAttribute("innerText").contains("web-app.testing.bigrig.app.")).findFirst()
                    .map(m -> m.findElement(By.xpath("span")).getAttribute("data-clipboard-text"))
                    .orElse(null);
            return new SmsCodeResult(code != null, code).toString();
        } catch (WebDriverException e) {
            e.printStackTrace();
            System.out.println("Need vpn or proxy to connect from Russia");
            return new SmsCodeResult(false, null).toString();
        } finally {
            driver.quit();
        }

    }

}
