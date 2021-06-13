package xyz.matirbank.spring.utils;

import java.util.Date;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import xyz.matirbank.spring.models.entities.BaseEntity;

public class Commons {

    @Value("${system.salt}")
    private static String systemSalt;

    public static String getSystemSalt() {
        return systemSalt;
    }

    public static String getSHA1(String inputData) {
        return DigestUtils.sha1Hex(inputData);
    }

    public static String encodePassword(String password_hash) {
        String passwordToken = getSystemSalt() + password_hash + getSystemSalt();
        return getSHA1(passwordToken);
    }

    public static String makeIdHash(long id) {
        String passwordToken = getSystemSalt() + id + "_" + new Date().getTime() + getSystemSalt();
        return getSHA1(passwordToken);
    }

    public static String makeRandomHash() {
        Random rand = new Random();
        String token = getSystemSalt() + rand.longs(0, 999999999).toString() + "_" + rand.longs(0, 999999999).toString() + new Date().getTime() + getSystemSalt();
        return getSHA1(token);
    }

    public static String getRandomAlphaNumeric(int length) {
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder();
        Random rnd = new Random();

        while (randomString.length() < length) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            randomString.append(CHARS.charAt(index));
        }

        return randomString.toString();
    }

    public static void createEntity(BaseEntity baseEntity) {
        baseEntity.setDate_created(new Date());
        baseEntity.setDate_updated(new Date());
        baseEntity.setIs_active(Boolean.TRUE);
        baseEntity.setIs_deleted(Boolean.FALSE);
    }

    public static void updateEntityDate(BaseEntity baseEntity) {
        baseEntity.setDate_updated(new Date());
    }

}
