package ru.sbt.mipt.oop.homecomponents;

import java.util.Collection;

public interface HomeComposite extends HomeComponent {
    void addChild(HomeComponent component);

    void remove(HomeComponent component);

    Collection<HomeComponent> getChildren();
}
