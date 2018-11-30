package ru.sbt.mipt.oop.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.alarm.*;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)
class SMSSendingDecoratorTest {

    private SensorEvent alarmEvent = new SensorEvent(SensorEventType.LIGHT_ON, "1");

    @Mock
    private Signaling signaling;
    @Mock
    private BasicSmartHome homeMock;
    @Mock
    private HomeEventProcessor processor;
    @InjectMocks
    SMSSenderDecorator smsSenderDecorator;

    @Test
    void onEventWithActivatedStateTest() {
        AlarmState state = new Activated(signaling, "0000");
        Mockito.when(signaling.getState()).thenReturn(state);
        Mockito.when(homeMock.getSignaling()).thenReturn(signaling);
        smsSenderDecorator.onEvent(alarmEvent);
        Mockito.verifyNoMoreInteractions(processor);
    }

    @Test
    void onEventWithAlarmStateTest() {
        AlarmState state = new Alarm(signaling);
        Mockito.when(signaling.getState()).thenReturn(state);
        Mockito.when(homeMock.getSignaling()).thenReturn(signaling);
        smsSenderDecorator.onEvent(alarmEvent);
        Mockito.verifyNoMoreInteractions(processor);
    }
    @Test
    void onEventWithDisabledStateTest() {
        AlarmState state = new Disabled(signaling);
        Mockito.when(signaling.getState()).thenReturn(state);
        Mockito.when(homeMock.getSignaling()).thenReturn(signaling);
        smsSenderDecorator.onEvent(alarmEvent);
        Mockito.verify(processor).onEvent(alarmEvent);
    }

}
