package ru.sbt.mipt.oop.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.eventsgenerator.SensorEvent;
import ru.sbt.mipt.oop.eventsgenerator.SensorEventType;
import ru.sbt.mipt.oop.homecomponents.Action;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.Room;

@ExtendWith(MockitoExtension.class)
class HallEventActionTest {

    @Mock
    private Room roomMock;

    @Mock
    private SensorEvent eventMock;

    @Mock
    private BasicSmartHome smartHome ; //= new BasicSmartHome();

    @InjectMocks
    private HallEventProcessor processor;

    @Test
    void executeActionTest() {

        Mockito.when(eventMock.getType()).thenReturn(SensorEventType.DOOR_CLOSED);

        processor.onEvent(eventMock);

        Mockito.verify(smartHome).executeAction(Mockito.any());
    }

    @Test
    void executeActionOnRoomTest() {
        Mockito.when(eventMock.getType()).thenReturn(SensorEventType.DOOR_CLOSED);
        Mockito.when(roomMock.getName()).thenReturn("hall");
        Mockito.doCallRealMethod().when(smartHome).executeAction(Mockito.any(Action.class));
        Mockito.doCallRealMethod().when(roomMock).executeAction(Mockito.any(Action.class));
        Mockito.doCallRealMethod().when(smartHome).addChild(Mockito.any(Room.class));
        smartHome.addChild(roomMock);
        processor.onEvent(eventMock);
        Mockito.verify(roomMock).executeAction(Mockito.any());
        Mockito.verify(smartHome).allLightsOff();

    }


}
