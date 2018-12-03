package ru.sbt.mipt.oop.command;

public interface UndoableCommand extends Command {
    void undo();


    @Override
    void execute();
}
