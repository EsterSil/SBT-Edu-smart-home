package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.command.Command;
import ru.sbt.mipt.oop.command.CommandHistory;


import java.util.HashMap;
import java.util.Map;

public class Controller implements RemoteControl {


    private final String rcID;
    private Map<String, Command> buttonMap = new HashMap<>();
    private final CommandHistory history;
    public Controller(String rcID, CommandHistory ch) {
        this.rcID = rcID;
        this.history = ch;
    }

    public String getRcID() {
        return rcID;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttonMap.containsKey(buttonCode)) {
            Command command = buttonMap.get(buttonCode);
            history.save(command, rcID);
            command.execute();
        }
    }

    public Controller setCommandOnButton(String buttonCode, Command command) {
        buttonMap.put(buttonCode, command);
        return this;
    }

}
