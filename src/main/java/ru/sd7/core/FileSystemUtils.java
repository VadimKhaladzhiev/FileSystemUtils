package ru.sd7.core;

import ru.sd7.model.SearchParams;
import ru.sd7.model.SearchResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionService;

public class FileSystemUtils {

    private String path;

    public FileSystemUtils() {
    }

    public FileSystemUtils(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public List<String> getDir(String path) throws Exception{
        setPath(path);
        return getDir();
    }

    public List<String> getDir() throws Exception{
        checkPathAndFolder();
        File f = new File(path);
        String[] paths = f.list();
        return new ArrayList<>(Arrays.asList(paths));
    }


    public int processDirRecursive(File directory, SearchParams params, CompletionService<List<SearchResult>> completionService) throws Exception{
        int fileList=0;
        File[] files = directory.listFiles();
        for(File file: files){
            if(file.isDirectory()){
                fileList+=processDirRecursive(file, params, completionService);
            }
            else {
                try {
                    completionService.submit(new SearchTask(file, params.getKeyword()));
                    fileList++;
                } catch (Exception e) {
                    System.out.println("Exception: "+ e.getMessage());
                    System.out.println(params.getKeyword() + " " + file.getName());
                }
            }
        }
        Thread.sleep(1);
        return fileList;
    }

    private void checkPathAndFolder() throws Exception {
        checkEmptyPath();
        folderNotExists();
    }

    private void checkEmptyPath() throws Exception {
        if(path==null) throw new Exception("Path is empty");
    }

    private void folderNotExists() throws Exception{
        File file = new File(path);
        if(!file.exists()) throw new Exception("Folder not exists");
    }

}
