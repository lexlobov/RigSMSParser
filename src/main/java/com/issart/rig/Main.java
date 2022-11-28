package com.issart.rig;

import com.issart.rig.parsers.HttpParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

    public static void main(String[] args) throws IOException {

        HttpParser parser = new HttpParser();
        //String jsonResult = parser.getSmsForMobile(args[0]);
        String jsonResult = parser.getSmsForMobile("+17602781253");
        String phone = "+17602781253";
        File file = new File("sms.json");
        try(Writer writer = new FileWriter(file)) {
            writer.write(jsonResult);
        }


    }

//    public void run(){
//        ReceiveForMobile parser = new ReceiveForMobile();
//        String jsonResult = parser.getLastSmsCodeMobile(args[0]);
////        String jsonResult = parser.getLastSmsCodeMobile("+17602781253");
//        File file = new File("sms.json");
//        try(Writer writer = new FileWriter(file)) {
//            writer.write(jsonResult);
//        }
//    }
}
