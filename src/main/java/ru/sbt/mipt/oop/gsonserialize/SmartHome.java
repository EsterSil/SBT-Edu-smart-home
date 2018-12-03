package ru.sbt.mipt.oop.gsonserialize;

import java.util.Collection;

class SmartHome {

    private Collection<Room> rooms;

    public SmartHome() {
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }


}
