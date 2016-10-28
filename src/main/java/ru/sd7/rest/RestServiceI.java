package ru.sd7.rest;

import ru.sd7.model.SearchResult;

import java.util.List;

public interface RestServiceI{
    List<SearchResult> list(String name, String keyword);
    int count(String name, String keyword);
}
