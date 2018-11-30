package ru.sbt.mipt.oop.processor;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;

import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;


@ExtendWith(MockitoExtension.class)

class DoorEventProcessorTest {

    @Mock
    private  SensorEvent doorEvent;

    @Mock
    private  SensorEvent otherEvent;

    @Mock
    private BasicSmartHome homeMock;
    @InjectMocks
    private  DoorEventProcessor processor;



    @Test
     void executeActionOnSmartHomeWithDoorOpenEventTest() {
        Mockito.when(doorEvent.getType()).thenReturn(SensorEventType.DOOR_OPEN);
        processor.onEvent(doorEvent);
        Mockito.verify(homeMock).executeAction(Mockito.any());

    }
    @Test
     void executeActionOnSmartHomeWithDoorClosedEventTest() {
        Mockito.when(doorEvent.getType()).thenReturn(SensorEventType.DOOR_CLOSED);
        processor.onEvent(doorEvent);
        Mockito.verify(homeMock).executeAction(Mockito.any());
    }

    @Test
     void executeActionOnSmartHomeWithOtherEventTest() {
        Mockito.when(otherEvent.getType()).thenReturn(SensorEventType.LIGHT_ON);
        processor.onEvent(otherEvent);
        Mockito.verifyNoMoreInteractions(homeMock);

    }



}
