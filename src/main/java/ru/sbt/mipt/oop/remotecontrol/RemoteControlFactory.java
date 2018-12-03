package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.command.CommandHistory;



public class RemoteControlFactory {


private final CommandHistory history;
    public RemoteControlFactory(CommandHistory history) {
        this.history = history;
    }
    private int counter;

    public  Controller  newController() {
        return new Controller(getRcID(), history);
    }

    private synchronized String getRcID() {
        return String.valueOf(++counter);
    }
}
