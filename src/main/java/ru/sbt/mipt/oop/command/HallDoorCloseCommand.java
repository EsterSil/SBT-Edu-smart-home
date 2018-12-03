package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.Room;

public class HallDoorCloseCommand implements UndoableCommand {

    private final BasicSmartHome smartHome;


    public HallDoorCloseCommand(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.executeAction(object1 -> {
                        if (object1 instanceof Door) {
                            Door door = (Door) object1;
                            door.changeState(door.getId(), false);
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
                        if (object1 instanceof Door) {
                            Door door = (Door) object1;
                            door.changeState(door.getId(), true);
                        }
                    });
                }
            }
        });
    }

}
