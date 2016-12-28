package ru.sd7.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sd7.model.SearchResult;
import ru.sd7.services.spring.api.SearchResultService;

import java.util.List;

@RestController
@RequestMapping("/search_results")
public class SearchResultRestService {

    @Autowired
    private SearchResultService searchResultService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<SearchResult> showAll() {
        return searchResultService.getAll();
    }

    @RequestMapping(value = "/limit", method = RequestMethod.GET)
    public List<SearchResult> showLimit() {
        return searchResultService.getLimit(100);
    }

    @RequestMapping(value = "/by_id", method = RequestMethod.GET)
    public SearchResult getById(@RequestParam(required = true) Long id) {
        return searchResultService.get(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean add(SearchResult item) {
        if(item.getId() == null) searchResultService.add(item);
        else searchResultService.update(item);

        return true;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean deleteContact(@RequestParam(required = true) Long id) {
        searchResultService.remove(id);
        return true;
    }
}
