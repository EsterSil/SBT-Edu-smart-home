package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.CommandHistory;
import ru.sbt.mipt.oop.command.CommandHistoryImpl;
import ru.sbt.mipt.oop.command.UndoableCommand;

@ExtendWith(MockitoExtension.class)
class CommandHistoryImplTest {

    @Mock
    private UndoableCommand command;
    private CommandHistory history = new CommandHistoryImpl();


    @Test
    void commandHistoryExistingTest() {
        history.save(command, "1");
        Assertions.assertEquals(history.getLast("1"), command);
    }
    
}
