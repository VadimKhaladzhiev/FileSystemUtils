package ru.sd7;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<String> dir() throws Exception{
        checkPathAndFolder();
        File f = new File(path);
        String[] paths = f.list();
        return new ArrayList<>(Arrays.asList(paths));
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
