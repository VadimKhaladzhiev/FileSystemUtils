package ru.sd7.services.spring.api;

import org.springframework.data.domain.Pageable;
import ru.sd7.model.ListVO;
import ru.sd7.model.SearchResult;

import java.util.List;

public interface SearchResultService {

    void add(SearchResult item);

    void insertAll(List<SearchResult> list);

    void update(SearchResult item);

    SearchResult get(Long id);

    ListVO<SearchResult> getLimit(Pageable page);

    List<SearchResult> getAll();

    void remove(Long id);
}