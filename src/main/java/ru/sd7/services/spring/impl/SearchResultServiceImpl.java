package ru.sd7.services.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sd7.model.SearchResult;
import ru.sd7.repository.SearchResultDao;
import ru.sd7.repository.SequenceDao;
import ru.sd7.services.spring.api.SearchResultService;

import java.util.List;

@Service
public class SearchResultServiceImpl implements SearchResultService {

    @Autowired private SequenceDao sequenceDao;
    @Autowired private SearchResultDao searchResultDao;

    public void add(SearchResult item) {
        item.setId(sequenceDao.getNextSequenceId(SearchResult.COLLECTION_NAME));
        searchResultDao.save(item);
    }

    public void insertAll(List<SearchResult> list) {
        for (SearchResult item : list){
            item.setId(sequenceDao.getNextSequenceId(SearchResult.COLLECTION_NAME));
        }
        searchResultDao.insertAll(list);
    }

    public void update(SearchResult item) {
        searchResultDao.save(item);
    }

    public SearchResult get(Long id) {
        return searchResultDao.get(id);
    }

    public List<SearchResult> getLimit(int limit) {
        return searchResultDao.getLimit(limit);
    }

    public List<SearchResult> getAll() {
        return searchResultDao.getAll();
    }

    public void remove(Long id) {
        searchResultDao.remove(id);
    }
}