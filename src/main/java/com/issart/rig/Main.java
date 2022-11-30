package com.issart.rig;

import com.issart.rig.parsers.HttpMobileParser;
import com.issart.rig.parsers.HttpStripeParser;
import com.issart.rig.parsers.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

    public static void main(String[] args) throws IOException {

        try {
            String number = args[0];
            String service = args[1];
            switch (service) {
                case "stripe": {
                    getCode(new HttpStripeParser(number));
                    break;
                }
                case "rig": {
                    getCode(new HttpMobileParser(number));
                    break;
                }
                default: {
                    getCode(new HttpMobileParser(number));
                }
            }
        } catch (IndexOutOfBoundsException e){
            try {
                getCode(new HttpMobileParser(args[0]));
            } catch (IndexOutOfBoundsException ex){
                System.out.println("Phone number is needed. Restart program with phone number in args :<");
            }
        }
    }

    private static void getCode(Parser parser) throws IOException {
        String jsonResult = parser.getCode();
        File file = new File("sms.json");
        try (Writer writer = new FileWriter(file)) {
            writer.write(jsonResult);
        }
    }
}
