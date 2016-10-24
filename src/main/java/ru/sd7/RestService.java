package ru.sd7;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@EnableAutoConfiguration
public class RestService {
    @RequestMapping("/")
    void home(HttpServletResponse response) {
        try {
            response.getOutputStream().println("Hello World!");
            new DirSearcher().start(new DirSearcher.Params("src", "class", response.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestService.class, args);
    }
}
