package lab06;

import lab06.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandSelector {
    Map<String, Command> list = new HashMap();

    public CommandSelector() {
        initCommands();
    }

    private void initCommands() {
        list.put("ls", new CommandLS());
        list.put("cat", new CommandCat());
        list.put("make", new CommandMakeFile());
        list.put("remove", new CommandRemoveFile());
        list.put("append", new CommandWriteFile(true));
        list.put("rewrite", new CommandWriteFile(false));
    }

    public void run(String[] cmd) {
        Command command = list.get(cmd[0]);

        if (command != null) {
            if (cmd.length > 1){
                command.run(cmd[1]);
            } else {
                command.run();
            }
        } else {
            System.out.println("Command " + cmd[0] + " not found");
        }
    }
}
