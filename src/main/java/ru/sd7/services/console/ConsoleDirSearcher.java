package ru.sd7.services.console;

import ru.sd7.core.DirSearcher;
import ru.sd7.model.SearchParams;
import ru.sd7.model.SearchResult;

import java.util.List;
import java.util.Scanner;

public class ConsoleDirSearcher extends DirSearcher {

    public void run(){
        nextQuery(readConsoleInput());
    }

    private void nextQuery(SearchParams params) {
        printResults(search(params));
        nextQuery(readConsoleInput());
    }

    private void printResults(List<SearchResult> list) {
        for(SearchResult result : list){
            System.out.println(result);
        }
    }

    private SearchParams readConsoleInput() {
        SearchParams params = new SearchParams();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dir?");
        params.setDir(scanner.next());
        System.out.println("Keyword?");
        params.setKeyword(scanner.next());
        return params;
    }
}
