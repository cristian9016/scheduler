package com.example.personal.scheduleremployee.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppTool {
    private static final String APP_KEY = "S0M0S";

    public static String encodeSHA512(String cad) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(APP_KEY.getBytes("UTF-8"));
            byte[] bytes = md.digest(cad.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();

            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
