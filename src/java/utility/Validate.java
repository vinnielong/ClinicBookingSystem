/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class Validate {

    /**
     *
     * @param phone
     * @return
     */
    public boolean checkPhone(String phone) {
        // \\d{10,12} at least 10 digit, max 12 digit
        String regex = "\\d{10,12}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean checkPassword(String pass) {
        //Password requires at least 8 characters, including at least 1 digit, 1 uppercase letter, 1 lowercase letter and no spaces!
        //(?=.*\\d) at least 1 digit, (?=.*[a-z]) contain at least 1 lowercase letter, 
        //(?=.*[A-Z]) contain at least 1 uppercase letter, (?!.* ) no space, {8,} 8 character or more
        //(?=.*[a-zA-Z]) match any upper case or lower case letter
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?!.* ).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public int checkDate(String date) {
        String now = java.time.LocalDate.now().toString();
        return date.compareTo(now);
    }
    
    public boolean checkEmail(String email){
        //[\\w-\\.] \\w matches any word charater(alphanumreic & underscore) include - and . character
        //+@ phai la @ cho ki tu tiep theo, {2,4} tu 2 den 4 chu cai
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Validate validate = new Validate();
        System.out.println(validate.checkPassword("Ihope123 "));
        System.out.println(validate.checkPhone("0123456789111"));
        System.out.println(validate.checkEmail("guzhinatra@gm.d"));
    }
}
