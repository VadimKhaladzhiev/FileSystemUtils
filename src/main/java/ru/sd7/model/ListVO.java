package ru.sd7.model;

import java.io.Serializable;
import java.util.List;

public class ListVO<T> implements Serializable {

    private long count;
    private List<T> results;

    public ListVO() {
    }

    public ListVO(long count, List<T> results) {
        this.count = count;
        this.results = results;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
