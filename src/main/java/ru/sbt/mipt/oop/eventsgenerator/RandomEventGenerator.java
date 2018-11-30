package ru.sbt.mipt.oop.eventsgenerator;

public class RandomEventGenerator implements EventSource {
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the eventsgenerator from physical world, but here we're going to just generate some random eventsgenerator
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
