package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.HallDoorCloseCommand;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.Door;
import ru.sbt.mipt.oop.homecomponents.Room;

@ExtendWith(MockitoExtension.class)
class HallDoorCloseActionTest {

    @Mock
    private Room room;
    @Mock
    private Door door;
    private BasicSmartHome smartHome = new BasicSmartHome();

    private HallDoorCloseCommand command;


    @Test
    void actionTest() {
        Mockito.when(room.getName()).thenReturn("hall");
        Mockito.doCallRealMethod().when(room).executeAction(Mockito.any());
        smartHome.addChild(room);
        command = new HallDoorCloseCommand(smartHome);
        command.execute();
        Mockito.verify(room, Mockito.atLeast(2)).executeAction(Mockito.any());
    }

    @Test
    void doubleActionTest() {
        Mockito.when(room.getName()).thenReturn("hall");
        Mockito.doCallRealMethod().when(room).executeAction(Mockito.any());
        Mockito.doCallRealMethod().when(room).addChild(Mockito.any());
        room.addChild(door);
        smartHome.addChild(room);
        command = new HallDoorCloseCommand(smartHome);
        command.execute();
        Mockito.verify(door, Mockito.atLeast(2)).executeAction(Mockito.any());
    }
}
