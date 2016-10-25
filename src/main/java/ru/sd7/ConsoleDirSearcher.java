package ru.sd7;

import java.util.List;
import java.util.Scanner;

class ConsoleDirSearcher extends DirSearcher {

    void run(){
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
        params.dir = scanner.next();
        System.out.println("Keyword?");
        params.keyword = scanner.next();
        return params;
    }
}
