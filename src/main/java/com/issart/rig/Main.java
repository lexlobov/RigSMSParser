package com.issart.rig;

import com.issart.rig.parsers.HttpMobileParser;
import com.issart.rig.parsers.HttpStripeParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            if (args[1].equals("stripe")) getStripeCode(args[0]);
            else if (args[1].equals("rig")) getRigCode(args[0]);
            else getRigCode(args[0]);
        } catch (IndexOutOfBoundsException e){
            try {
                getRigCode(args[0]);
            } catch (IndexOutOfBoundsException ex){
                System.out.println("Phone number is needed. Restart program with phone number in args :<");
            }
        }
    }

    private static void getRigCode(String phone) throws IOException {
        HttpMobileParser parser = new HttpMobileParser();
        String jsonResult = parser.getSmsForMobile(phone);
        File file = new File("sms.json");
        try (Writer writer = new FileWriter(file)) {
            writer.write(jsonResult);
        }

    }

    private static void getStripeCode(String phone) throws IOException {
        HttpStripeParser parser = new HttpStripeParser();
        String jsonResult = parser.getStripeCode(phone);
        File file = new File("sms.json");
        try (Writer writer = new FileWriter(file)) {
            writer.write(jsonResult);
        }
    }
}
