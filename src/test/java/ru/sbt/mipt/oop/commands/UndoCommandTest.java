package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.Command;
import ru.sbt.mipt.oop.command.CommandHistoryImpl;
import ru.sbt.mipt.oop.command.UndoCommand;
import ru.sbt.mipt.oop.command.UndoableCommand;
import ru.sbt.mipt.oop.remotecontrol.Controller;

@ExtendWith(MockitoExtension.class)
public class UndoCommandTest {

    @Mock
    private UndoableCommand command;
    @Mock
    private Controller controller;
    @Mock
    CommandHistoryImpl commandHistory;

    private UndoCommand undo;

    @Test
    void executeTest() {
        //Mockito.doCallRealMethod().when(commandHistory).save(Mockito.any(Command.class), Mockito.anyString());
        Mockito.when(commandHistory.getLast(Mockito.anyString())).thenReturn(command);
        Mockito.when(controller.getRcID()).thenReturn("1");
        undo = new UndoCommand(controller, commandHistory);
        undo.execute();

        Mockito.verify(commandHistory).getLast("1");
        Mockito.verify(command).undo();
    }
}
