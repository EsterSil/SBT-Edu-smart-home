package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.remotecontrol.Controller;

public class UndoCommand implements Command {

    private String controller;
    private CommandHistory history;

    public UndoCommand(Controller controller, CommandHistory history) {
        this.history = history;
        this.controller = controller.getRcID();
    }


    @Override
    public void execute() {
        UndoableCommand command = history.getLast(controller);
        if (command != null) command.undo();
    }
}
