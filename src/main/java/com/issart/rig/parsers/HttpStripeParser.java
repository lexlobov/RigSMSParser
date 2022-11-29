package com.issart.rig.parsers;

import com.issart.rig.model.SmsCodeResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class HttpStripeParser extends Parser{


    public String getStripeCode(String phone) throws IOException {

        String html = getHtml(phone);
        if (html.equals("false")){
            System.out.println("Request wasn't successful");
            return new SmsCodeResult(false, null).toString();
        }
        Document doc = Jsoup.parse(html);
        List<Element> rows = doc.selectXpath("//div[@class='col-xs-12 col-md-8']");

        String code = rows.stream()
                .filter(m -> m.text().contains("Your Stripe verification code is")).findFirst()
                .map(m -> m.selectXpath("span").attr("data-clipboard-text"))
                .orElse(null);
        System.out.println(code);
        return new SmsCodeResult(code !=null, code).toString();
    }
}
