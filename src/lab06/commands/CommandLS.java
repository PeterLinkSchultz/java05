package lab06.commands;

import java.io.File;

public class CommandLS implements Command {
    public void run(String pathName) {
        File file = new File(pathName);

        if (!file.exists()) {
            System.out.println(pathName + ": No such file or directory");
        } else {
            File[] directory = file.listFiles();

            assert directory != null;
            for (File result: directory) {
                System.out.println(result.getName() + (result.isDirectory() ? "/" : ""));
            }
        }
    }

    public void run() {
        run("./");
    }
}
