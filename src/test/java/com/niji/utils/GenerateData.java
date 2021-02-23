package com.niji.utils;

public class GenerateData {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase();

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;

    public static String generateRandomMail(){
        String mail = "";
        for (int i = 0; i < 8; i++){
            int random = (int)(Math.random() * alphanum.length()-1);
            mail = mail + alphanum.substring(random, random+1);
        }
        mail = mail + "@getnada.com";
        return mail;
    }
}
