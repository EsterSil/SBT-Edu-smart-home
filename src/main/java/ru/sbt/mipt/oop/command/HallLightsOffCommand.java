package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.Room;

public class HallLightsOffCommand implements UndoableCommand {
    private final BasicSmartHome smartHome;
    //private final String owner;

    public HallLightsOffCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
       // this.owner = owner;
    }

    @Override
    public void execute() {
       // CommandHistory.save(this);
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.executeAction(object1 -> {
                        if (object1 instanceof Light) {
                            Light light = (Light) object1;
                            light.changeState(light.getId(), true);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void undo() {
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.executeAction(object1 -> {
                        if (object1 instanceof Light) {
                            Light light = (Light) object1;
                            light.changeState(light.getId(), false);
                        }
                    });
                }
            }
        });
    }
}
