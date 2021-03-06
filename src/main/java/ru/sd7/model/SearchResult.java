package ru.sd7.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = SearchResult.COLLECTION_NAME)
public class SearchResult implements Serializable {
    public static final String COLLECTION_NAME = "searchResult";

    @Id
    private Long id;

    private String threadName;
    private String filePath;
    private Integer lineNumber;
    private String line;

    public SearchResult() {
    }

    public SearchResult(String threadName, String filePath, Integer lineNumber, String line) {
        this.threadName = threadName;
        this.filePath = filePath;
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return String.format("%12s:   %s:%d:%s%n", threadName, filePath, lineNumber, line);
    }
}
