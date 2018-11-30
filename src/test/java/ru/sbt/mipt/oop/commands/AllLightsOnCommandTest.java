package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.AllLightsOnCommand;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)
class AllLightsOnCommandTest {


    @Mock
    private BasicSmartHome smartHome;
    @InjectMocks
    private AllLightsOnCommand command;

    @Test
    void executeTest() {
        command.execute();
        Mockito.verify(smartHome).allLightsOn();
    }

    @Test
    void undoTest() {
        command.undo();
        Mockito.verify(smartHome).allLightsOff();
    }


}
