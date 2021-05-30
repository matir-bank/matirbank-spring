/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.matirbank.spring.utils;

import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import xyz.matirbank.spring.MatirBankApplication;

/**
 *
 * @author User
 */
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
    
}
