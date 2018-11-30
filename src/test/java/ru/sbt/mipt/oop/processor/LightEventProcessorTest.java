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
class LightEventProcessorTest {


    @Mock
    private SensorEvent lightEvent;

    @Mock
    private  SensorEvent otherEvent;

    @Mock
    private BasicSmartHome homeMock;
    @InjectMocks
    private  LightEventProcessor processor;



    @Test
    void executeActionOnSmartHomeWithLightOnEventTest() {
        Mockito.when(lightEvent.getType()).thenReturn(SensorEventType.LIGHT_ON);
        processor.onEvent(lightEvent);
        Mockito.verify(homeMock).executeAction(Mockito.any());
    }

    @Test
    void executeActionOnSmartHomeWithLightsOffEventTest() {
        Mockito.when(lightEvent.getType()).thenReturn(SensorEventType.LIGHT_OFF);
        processor.onEvent(lightEvent);
        Mockito.verify(homeMock).executeAction(Mockito.any());
    }

    @Test
    void executeActionOnSmartHomeWithOtherEventTest() {
        Mockito.when(otherEvent.getType()).thenReturn(SensorEventType.ALARM_ACTIVATE);
        processor.onEvent(otherEvent);
        Mockito.verifyNoMoreInteractions(homeMock);
    }
}
