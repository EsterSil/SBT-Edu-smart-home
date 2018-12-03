package ru.sbt.mipt.oop.alarm;

public class Signaling {

    private AlarmState state = new Disabled(this);
    private String secretCode = "0000";

    public Signaling() {
    }

    public Signaling(String secretCode) {
        this.secretCode = secretCode;
    }

    void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    void changeState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {
        return state;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public boolean checkCodeConcept(String code) {
        return this.secretCode.equals(code);
    }

    public void setToAlarm() {
        state.setToAlarm();
    }
}
