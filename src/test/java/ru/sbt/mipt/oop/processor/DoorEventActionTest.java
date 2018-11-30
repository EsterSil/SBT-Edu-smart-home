package ru.sbt.mipt.oop.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)
class DoorEventActionTest {

    @Mock
    private Door doorMock;

    @Mock
    private SensorEvent eventMock;

    private BasicSmartHome smartHome = new BasicSmartHome();

    private  DoorEventProcessor processor =  new DoorEventProcessor(smartHome);


    @Test
    void executeActionOnDoorTest() {
        Mockito.when(eventMock.getType()).thenReturn(SensorEventType.DOOR_OPEN);
        smartHome.addChild(doorMock);
        processor.onEvent(eventMock);
        Mockito.verify(doorMock).executeAction(Mockito.any() );
    }

    @Test
    void changeStateOnDoorTest() {

        Mockito.when(eventMock.getType()).thenReturn(SensorEventType.DOOR_OPEN);
        Mockito.doCallRealMethod().when(doorMock).executeAction(Mockito.any(Action.class));
        Mockito.when(eventMock.getObjectId()).thenReturn("1");
        smartHome.addChild(doorMock);
        processor.onEvent(eventMock);
        Mockito.verify(doorMock).changeState("1", true );
    }
}
