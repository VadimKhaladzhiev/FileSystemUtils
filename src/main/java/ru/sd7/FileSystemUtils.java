package ru.sd7;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

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

    void getDirRecursive(File directory, BlockingQueue<File> queue) throws Exception{
        File[] files = directory.listFiles();
        for(File file: files){
            if(file.isDirectory())getDirRecursive(file, queue);
            else queue.put(file);
        }
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
