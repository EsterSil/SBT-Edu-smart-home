package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class SignallingActivateCommand implements Command {

    private final BasicSmartHome smartHome;
    private final String password;


    public SignallingActivateCommand(BasicSmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    public SignallingActivateCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
        password = "default";
    }

    @Override
    public void execute() {
        smartHome.getSignaling().activate(password);
    }
}
