package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.CommandHistory;
import ru.sbt.mipt.oop.command.UndoCommand;
import ru.sbt.mipt.oop.command.UndoableCommand;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)
public class UndoCommandTest {

    @Mock
    private UndoableCommand command;

    private UndoCommand undo = new UndoCommand("1");

    @Test
    void executeTest() {
        Mockito.when(command.getOwner()).thenReturn("1");
        CommandHistory.save(command);

        undo.execute();
        Assertions.assertTrue(CommandHistory.getLast() == null);
        Mockito.verify(command).getOwner();
    }
}
