package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class SignallingAlarmCommand implements Command {

    private final BasicSmartHome smartHome;


    public SignallingAlarmCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSignaling().setToAlarm();
    }
}
