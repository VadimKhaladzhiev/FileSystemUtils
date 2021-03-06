package ru.sd7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootConfiguration
@ImportResource("classpath:applicaitonContext.xml")
@EnableAutoConfiguration
public class SpringBoot {
    public static void main(String[] args) throws Exception {
        new SpringApplication(SpringBoot.class).run(args);
    }
}
