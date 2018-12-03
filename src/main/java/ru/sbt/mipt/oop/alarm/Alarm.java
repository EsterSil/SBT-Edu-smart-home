package ru.sbt.mipt.oop.alarm;

public class Alarm implements AlarmState {
    private Signaling signaling;


    public Alarm(Signaling signaling) {
        this.signaling = signaling;
        System.out.println("ALARM!");
    }


    @Override
    public void activate(String code) {
        System.out.println("ALARM! Someone try to change code!");
    }

    @Override
    public void deactivate(String code) {
        if (signaling.checkCodeConcept(code)) {
            signaling.changeState(new Disabled(signaling));
            System.out.println("Signaling disabled");
        }
        System.out.println("ALARM! Wrong code!");
    }

    @Override
    public void setToAlarm() {

    }
}
