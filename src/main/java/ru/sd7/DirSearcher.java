package ru.sd7;

import java.io.File;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class DirSearcher implements Runnable {

    private FileSystemUtils fsu = new FileSystemUtils();
    private BlockingQueue<File> queue = new ArrayBlockingQueue<>(5);

    public void run(){
        doNext(readConsoleInput());
    }

    private void doNext(Params params) {
        start(params);
        doNext(readConsoleInput());
    }

    public void start(Params params) {
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
                    new Thread(new SearchTask(queue, params.keyword, params.out), "Thread "+i).start();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Params{
        String dir;
        String keyword;
        OutputStream out;

        public Params() {
        }

        public Params(String dir, String keyword, OutputStream out) {
            this.dir = dir;
            this.keyword = keyword;
            this.out = out;
        }
    }

    private Params readConsoleInput() {
        Params params = new Params();
        params.out=System.out;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dir?");
        params.dir = scanner.next();
        System.out.println("Keyword?");
        params.keyword = scanner.next();
        return params;
    }
}
