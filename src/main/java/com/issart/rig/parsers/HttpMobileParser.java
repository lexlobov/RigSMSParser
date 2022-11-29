package com.issart.rig.parsers;

import com.issart.rig.model.SmsCodeResult;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class HttpMobileParser extends Parser {

    public String getSmsForMobile(String phone) throws IOException {
        String html = getHtml(phone);
        if (html.equals("false")){
            System.out.println("Request wasn't successful");
            return new SmsCodeResult(false, null).toString();
        }
        Document doc = Jsoup.parse(html);
        List<Element> elementList = doc.select(".table-hover");

        String code = null;
        for (Element e : elementList) {
            String sender = e.selectXpath("div/div[contains(@class, 'mobile_hide')]").text();
            if(sender.contains("44398**")){
                code = e.selectXpath("div/span").attr("data-clipboard-text");
                System.out.println(code);
                break;
            }
        }
        return new SmsCodeResult(code != null, code).toString();
    }

}
