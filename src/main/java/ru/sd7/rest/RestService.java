package ru.sd7.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;
import ru.sd7.services.spring.api.SearchService;
import ru.sd7.model.SearchResult;

import java.util.List;

@Configuration
@ImportResource("classpath:applicaitonContext.xml")
@RestController
@EnableAutoConfiguration
public class RestService implements RestServiceI{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SearchService searchService;

    @RequestMapping("/mongo")
    public String mongo(){
        return "ok";
    }

    @RequestMapping("/list")
    public List<SearchResult> list(@RequestParam(value="dir", defaultValue="src") String name,
                            @RequestParam(value="keyword", defaultValue="class") String keyword) {
        logger.info("List dir:{} keyword:{}", name, keyword);
        return searchService.list(name, keyword);
    }

    @RequestMapping("/count")
    public int count(@RequestParam(value="dir", defaultValue="src") String name,
                            @RequestParam(value="keyword", defaultValue="class") String keyword) {
        logger.info("List dir:{} keyword:{}", name, keyword);
        int count = searchService.count(name, keyword);
        return count;
    }
}
