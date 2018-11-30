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

class HallEventProcessorTest {

    @Mock
    private SensorEvent doorEvent;

    @Mock
    private SensorEvent otherEvent;

    @Mock
    private BasicSmartHome homeMock;
    @InjectMocks
    private HallEventProcessor processor;



    @Test
    void executeActionWithCloseDoorEventTest () {
        Mockito.when(doorEvent.getType()).thenReturn(SensorEventType.DOOR_CLOSED);
        processor.onEvent(doorEvent);
        Mockito.verify(homeMock).executeAction(Mockito.any());
    }
    @Test
    void executeActionWithOpenDoorEventTest () {
        Mockito.when(doorEvent.getType()).thenReturn(SensorEventType.DOOR_OPEN);
        processor.onEvent(doorEvent);
        Mockito.verifyNoMoreInteractions(homeMock);
    }
    @Test
    void executeActionWithOtherEventTest () {
        Mockito.when(doorEvent.getType()).thenReturn(SensorEventType.LIGHT_ON);
        processor.onEvent(doorEvent);
        Mockito.verifyNoMoreInteractions(homeMock);
    }

}
