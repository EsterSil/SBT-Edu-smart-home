package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.HallDoorCloseCommand;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)
class HallDoorCloseCommandTest {

    @Mock
    private BasicSmartHome smartHome;

    private HallDoorCloseCommand command;

    @Test
    void executeTest() {
        command = new HallDoorCloseCommand(smartHome);
        command.execute();
        Mockito.verify(smartHome).executeAction(Mockito.any());
    }

    @Test
    void undoTest() {
        command = new HallDoorCloseCommand(smartHome);
        command.undo();
        Mockito.verify(smartHome).executeAction(Mockito.any());
    }


}
