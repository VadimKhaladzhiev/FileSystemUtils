package ru.sd7;

import org.springframework.boot.SpringApplication;
import ru.sd7.rest.RestService;

public class SpringBoot {
    public static void main(String[] args) throws Exception {
        new SpringApplication(RestService.class).run(args);
    }
}
