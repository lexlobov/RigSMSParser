package com.issart.rig.parsers;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Parser {
    protected String linkBuilder(String phoneNumber) {
        String baseUrl = "https://receive-sms.cc/US-Phone-Number/";
        return baseUrl + phoneNumber.replaceAll("\\s", "").replaceAll("[-()+ ]", "");
    }

    protected String getHtml(String phone) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(linkBuilder(phone))
                .method("GET", null)
                .addHeader("Cookie", "SMSSId=aRTlVW5zgg5FMWXwUSH626h6TSsT8RF%2BE07297KzGjr41UVZ3CzcaXMfLanj8Jsg7yZ1gmpCsoa9zs%2BzNAHGjT5PjA")
                .build();
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()){
            return "false";
        } else {
            String html = response.body().string();
            return html;
        }

    }
}
