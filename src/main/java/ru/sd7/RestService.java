package ru.sd7;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@EnableAutoConfiguration
public class RestService {
    @RequestMapping("/")
    void home(@RequestParam(value="dir", defaultValue="src") String name,
              @RequestParam(value="keyword", defaultValue="class") String keyword, HttpServletResponse response) {
        try {
            response.getOutputStream().println("Hello World!");
            new DirSearcher().start(new DirSearcher.Params(name, keyword, response.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> props = new HashMap<>();
        props.put("server.port", 9999);
        SpringApplication app = new SpringApplication(RestService.class);
        app.setDefaultProperties(props);
        app.run(args);
    }
}
