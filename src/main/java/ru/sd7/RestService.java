package ru.sd7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@ImportResource("classpath:applicaitonContext.xml")
@RestController
@EnableAutoConfiguration
public class RestService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/list")
    List<SearchResult> list(@RequestParam(value="dir", defaultValue="src") String name,
                            @RequestParam(value="keyword", defaultValue="class") String keyword) {
        logger.info("List dir:{} keyword:{}", name, keyword);
        return new DirSearcher().search(new SearchParams(name, keyword));
    }

    @RequestMapping("/count")
    int count(@RequestParam(value="dir", defaultValue="src") String name,
                            @RequestParam(value="keyword", defaultValue="class") String keyword) {
        logger.info("List dir:{} keyword:{}", name, keyword);
        List<SearchResult> list = new DirSearcher().search(new SearchParams(name, keyword));
        return list.size();
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(RestService.class).run(args);
    }
}
