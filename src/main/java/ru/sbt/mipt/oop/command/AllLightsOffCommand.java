package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class AllLightsOffCommand implements UndoableCommand {

    private final String owner;
    private final BasicSmartHome smartHome;

    public AllLightsOffCommand(BasicSmartHome smartHome, String owner) {
        this.smartHome = smartHome;
        this.owner = owner;
    }

    @Override
    public void execute() {
        CommandHistory.save(this);
        smartHome.allLightsOff();
    }

    @Override
    public void undo() {
        smartHome.allLightsOn();
    }

    @Override
    public String getOwner() {
        return owner;
    }
}
