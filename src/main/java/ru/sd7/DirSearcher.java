package ru.sd7;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class DirSearcher implements Runnable {

    private FileSystemUtils fsu;
    private BlockingQueue<File> queue = new ArrayBlockingQueue<>(5);

    public void run(){
        fsu = new FileSystemUtils();
        doNext(readConsoleInput());
    }

    private void doNext(Params params) {
        File dir = new File(params.dir);
        if(dir.isDirectory()){
            try {
                new Thread(()-> {
                        try {
                            fsu.getDirRecursive(dir, queue);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }).start();
                for (int i = 0; i < 100; i++) {
                    new Thread(new SearchTask(queue, params.keyword)).start();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        doNext(readConsoleInput());
    }

    private class Params{
        String dir;
        String keyword;
    }

    private Params readConsoleInput() {
        Params params = new Params();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dir?");
        params.dir = scanner.next();
        System.out.println("Keyword?");
        params.keyword = scanner.next();
        return params;
    }
}
