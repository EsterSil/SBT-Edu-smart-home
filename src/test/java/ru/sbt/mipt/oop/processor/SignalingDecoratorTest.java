package ru.sbt.mipt.oop.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.alarm.*;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)
class SignalingDecoratorTest {
    //@Mock
    private SensorEvent alarmEvent;

    @Mock
    private Signaling signaling;// = new Signaling();
    @Mock
    private BasicSmartHome homeMock;
    @Mock
    private HomeEventProcessor processor;
    @InjectMocks
    SignalingDecorator decorator;

    @Test
    void onEventWithActivatedStateTest() {
        AlarmState state = new Activated(signaling, "0000");
        Mockito.when(signaling.getState()).thenReturn(state);
        Mockito.when(homeMock.getSignaling()).thenReturn(signaling);
        decorator.onEvent(alarmEvent);
        Mockito.verify(signaling).setToAlarm();
        Mockito.verifyNoMoreInteractions(processor);
    }

    @Test
    void onEventWithAlarmStateTest() {
        AlarmState state = new Alarm(signaling);
        Mockito.when(signaling.getState()).thenReturn(state);
        Mockito.when(homeMock.getSignaling()).thenReturn(signaling);
        decorator.onEvent(alarmEvent);
        Mockito.verifyNoMoreInteractions(processor);
    }
    @Test
    void onEventWithDisabledStateTest() {
        AlarmState state = new Disabled(signaling);
        Mockito.when(signaling.getState()).thenReturn(state);
        Mockito.when(homeMock.getSignaling()).thenReturn(signaling);
        decorator.onEvent(alarmEvent);
        Mockito.verify(processor).onEvent(alarmEvent);
    }

}
