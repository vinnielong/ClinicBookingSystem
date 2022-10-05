/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class PasswordEncrypt {

    public String generateEncryptedPassword(String password) throws UnsupportedEncodingException {
        String encryptedpassword = null;

        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");
            /* Chuyển chuỗi văn bản gốc sang mảng các byte*/
            //UTF-8 đảm bảo việc mã hóa các chuỗi kí tự unicode
            byte[] textToBytes = password.getBytes("UTF-8");
            //MessageDigest sẽ mã hóa (hash/băm) mảng byte của văn bản gốc sang mảng byte mới
            byte[] enrTextBytes = m.digest(textToBytes);
            //Mảng byte mã hóa sẽ được chuyển sang 1 chuỗi các số hệ 16(hexa) nhờ lớp biginteger
            BigInteger bigInteger = new BigInteger(1, enrTextBytes);
            
            
            System.out.println(bigInteger.toString());
            /* Complete hashed password in hexadecimal format */
            encryptedpassword = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        return encryptedpassword;
    }

    public static void main(String[] args) {
        PasswordEncrypt obj = new PasswordEncrypt();
        try {
            System.out.println(obj.generateEncryptedPassword("admin"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PasswordEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
