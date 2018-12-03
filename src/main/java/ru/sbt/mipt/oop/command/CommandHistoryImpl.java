package ru.sbt.mipt.oop.command;

import org.springframework.stereotype.Component;

import java.util.List;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
@Component
public class CommandHistoryImpl implements CommandHistory {

    private  ConcurrentHashMap <String, List<UndoableCommand>>history = new ConcurrentHashMap<>();

    public void save(Command command, String rcID ) {

        if (!(command instanceof UndoableCommand)) return;

        if(history.containsKey(rcID)){
            history.get(rcID).add(0, (UndoableCommand) command);
        } else {
            List<UndoableCommand> personalHistory = new CopyOnWriteArrayList<>();
            personalHistory.add((UndoableCommand) command);
            history.put(rcID, personalHistory);
        }
    }


    public UndoableCommand getLast(String rcID) {
        if (!history.containsKey(rcID)) return null;
        List<UndoableCommand> personalHistory = history.get(rcID);
        if (personalHistory.size() == 0) return null;
        return personalHistory.remove(0);

    }
}
