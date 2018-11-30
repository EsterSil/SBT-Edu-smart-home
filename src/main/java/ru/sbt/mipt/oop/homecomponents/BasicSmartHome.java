package ru.sbt.mipt.oop.homecomponents;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.alarm.Signaling;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class BasicSmartHome implements HomeComposite {

    private Collection<HomeComponent> components;
    private Signaling signaling;

    public BasicSmartHome() {
        components = new ArrayList<>();
        signaling = new Signaling();
    }

    @JsonCreator
    public BasicSmartHome(@JsonProperty("rooms") Collection<Room> rooms) {
        components = new ArrayList<>();
        components.addAll(rooms);
        this.signaling = new Signaling();
    }


    public Collection<HomeComponent> getComponents() {
        return components;
    }

    public Signaling getSignaling() {
        return signaling;
    }

    public void activateSignaling(String code) {
        this.signaling.activate(code);
    }

    public void deactivateSignaling(String code) {
        this.signaling.deactivate(code);
    }


    @Override
    public void addChild(HomeComponent component) {
        if (components == null) components = new ArrayList<>();
        components.add(component);
    }

    @Override
    public void remove(HomeComponent component) {
        components.remove(component);
    }

    @Override
    public Collection<HomeComponent> getChildren() {
        return components;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        if (components != null) components.forEach(c -> c.executeAction(action));
    }


    public void allLightsOff() {
        this.executeAction(object1 -> {
            if (object1 instanceof Light) {
                Light light = (Light) object1;
                light.changeState(light.getId(), false);
            }
        });
    }

    public void allLightsOn() {
        this.executeAction(object1 -> {
            if (object1 instanceof Light) {
                Light light = (Light) object1;
                light.changeState(light.getId(), true);
            }
        });
    }
}
