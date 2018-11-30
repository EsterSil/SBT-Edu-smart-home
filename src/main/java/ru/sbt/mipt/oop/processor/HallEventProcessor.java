package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class HallEventProcessor implements HomeEventProcessor {
    private final BasicSmartHome smartHome;

    public HallEventProcessor(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void onEvent(SensorEvent event) {
        if (!event.getType().equals(SensorEventType.DOOR_CLOSED)) {
            return;
        }
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    smartHome.allLightsOff();
                    System.out.println(" Hall door was closed. All lights off");
                }
            }
        });
    }
}
