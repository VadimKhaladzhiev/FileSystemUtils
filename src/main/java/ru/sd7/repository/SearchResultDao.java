package ru.sd7.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.sd7.model.SearchResult;

import java.util.List;

@Repository
public class SearchResultDao {

    @Autowired
    private MongoOperations mongoOperations;

    public void save(SearchResult item) {
        mongoOperations.save(item);
    }

    public void insertAll(List<SearchResult> list) {
        mongoOperations.insertAll(list);
    }

    public SearchResult get(Long id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), SearchResult.class);
    }

    public List<SearchResult> getAll() {
        return mongoOperations.findAll(SearchResult.class);
    }

    public List<SearchResult> getLimit(int limit) {
        return mongoOperations.find(new Query().limit(limit), SearchResult.class);
    }

    public void remove(Long id) {
        mongoOperations.remove(Query.query(Criteria.where("id").is(id)), SearchResult.class);
    }
}
