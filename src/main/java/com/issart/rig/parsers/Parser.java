package com.issart.rig.parsers;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Parser {

    protected String phone;

    public Parser(String phone){
        this.phone = phone;
    }

    public String getCode() throws IOException {
        return "";
    }
    protected String linkBuilder(String phoneNumber) {
        String baseUrl = "https://receive-sms.cc/US-Phone-Number/";
        return baseUrl + phoneNumber.replaceAll("\\s", "").replaceAll("[-()+]", "");
    }

    protected String getHtml(String phone) throws IOException {
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(linkBuilder(phone))
                    .method("GET", null)
                    //.addHeader("Cookie", "SMSSId=aRTlVW5zgg5FMWXwUSH626h6TSsT8RF%2BE07297KzGjr41UVZ3CzcaXMfLanj8Jsg7yZ1gmpCsoa9zs%2BzNAHGjT5PjA")
                    .build();
            response = client.newCall(request).execute();
        } catch (Exception e){
            System.out.println("Wasn't able to reach https://receive-sms.cc/. Probably VPN or Proxy connection is needed");
            return "false";
        }
        if(!response.isSuccessful()){
            return "false";
        } else {
            String html = response.body().string();
            return html;
        }

    }
}
