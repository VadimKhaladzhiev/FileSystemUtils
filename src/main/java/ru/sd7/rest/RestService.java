package ru.sd7.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import ru.sd7.model.User;
import ru.sd7.services.spring.api.SearchService;
import ru.sd7.model.SearchResult;

import java.util.Date;
import java.util.List;

@Configuration
@ImportResource("classpath:applicaitonContext.xml")
@RestController
@EnableAutoConfiguration
public class RestService implements RestServiceI{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SearchService searchService;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("/mongoAdd")
    public boolean mongoAdd(){
        User userA = new User("1000", "apple", 54, new Date());
        mongoTemplate.save(userA, "tableA");
        return true;
    }

    @RequestMapping("/mongo")
    public List<User> mongo(){
        Query findUserQuery = new Query();
        findUserQuery.addCriteria(Criteria.where("ic").is("1000"));
        List<User> list = mongoTemplate.find(findUserQuery, User.class, "tableA");
        return list;
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
