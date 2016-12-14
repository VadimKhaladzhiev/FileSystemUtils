package ru.sd7.core;

import ru.sd7.model.SearchParams;
import ru.sd7.model.SearchResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DirSearcher {

    private FileSystemUtils fsu = new FileSystemUtils();

    public DirSearcher() {
    }

    public List<SearchResult> search(SearchParams params) {
        List<SearchResult> list = new ArrayList<>();
        File dir = new File(params.getDir());
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
        ExecutorService executor = new ThreadPoolExecutor(0, 10,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        return new ExecutorCompletionService<>(executor);
    }

}
