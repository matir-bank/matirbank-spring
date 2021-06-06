package xyz.matirbank.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatirBankApplication {
    
    public static final String SystemSalt = "S0m3S@1T";
    
    public static void main(String[] args) {
	SpringApplication.run(MatirBankApplication.class, args);
    }

}
