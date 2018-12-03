package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.remotecontrol.Controller;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;

public class UndoCommand implements Command {

    private String controller;

    public UndoCommand(Controller controller) {

       this.controller = controller.getRcID();
    }



    @Override
    public void execute() {
            UndoableCommand command = CommandHistory.getLast(controller);
            if (command != null) command.undo();
    }
}
