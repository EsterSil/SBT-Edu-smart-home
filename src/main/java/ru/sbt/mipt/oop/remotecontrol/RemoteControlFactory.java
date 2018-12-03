package ru.sbt.mipt.oop.remotecontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.command.CommandHistory;


@Component
public class RemoteControlFactory {


    private final CommandHistory history;
    private int counter;

    @Autowired
    public RemoteControlFactory(CommandHistory history) {
        this.history = history;
    }

    public Controller newController() {
        return new Controller(getRcID(), history);
    }

    private synchronized String getRcID() {
        return String.valueOf(++counter);
    }
}
