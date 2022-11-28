package com.issart.rig.parsers;

import com.issart.rig.model.SmsCodeResult;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class HttpParser{

    protected String linkBuilder(String phoneNumber) {
        String baseUrl = "https://receive-sms.cc/US-Phone-Number/";
        return baseUrl + phoneNumber.replaceAll("\\s", "").replaceAll("[-()+ ]", "");
    }


    public String getHtml(String phone) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(linkBuilder(phone))
                .method("GET", null)
                .addHeader("Cookie", "SMSSId=aRTlVW5zgg5FMWXwUSH626h6TSsT8RF%2BE07297KzGjr41UVZ3CzcaXMfLanj8Jsg7yZ1gmpCsoa9zs%2BzNAHGjT5PjA")
                .build();
        Response response = client.newCall(request).execute();

        String html = response.body().string();
        return html;
    }
    public String getSmsForMobile(String phone) throws IOException {
        Document doc = Jsoup.parse(getHtml(phone));
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
