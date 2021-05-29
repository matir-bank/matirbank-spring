package xyz.matirbank.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatirBankApplication {
    
    public static final String SystemSalt = "S0m3S@1T";
    
    // https://www.toptal.com/spring/spring-boot-oauth2-jwt-rest-protection

    public static void main(String[] args) {
	SpringApplication.run(MatirBankApplication.class, args);
    }

}
