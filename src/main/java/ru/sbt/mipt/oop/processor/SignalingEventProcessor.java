package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class SignalingEventProcessor implements HomeEventProcessor {
    private final BasicSmartHome smartHome;

    public SignalingEventProcessor(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void onEvent(SensorEvent event) {
        if (!isSignalingEvent(event)) {
            return;
        }
        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.activateSignaling(event.getObjectId());
        } else {
            smartHome.deactivateSignaling(event.getObjectId());
        }
    }

    private boolean isSignalingEvent(SensorEvent event) {
        return event.getType().equals(SensorEventType.ALARM_ACTIVATE)
                || event.getType().equals(SensorEventType.ALARM_DEACTIVATE);
    }
}
