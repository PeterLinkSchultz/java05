package lab06.commands;

import java.io.*;
import java.util.ArrayList;

public class CommandWriteFile implements Command {
    ArrayList<String> buffer = new ArrayList<>();

    private boolean appendMode = true;

    public CommandWriteFile(boolean mode) {
        appendMode = mode;
    }

    public void run(String pathName) {
        try {
            File file = new File(pathName);

            if (!file.exists()) {
                System.out.println(pathName + " does not exist!");
            } else {
                System.out.println(pathName + " open to write! Enter exit in new line for save and quit!");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in, "UTF-8"));

                while (true) {
                    String line = reader.readLine();

                    if (line.equals("exit")) {
                        break;
                    } else {
                        buffer.add(line);
                    }
                }

                FileWriter fileWriter = new FileWriter(pathName, this.appendMode);
                buffer.forEach(line -> {
                    try {
                        fileWriter.append(line).append("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                buffer.clear();
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(pathName + " does not exist!");
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Not enough arguments!");
    }
}
