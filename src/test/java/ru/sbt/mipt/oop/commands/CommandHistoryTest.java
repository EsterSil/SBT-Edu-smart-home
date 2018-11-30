package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.CommandHistory;
import ru.sbt.mipt.oop.command.UndoableCommand;

@ExtendWith(MockitoExtension.class)
class CommandHistoryTest {

    @Mock
    private UndoableCommand command;


    @Test
    void commandHistoryExistingTest() {
        CommandHistory.save(command);
        Assertions.assertEquals(CommandHistory.getLast(), command);
    }

    @Test
    void isOwnerTest() {
        Mockito.when(command.getOwner()).thenReturn("1");
        CommandHistory.save(command);
        Assertions.assertTrue(CommandHistory.isOwnerOfLast("1"));
    }
}
