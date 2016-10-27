package ru.sd7.services.spring.impl;

import org.springframework.stereotype.Service;
import ru.sd7.core.DirSearcher;
import ru.sd7.model.SearchParams;
import ru.sd7.model.SearchResult;
import ru.sd7.services.spring.api.SearchService;

import java.util.List;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

    public List<SearchResult> list(String path, String keyword){
        return searchList(path, keyword);
    }

    public int count(String path, String keyword){
        List<SearchResult> list = searchList(path, keyword);
        return list.size();
    }

    private List<SearchResult> searchList(String path, String keyword) {
        return new DirSearcher().search(new SearchParams(path, keyword));
    }

}
