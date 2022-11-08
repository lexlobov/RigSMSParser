import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

public class ReceiveSmsParser {

    private String smsCode;
    private boolean isSuccess;
    private final String baseUrl = "https://receive-sms.cc/US-Phone-Number/";

    public String linkBuilder(String phoneNumber) {
        return baseUrl + phoneNumber.replaceAll("\\s", "").replaceAll("[-()+]", "");
    }

    public String getLastSmsCode(String phoneNumber) {

        Gson gson = new Gson();
        String json;

        WebDriver driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true)); //new FirefoxOptions().setHeadless(true)
        driver.get(linkBuilder(phoneNumber));
        List<WebElement> messages = driver.findElements(By.xpath("//div[@class='col-xs-12 col-md-8']"));
        smsCode = messages.stream()
                .filter(m -> m.getAttribute("innerText").contains("web-app.testing.bigrig.app.")).findFirst()
                .map(m -> m.findElement(By.xpath("span")).getAttribute("data-clipboard-text"))
                .orElse("null");
        driver.quit();
        if("null".equals(smsCode)){
            isSuccess = false;
            json = gson.toJson(new ReceiveSmsParser().withSmsCode(null).withSuccess(isSuccess));
            return json;
        } else {
            isSuccess = true;
            json = gson.toJson(new ReceiveSmsParser().withSmsCode(smsCode).withSuccess(isSuccess));
            return json;
        }
    }

    public ReceiveSmsParser withSmsCode(String smsCode) {
        this.smsCode = smsCode;
        return this;
    }

    public ReceiveSmsParser withSuccess(boolean success) {
        this.isSuccess = success;
        return this;
    }
}
