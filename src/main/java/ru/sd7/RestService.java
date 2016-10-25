package ru.sd7;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class RestService {

    @RequestMapping("/list")
    List<SearchResult> list(@RequestParam(value="dir", defaultValue="src") String name,
                            @RequestParam(value="keyword", defaultValue="class") String keyword) {
        return new DirSearcher().search(new SearchParams(name, keyword));
    }

    @RequestMapping("/count")
    int count(@RequestParam(value="dir", defaultValue="src") String name,
                            @RequestParam(value="keyword", defaultValue="class") String keyword) {
        List<SearchResult> list = new DirSearcher().search(new SearchParams(name, keyword));
        return list.size();
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> props = new HashMap<>();
        props.put("server.port", 9999);
        SpringApplication app = new SpringApplication(RestService.class);
        app.setDefaultProperties(props);
        app.run(args);
    }
}
