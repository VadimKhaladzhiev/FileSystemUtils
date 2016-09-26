package ru.sd7;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileSystemUtils {

    private final String path;

    public FileSystemUtils(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public List<String> dir(){
        checkPathAndFolder();
        File f = new File(path);
        String[] paths = f.list();
        return new ArrayList<>(Arrays.asList(paths));
    }

    private void checkPathAndFolder() {
        checkEmptyPath();
        folderNotExists();
    }

    private void checkEmptyPath() {
        if(path==null) throw new RuntimeException("Path is empty");
    }

    private void folderNotExists() {
        File file = new File(path);
        if(!file.exists()) throw new RuntimeException("Folder not exists");
    }

}
