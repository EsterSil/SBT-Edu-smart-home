package ru.sbt.mipt.oop.managers;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;

import java.util.HashMap;
import java.util.Map;

public class HandlerProcessorAdapter implements EventHandler {
    private final HomeEventProcessor eventProcessor;
    private static Map<String, SensorEventType> eventTypeMap = new HashMap<>();

    static {
        eventTypeMap.put("LightIsOn", SensorEventType.LIGHT_ON);
        eventTypeMap.put("LightIsOff", SensorEventType.LIGHT_OFF);
        eventTypeMap.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        eventTypeMap.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        eventTypeMap.put("DoorIsLocked", SensorEventType.ALARM_ACTIVATE);
        eventTypeMap.put("DoorIsUnlocked", SensorEventType.ALARM_DEACTIVATE);
    }

    HandlerProcessorAdapter(HomeEventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcessor.onEvent(new SensorEvent(eventTypeMap.get(event.getEventType()), event.getObjectId()));
    }
}
