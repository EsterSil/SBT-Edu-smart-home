package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.command.Command;


import java.util.HashMap;
import java.util.Map;

public class Controller implements RemoteControl {


    private final String rcID;
    private Map<String, Command> buttonMap = new HashMap<>();

    public Controller(String rcID) {
        this.rcID = rcID;
    }

    public String getRcID() {
        return rcID;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttonMap.containsKey(buttonCode)) {
            buttonMap.get(buttonCode).execute();
        }
    }

    public Controller setCommandOnButton(String buttonCode, Command command) {
        buttonMap.put(buttonCode, command);
        return this;
    }

}
