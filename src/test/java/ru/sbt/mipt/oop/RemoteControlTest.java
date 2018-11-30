package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.command.Command;
import ru.sbt.mipt.oop.command.UndoableCommand;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.remotecontrol.Controller;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;

@ExtendWith(MockitoExtension.class)
class RemoteControlTest {
    @Mock
    UndoableCommand command;

    @Mock
    BasicSmartHome smartHome;
    @InjectMocks
    Controller controller;

    @Test
    void onButtonPressedTest() {
        controller.setCommandOnButton("A", command);
        controller.onButtonPressed("A");
        Mockito.verify(command).execute();

    }
}
