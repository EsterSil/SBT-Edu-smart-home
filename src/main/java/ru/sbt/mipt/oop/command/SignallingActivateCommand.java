package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class SignallingActivateCommand implements Command {

    private final BasicSmartHome smartHome;

    public SignallingActivateCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSignaling().activate("default");
    }
}
