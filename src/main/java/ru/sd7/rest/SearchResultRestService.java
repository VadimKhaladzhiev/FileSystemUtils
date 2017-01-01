package ru.sd7.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.sd7.model.ListVO;
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
    public ListVO<SearchResult> showDefaultLimit(
            @RequestParam(required = true, defaultValue = "0")  int page,
            @RequestParam(required = true, defaultValue = "10")  int limit
    ) {
        return searchResultService.getLimit(new PageRequest(page, limit));
    }

    @RequestMapping(value = "/limit/{limit}", method = RequestMethod.GET)
    public ListVO<SearchResult> showLimit(@PathVariable int limit) {
        return searchResultService.getLimit(new PageRequest(0, limit));
    }

    @RequestMapping(value = "/by_id/{id}", method = RequestMethod.GET)
    public SearchResult getById(@PathVariable Long id) {
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
