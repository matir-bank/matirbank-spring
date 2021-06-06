package xyz.matirbank.spring.utils;

import java.util.Date;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;
import xyz.matirbank.spring.MatirBankApplication;


public class Commons {
    
    public static String getSHA1(String inputData) {
        return DigestUtils.sha1Hex(inputData);
    }
    
    public static String encodePassword(String password_hash) {
        String passwordToken = MatirBankApplication.SystemSalt + password_hash + MatirBankApplication.SystemSalt;
        return getSHA1(passwordToken);
    }
    
    public static String makeIdHash(long id) {
        String passwordToken = MatirBankApplication.SystemSalt + id + "_" + new Date().getTime() + MatirBankApplication.SystemSalt;
        return getSHA1(passwordToken);
    }
    
    protected String getRandomAlphaNumeric(int length) {
        
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder();
        Random rnd = new Random();
        
        while (randomString.length() < length) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            randomString.append(CHARS.charAt(index));
        }
        
        return randomString.toString();
    }
    
}
