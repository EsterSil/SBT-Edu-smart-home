package ru.sbt.mipt.oop.alarm;

public class Disabled implements AlarmState {
    private Signaling signaling;

    public Disabled(Signaling signaling) {
        this.signaling = signaling;
        this.signaling.setSecretCode("0000");
        System.out.println("Signaling disabled");
    }

    @Override
    public void changeState(AlarmState state) {
        signaling.changeState(state);
    }

    @Override
    public void activate(String code) {
        signaling.changeState(new Activated(signaling, code));
        System.out.println("Signaling was activated. The Home is under control!");
    }

    @Override
    public void deactivate(String code) {
        System.out.println(" Signaling is already disabled");
    }

    @Override
    public void setToAlarm() {
        System.out.println("Something goes wrong...");
        changeState(new Alarm(signaling));
    }
}
