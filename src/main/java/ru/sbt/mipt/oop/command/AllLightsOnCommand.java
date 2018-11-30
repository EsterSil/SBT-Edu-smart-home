package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class AllLightsOnCommand implements UndoableCommand {
    private final BasicSmartHome smartHome;
    private final String owner;

    public AllLightsOnCommand(BasicSmartHome smartHome, String owner) {
        this.smartHome = smartHome;
        this.owner = owner;
    }

    @Override
    public void execute() {
        CommandHistory.save(this);
        smartHome.allLightsOn();
    }

    @Override
    public void undo() {
        smartHome.allLightsOff();
    }

    @Override
    public String getOwner() {
        return owner;
    }

}
