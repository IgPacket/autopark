package ru.ustinov.autopark;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AutoparkApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoparkApplication.class, args);
        log.info("Database URL= {}", "jdbc:postgresql://localhost:5432/autopark");
        log.info("Application URI's:");
        log.info("http://localhost:8080/cars");
        log.info("http://localhost:8080/bikes");
        log.info("http://localhost:8080/brands");
        log.info("http://localhost:8080/engines");
    }
}
