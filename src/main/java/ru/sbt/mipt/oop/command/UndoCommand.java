package ru.sbt.mipt.oop.command;

public class UndoCommand implements Command {

    private final String controller;

    public UndoCommand(String controller) {

        this.controller = controller;
    }

    @Override
    public void execute() {
        if (CommandHistory.isOwnerOfLast(controller)) {
            UndoableCommand command = CommandHistory.getLast();
            if (command != null) command.undo();
        }
    }
}
