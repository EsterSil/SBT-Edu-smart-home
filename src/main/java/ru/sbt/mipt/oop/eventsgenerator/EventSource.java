package ru.sbt.mipt.oop.eventsgenerator;

public interface EventSource {
    SensorEvent getNextSensorEvent();
}
