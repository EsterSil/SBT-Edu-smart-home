package ru.sbt.mipt.oop.gsonserialize;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Collection;

class SmartHome {

    private Collection<Room> rooms;

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome() {
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }


}
