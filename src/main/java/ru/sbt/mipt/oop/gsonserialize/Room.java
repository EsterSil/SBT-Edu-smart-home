package ru.sbt.mipt.oop.gsonserialize;


import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.HomeComponent;

import java.util.Collection;

 class Room  implements HomeComponent {

    private Collection<Light> lights;
    public void setLights(Collection<Light> lights) {
        this.lights = lights;
    }

    private Collection<Door> doors;
    public void setDoors(Collection<Door> doors) {
        this.doors = doors;
    }

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Room() {
    }

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        //this.components = new ArrayList<>();
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }


    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    @Override
    public void executeAction(Action action) {

    }
}
