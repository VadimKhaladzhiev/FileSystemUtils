package ru.sd7;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionService;

class FileSystemUtils {

    private String path;

    FileSystemUtils() {
    }

    FileSystemUtils(String path) {
        this.path = path;
    }

    private void setPath(String path) {
        this.path = path;
    }

    String getPath() {
        return path;
    }

    List<String> getDir(String path) throws Exception{
        setPath(path);
        return getDir();
    }

    List<String> getDir() throws Exception{
        checkPathAndFolder();
        File f = new File(path);
        String[] paths = f.list();
        return new ArrayList<>(Arrays.asList(paths));
    }


    int processDirRecursive(File directory, SearchParams params, CompletionService<List<SearchResult>> completionService) throws Exception{
        int fileList=0;
        File[] files = directory.listFiles();
        for(File file: files){
            if(file.isDirectory()){
                fileList+=processDirRecursive(file, params, completionService);
            }
            else {
                try {
                    completionService.submit(new SearchTask(file, params.keyword));
                    fileList++;
                } catch (Exception e) {
                    System.out.println("Exception: "+ e.getMessage());
                    System.out.println(params.keyword + " " + file.getName());
                }
            }
        }
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
