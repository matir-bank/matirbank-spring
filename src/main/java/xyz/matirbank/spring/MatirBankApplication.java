package xyz.matirbank.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.matirbank.spring.migrations.InitialMigration;

@SpringBootApplication
public class MatirBankApplication {
    
    
    public static void main(String[] args) {
	SpringApplication.run(MatirBankApplication.class, args);
        
        // Run Migrations
        InitialMigration.migrate();
    }

}
