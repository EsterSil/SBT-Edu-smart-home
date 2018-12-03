package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class AllLightsOffCommand implements UndoableCommand {

    private final BasicSmartHome smartHome;

    public AllLightsOffCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.allLightsOff();
    }

    @Override
    public void undo() {
        smartHome.allLightsOn();
    }

}
