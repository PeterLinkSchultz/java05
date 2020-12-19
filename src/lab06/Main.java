package lab06;

import lab06.commands.CommandCat;
import lab06.commands.CommandWriteFile;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        CommandSelector commandSelector = new CommandSelector();

            try(Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("Enter a command:");
                    String line = scanner.nextLine();

                    if (line.equals("exit")) {
                        break;
                    }

                    String[] cmd = line.split(" ");

                    commandSelector.run(cmd);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
