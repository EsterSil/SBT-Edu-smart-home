package ru.sbt.mipt.oop.managers;

import ru.sbt.mipt.oop.processor.HomeEventProcessor;

public interface EventManager {
    void runEventLoop();

    void addEventProcessor(HomeEventProcessor processor);

}
