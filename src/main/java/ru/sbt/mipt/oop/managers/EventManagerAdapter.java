package ru.sbt.mipt.oop.managers;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;

public class EventManagerAdapter implements EventManager {
    private SensorEventsManager manager;

    public EventManagerAdapter(SensorEventsManager manager) {
        this.manager = manager;
    }

    public EventManagerAdapter() {
        this.manager = new SensorEventsManager();
    }

    @Override
    public void runEventLoop() {
        manager.start();
    }

    @Override
    public void addEventProcessor(HomeEventProcessor processor) {
        manager.registerEventHandler(new HandlerProcessorAdapter(processor));
    }
}
