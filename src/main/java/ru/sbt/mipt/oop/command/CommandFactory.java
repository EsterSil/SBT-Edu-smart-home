package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;


public class CommandFactory {


    private final BasicSmartHome smartHome;

    public CommandFactory(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public Command getCommand(CommandType type, String controllerId) {
        switch (type) {
            case ALL_LIGHTS_OFF:
                return new AllLightsOffCommand(smartHome, controllerId);
            case ALL_LIGHTS_ON:
                return new AllLightsOnCommand(smartHome, controllerId);
            case HALL_DOOR_CLOSE:
                return new HallDoorCloseCommand(smartHome, controllerId);
            case HALL_LIGHTS_OFF:
                return new HallLightsOffCommand(smartHome, controllerId);
            case SIGNALLING_ACTIVATE:
                return new SignallingActivateCommand(smartHome);
            case SIGNALLING_DEACTIVATE:
                return new SignallingAlarmCommand(smartHome);
            case UNDO:
                return new UndoCommand(controllerId);
        }
        return null;
    }
}
