package ru.sd7;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

class DirSearcher {

    private FileSystemUtils fsu = new FileSystemUtils();

    DirSearcher() {
    }

    List<SearchResult> search(SearchParams params) {
        List<SearchResult> list = new ArrayList<>();
        File dir = new File(params.dir);
        if(dir.isDirectory()){

            CompletionService<List<SearchResult>> completionService = newCompletionService();

            int taskList = 0;
            try {
                taskList = fsu.processDirRecursive(dir, params, completionService);
            } catch (Exception e) {
                e.printStackTrace();
            }

            list = processCompletionList(completionService, taskList);
        }
        return list;
    }

    private List<SearchResult> processCompletionList(CompletionService<List<SearchResult>> completionService,
                                                     int taskList) {
        List<SearchResult> list = new ArrayList<>();
        try {
            for (int i = 0; i < taskList; i++) {
                Future<List<SearchResult>> fList = completionService.take();
                list.addAll(fList.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private CompletionService<List<SearchResult>> newCompletionService() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ((ThreadPoolExecutor)executor).setMaximumPoolSize(10);

        return new ExecutorCompletionService<>(executor);
    }

}
