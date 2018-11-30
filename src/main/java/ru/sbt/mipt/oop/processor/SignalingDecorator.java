package ru.sbt.mipt.oop.processor;

import ru.sbt.mipt.oop.alarm.Activated;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

public class SignalingDecorator implements HomeEventProcessor {

    private final HomeEventProcessor processor;
    private final BasicSmartHome smartHome;

    public SignalingDecorator(HomeEventProcessor processor, BasicSmartHome smartHome) {
        this.processor = processor;
        this.smartHome = smartHome;
    }

    @Override
    public void onEvent(SensorEvent event) {
        if (smartHome.getSignaling().getState() instanceof Activated) {
            smartHome.getSignaling().setToAlarm();
            return;
        }
        if (smartHome.getSignaling().getState() instanceof Alarm) {
            return;
        }
        processor.onEvent(event);
    }
}
