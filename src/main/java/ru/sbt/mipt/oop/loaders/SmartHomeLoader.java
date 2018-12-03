package ru.sbt.mipt.oop.loaders;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

import java.io.IOException;

public interface SmartHomeLoader {

    BasicSmartHome load() throws IOException;
}
