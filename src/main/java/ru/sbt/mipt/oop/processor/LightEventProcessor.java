package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.*;

import static ru.sbt.mipt.oop.eventsgenerator.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.eventsgenerator.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements HomeEventProcessor {
    private final BasicSmartHome smartHome;

    public LightEventProcessor(BasicSmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private boolean isLightEvent(SensorEvent event) {
        if (event == null) return false;
        return event.getType().equals(LIGHT_ON) || event.getType().equals(LIGHT_OFF);
    }

    @Override
    public void onEvent(SensorEvent event) {
        if (!isLightEvent(event)) {
            return;
        }
        smartHome.executeAction(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                boolean state = event.getType() == LIGHT_ON;
                light.changeState(event.getObjectId(), state);
            }
        });
    }


}
