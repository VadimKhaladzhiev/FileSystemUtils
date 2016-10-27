package ru.sd7.core;

import ru.sd7.utils.CachedFileSystemUtils;

import java.util.List;
import java.util.Scanner;

public class DirPrinter implements Runnable {

    private CachedFileSystemUtils fsu;

    public void run(){
        fsu = new CachedFileSystemUtils();
        doNext(readConsoleInput());
    }

    private void doNext(String dir) {
        try {
            List<String> files = fsu.getDir(dir);
            for(String file : files){
                System.out.println(file);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        doNext(readConsoleInput());
    }

    private String readConsoleInput() {
        String dir;Scanner scanner = new Scanner(System.in);
        System.out.println("Dir?");
        dir = scanner.next();
        return dir;
    }
}
