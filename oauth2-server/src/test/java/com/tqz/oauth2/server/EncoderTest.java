package com.tqz.oauth2.server;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/8/27 13:42
 */
public class EncoderTest {
    
    @Test
    public void testEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String password = "123456";
        String encoderPwd1 = encoder.encode(password);
        String encoderPwd2 = encoder.encode(password);
        
        System.out.println("encoderPwd1:" + encoderPwd1);
        System.out.println("encoderPwd1:" + encoderPwd2);
        
        System.out.println(encoder.matches(password, encoderPwd1));
        System.out.println(encoder.matches(password, encoderPwd2));
    }
}
