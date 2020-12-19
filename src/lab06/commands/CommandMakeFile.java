package lab06.commands;

import java.io.File;
import java.io.IOException;

public class CommandMakeFile implements Command {
    public void run(String fileName) {
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println(fileName + " already exists!");
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println(fileName + " has been successfully created!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        System.out.println("Not enough arguments!");
    }
}
