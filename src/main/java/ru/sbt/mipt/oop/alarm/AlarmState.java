package ru.sbt.mipt.oop.alarm;

public interface AlarmState {

    void changeState(AlarmState state);

    void activate(String code);

    void deactivate(String code);

    void setToAlarm();


}
