package ru.sd7.services.spring.api;

import ru.sd7.model.SearchResult;

import java.util.List;

public interface SearchService {
    /**
     * Get list count by keyword in path
     * */
    int count(String path, String keyword);
    /**
     * Get list by keyword in path
     * */
    List<SearchResult> list(String path, String keyword);
}
