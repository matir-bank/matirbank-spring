package xyz.matirbank.spring.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.matirbank.spring.utils.Commons;

public class SaltedPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence cs) {
        return Commons.encodePassword(cs.toString());
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        return Commons.encodePassword(cs.toString()).equals(string);
    }

}
