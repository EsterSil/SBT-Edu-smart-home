package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.HallLightsOffCommand;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.Light;
import ru.sbt.mipt.oop.homecomponents.Room;

@ExtendWith(MockitoExtension.class)
class HallLightsOffCommandActionTest {

    @Mock
    private Room room;
    @Mock
    private Light light;
    private BasicSmartHome smartHome = new BasicSmartHome();

    private HallLightsOffCommand command;

    @Test
    void actionTest() {
        Mockito.when(room.getName()).thenReturn("hall");
        Mockito.doCallRealMethod().when(room).executeAction(Mockito.any());
        smartHome.addChild(room);
        command = new HallLightsOffCommand(smartHome, "1");
        command.execute();
        Mockito.verify(room, Mockito.atLeast(2)).executeAction(Mockito.any());
    }


    @Test
    void doubleActionTest() {
        Mockito.when(room.getName()).thenReturn("hall");
        Mockito.doCallRealMethod().when(room).executeAction(Mockito.any());
        Mockito.doCallRealMethod().when(room).addChild(Mockito.any());
        room.addChild(light);
        smartHome.addChild(room);
        command = new HallLightsOffCommand(smartHome, "1");
        command.execute();
        Mockito.verify(light, Mockito.atLeast(2)).executeAction(Mockito.any());
    }
}
