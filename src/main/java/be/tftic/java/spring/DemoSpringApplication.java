package be.tftic.java.spring;

import be.tftic.java.spring.domain.models.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class DemoSpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoSpringApplication.class, args);
    }

}
