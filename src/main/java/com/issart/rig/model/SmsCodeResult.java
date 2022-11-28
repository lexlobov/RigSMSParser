package com.issart.rig.model;

public class SmsCodeResult {

    private final boolean success;
    private final String smsCode;

    public SmsCodeResult(boolean success, String smsCode) {
        this.success = success;
        this.smsCode = smsCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getSmsCode() {
        return smsCode;
    }

    @Override
    public String toString() {
        return "{" +
                "\"smsCode\": " + ((smsCode == null) ? "null" : "\"" + smsCode + "\"") +
                ", \"success\": " + success +
                '}';
    }
}
