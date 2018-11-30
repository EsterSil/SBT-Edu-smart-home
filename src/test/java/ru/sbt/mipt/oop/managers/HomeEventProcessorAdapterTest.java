package ru.sbt.mipt.oop.managers;


import com.coolcompany.smarthome.events.CCSensorEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.processor.HomeEventProcessor;

@ExtendWith(MockitoExtension.class)
public class HomeEventProcessorAdapterTest {

    @Mock
    private CCSensorEvent event;
    @Mock
    private HomeEventProcessor eventProcessor;

    @Captor
    private ArgumentCaptor<SensorEvent> captor;
    @InjectMocks
    private HandlerProcessorAdapter adapter;

    @Test
    void handleEventWithLightIsOnEventTest() {
        Mockito.when(event.getEventType()).thenReturn("LightIsOn");
        Mockito.when(event.getObjectId()).thenReturn("1");
        adapter.handleEvent(event);
        Mockito.verify(eventProcessor).onEvent(captor.capture());
        Assertions.assertEquals(captor.getValue().getType(), SensorEventType.LIGHT_ON);
        Assertions.assertEquals(captor.getValue().getObjectId(), "1");
    }

    @Test
    void handleEventWithLightIsOffEventTest() {
        Mockito.when(event.getEventType()).thenReturn("LightIsOff");
        Mockito.when(event.getObjectId()).thenReturn("1");
        adapter.handleEvent(event);
        Mockito.verify(eventProcessor).onEvent(captor.capture());
        Assertions.assertEquals(captor.getValue().getType(), SensorEventType.LIGHT_OFF);
        Assertions.assertEquals(captor.getValue().getObjectId(), "1");
    }

    @Test
    void handleEventWithDoorIsOpenEventTest() {
        Mockito.when(event.getEventType()).thenReturn("DoorIsOpen");
        Mockito.when(event.getObjectId()).thenReturn("1");
        adapter.handleEvent(event);
        Mockito.verify(eventProcessor).onEvent(captor.capture());
        Assertions.assertEquals(captor.getValue().getType(), SensorEventType.DOOR_OPEN);
        Assertions.assertEquals(captor.getValue().getObjectId(), "1");
    }

    @Test
    void handleEventWithDoorIsClosedEventTest() {
        Mockito.when(event.getEventType()).thenReturn("DoorIsClosed");
        Mockito.when(event.getObjectId()).thenReturn("1");
        adapter.handleEvent(event);
        Mockito.verify(eventProcessor).onEvent(captor.capture());
        Assertions.assertEquals(captor.getValue().getType(), SensorEventType.DOOR_CLOSED);
        Assertions.assertEquals(captor.getValue().getObjectId(), "1");
    }

    @Test
    void handleEventWithDoorIsLockedEventTest() {
        Mockito.when(event.getEventType()).thenReturn("DoorIsLocked");
        Mockito.when(event.getObjectId()).thenReturn("1");
        adapter.handleEvent(event);
        Mockito.verify(eventProcessor).onEvent(captor.capture());
        Assertions.assertEquals(captor.getValue().getType(), SensorEventType.ALARM_ACTIVATE);
        Assertions.assertEquals(captor.getValue().getObjectId(), "1");
    }

    @Test
    void handleEventWithDoorIsUnlockedEventTest() {
        Mockito.when(event.getEventType()).thenReturn("DoorIsUnlocked");
        Mockito.when(event.getObjectId()).thenReturn("1");
        adapter.handleEvent(event);
        Mockito.verify(eventProcessor).onEvent(captor.capture());
        Assertions.assertEquals(captor.getValue().getType(), SensorEventType.ALARM_DEACTIVATE);
        Assertions.assertEquals(captor.getValue().getObjectId(), "1");
    }
}
