package ru.sd7.core;

import ru.sd7.model.SearchResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class SearchTask implements Callable<List<SearchResult>> {

    private File file;
    private String keyword;

    public SearchTask(File file, String keyword) {
        this.file = file;
        this.keyword = keyword;
    }

    public List<SearchResult> call(){
        List<SearchResult> result = Collections.emptyList();
        try {
            result = search(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    private List<SearchResult> search(File file) throws IOException{
        List<SearchResult> result = new ArrayList<>();
        try(Scanner in = new Scanner(file)){
            int lineNumber = 0;
            while(in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword)){
                    result.add(new SearchResult(Thread.currentThread().getName(), file.getPath(), lineNumber , line));
                }
            }
        }
        return result;
    }
}
