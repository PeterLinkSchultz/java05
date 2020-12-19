package lab06.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandCat implements Command {
    @Override
    public void run(String fileName) {
        File file = new File(fileName);

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Not enough arguments!");
    }
}
