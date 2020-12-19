package lab06.commands;

import java.io.File;

public class CommandRemoveFile implements Command {
    public void run(String pathName) {
        File file = new File(pathName);

        if (!file.exists() || file.isDirectory()) {
            System.out.println(pathName + ": such no file!");
        } else {
            if (file.delete()) {
                System.out.println(pathName + " has been removed successfully!");
            } else {
                System.out.println(pathName + " could not remove!");
            }
        }
    }

    public void run() {
        System.out.println("Not enough arguments!");
    }
}
