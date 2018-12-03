package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class AllLightsOnCommand implements UndoableCommand {
    private final BasicSmartHome smartHome;

    public AllLightsOnCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.allLightsOn();
    }

    @Override
    public void undo() {
        smartHome.allLightsOff();
    }

}
