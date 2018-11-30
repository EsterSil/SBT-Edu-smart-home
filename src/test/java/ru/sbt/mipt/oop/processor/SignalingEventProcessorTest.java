package ru.sbt.mipt.oop.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)

class SignalingEventProcessorTest {


    @Mock
    private SensorEvent alarmEvent;

    @Mock
    private  SensorEvent otherEvent;

    @Mock
    private BasicSmartHome homeMock;
    @InjectMocks
    private  SignalingEventProcessor processor;

    @Test
    void executeActionOnSmartHomeWithDoorOpenEventTest() {
        Mockito.when(alarmEvent.getObjectId()).thenReturn("0000");
        Mockito.when(alarmEvent.getType()).thenReturn(SensorEventType.ALARM_ACTIVATE);
        processor.onEvent(alarmEvent);
        Mockito.verify(homeMock).activateSignaling("0000");

    }
    @Test
    void executeActionOnSmartHomeWithDoorClosedEventTest() {
        Mockito.when(alarmEvent.getObjectId()).thenReturn("0000");
        Mockito.when(alarmEvent.getType()).thenReturn(SensorEventType.ALARM_DEACTIVATE);
        processor.onEvent(alarmEvent);
        Mockito.verify(homeMock).deactivateSignaling("0000");
    }

    @Test
    void executeActionOnSmartHomeWithOtherEventTest() {
        Mockito.when(otherEvent.getType()).thenReturn(SensorEventType.LIGHT_ON);
        processor.onEvent(otherEvent);
        Mockito.verifyNoMoreInteractions(homeMock);

    }
}
