package ru.sd7.model;

public class SearchParams {
    private String dir;
    private String keyword;

    public SearchParams() {
    }

    public SearchParams(String dir, String keyword) {
        this.dir = dir;
        this.keyword = keyword;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
