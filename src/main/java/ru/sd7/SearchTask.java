package ru.sd7;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable {
    private BlockingQueue<File> queue;
    private String keyword;
    private PrintStream out;

    public SearchTask(BlockingQueue<File> queue, String keyword, OutputStream out) {
        this.queue = queue;
        this.keyword = keyword;
        this.out = new PrintStream(out);
    }

    public void run(){
        try {
            File file = queue.take();
            search(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        }
    }

    public void search(File file) throws IOException{
        try(Scanner in = new Scanner(file)){
            int lineNumber = 0;
            while(in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword)){
                    out.printf("%12s:   %s:%d:%s%n", Thread.currentThread().getName(), file.getPath(), lineNumber , line);
                }
            }
        }
    }
}
