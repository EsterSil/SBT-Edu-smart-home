package ru.sbt.mipt.oop.command;

public interface CommandHistory {
    void save(Command command, String rcID);

    UndoableCommand getLast(String rcID);

}
