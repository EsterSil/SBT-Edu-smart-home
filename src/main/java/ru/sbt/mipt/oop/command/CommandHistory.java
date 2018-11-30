package ru.sbt.mipt.oop.command;

import java.util.Stack;

public class CommandHistory {
    private static Stack<UndoableCommand> history = new Stack<>();

    public static void save(UndoableCommand command) {
        history.push(command);
    }

    public static boolean isOwnerOfLast(String owner) {
        if (history.empty()) return false;
        return owner.equals(history.peek().getOwner());
    }

    public static UndoableCommand getLast() {
        if (history.empty()) return null;
        return history.pop();

    }
}
