package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.HallLightsOffCommand;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)
class HallLightsOffCommandTest {

    @Mock
    private BasicSmartHome smartHome;
    //@InjectMocks
    private HallLightsOffCommand command;
    @Test
    void executeTest() {
        command = new HallLightsOffCommand(smartHome);
        command.execute();
        Mockito.verify(smartHome).executeAction(Mockito.any());
    }

    @Test
    void undoTest() {
        command = new HallLightsOffCommand(smartHome);
        command.undo();
        Mockito.verify(smartHome).executeAction(Mockito.any());
    }



}
