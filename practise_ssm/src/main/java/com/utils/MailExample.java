package com.utils;

/**
 * Created by wly on 2018/3/7.
 */

public class MailExample {

    public static void main (String args[]) throws Exception {
        String email = "1061607576@qq.com";
        String validateCode = "";
        SendEmail.sendEmailMessage(email,validateCode);

    }
}
