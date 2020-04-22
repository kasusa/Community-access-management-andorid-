package com.kasusa.communityaccessmanagement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailR {


    public static boolean isEmail(String emailaddress) {

        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(emailaddress);
        return matcher.matches();
    }
}
