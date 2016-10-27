package ru.sd7;

import ru.sd7.core.DirPrinter;
import ru.sd7.services.console.ConsoleDirSearcher;

import java.util.Scanner;

public class Runner {

    private enum Command{explorer, searcher};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Command command = ask(scanner);

        if(command==Command.explorer)new DirPrinter().run();
        else if(command==Command.searcher)new ConsoleDirSearcher().run();
    }

    private static Command ask(Scanner scanner) {
        System.out.println("Explorer or Searcher?");
        String command = scanner.next();
        if("explorer".equals(command)) return Command.explorer;
        else if("searcher".equals(command)) return Command.searcher;
        else ask(scanner);
        return null;
    }
}
