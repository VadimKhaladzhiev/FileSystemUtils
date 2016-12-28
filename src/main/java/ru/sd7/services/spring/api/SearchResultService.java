package ru.sd7.services.spring.api;

import ru.sd7.model.SearchResult;

import java.util.List;

public interface SearchResultService {

    void add(SearchResult item);

    void insertAll(List<SearchResult> list);

    void update(SearchResult item);

    SearchResult get(Long id);

    List<SearchResult> getLimit(int limit);

    List<SearchResult> getAll();

    void remove(Long id);
}