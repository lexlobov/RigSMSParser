import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiveSmsParser {

    @Expose
    private String smsCode;
    @Expose
    private boolean success;

    Gson gson = new Gson();
    WebDriver driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true)); //new FirefoxOptions().setHeadless(true)

    public String linkBuilder(String phoneNumber) {
        String baseUrl = "https://receive-sms.cc/US-Phone-Number/";
        return baseUrl + phoneNumber.replaceAll("\\s", "").replaceAll("[-()+]", "");
    }

    public String getLastSmsCodeForWeb(String phoneNumber) {

        try {
            driver.get(linkBuilder(phoneNumber));
            List<WebElement> messages = driver.findElements(By.xpath("//div[@class='col-xs-12 col-md-8']"));
            smsCode = messages.stream()
                    .filter(m -> m.getAttribute("innerText").contains("web-app.testing.bigrig.app.")).findFirst()
                    .map(m -> m.findElement(By.xpath("span")).getAttribute("data-clipboard-text"))
                    .orElse("null");
        } catch (WebDriverException e) {
            e.printStackTrace();
            driver.quit();
            System.out.println("Need vpn or proxy to connect from Russia");
            return gson.toJson(new ReceiveSmsParser().withSmsCode(null).withSuccess(false));
        }

        if ("null".equals(smsCode)) {
            success = false;
            return gson.toJson(new ReceiveSmsParser().withSmsCode(null).withSuccess(success));
        } else {
            success = true;
            return gson.toJson(new ReceiveSmsParser().withSmsCode(smsCode).withSuccess(success));
        }
    }

    public List<WebElement> getRowsInOrder(String phoneNumber) {//String phoneNumber
        List<WebElement> rows = new ArrayList<>();
        try {
            driver.get(linkBuilder(phoneNumber));
            rows = driver.findElements(By.xpath("//div[contains(@class, 'row') and contains(@class, 'table-hover')]"));
        } catch (WebDriverException e){
            e.printStackTrace();
            driver.quit();
            System.out.println("Need vpn or proxy to connect from Russia");
        }
        driver.quit();
        return rows;
    }

    public String getLastSmsCodeForMobile(String phoneNumber){
        List<WebElement> rows = getRowsInOrder(phoneNumber);
        if(!(rows.isEmpty())){
            try {
                smsCode = rows.stream()
                        .filter(e->e.findElement(By.xpath("/div/div[contains(@class, 'mobile_hide')]")).getText().contains("44398**")).collect(Collectors.toList())
                        .stream()
                        .map(e->e.findElement(By.xpath("//span")).getAttribute("data-clipboard-text")).findFirst().orElse("null");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if ("null".equals(smsCode)) {
            success = false;
            return gson.toJson(new ReceiveSmsParser().withSmsCode(null).withSuccess(success));
        } else {
            success = true;
            return gson.toJson(new ReceiveSmsParser().withSmsCode(smsCode).withSuccess(success));
        }
    }

    public ReceiveSmsParser withSmsCode(String smsCode) {
        this.smsCode = smsCode;
        return this;
    }

    public ReceiveSmsParser withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "ReceiveSmsParser{" +
                "smsCode='" + smsCode + '\'' +
                ", isSuccess=" + success +
                '}';
    }
}
