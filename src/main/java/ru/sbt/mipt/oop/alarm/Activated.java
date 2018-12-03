package ru.sbt.mipt.oop.alarm;

public class Activated implements AlarmState {
    Signaling signaling;

    public Activated(Signaling signaling, String code) {
        this.signaling = signaling;
        this.signaling.setSecretCode(code);
        System.out.println("Signaling activated");
    }


    private void changeState(AlarmState state) {
        signaling.changeState(state);
    }

    @Override
    public void activate(String code) {
        System.out.println("Signaling is already active! Any attempt to change code is interpreting as invasion!");
        changeState(new Alarm(signaling));
    }

    @Override
    public void deactivate(String code) {
        if (signaling.checkCodeConcept(code)) {
            System.out.println("Signaling successfully deactivated!");
            changeState(new Disabled(signaling));
        } else {
            System.out.println("Wrong secret code!");
            changeState(new Alarm(signaling));
        }
    }

    @Override
    public void setToAlarm() {
        System.out.println("Invasion detected!");
        changeState(new Alarm(signaling));
    }

}
