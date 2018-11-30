package ru.sbt.mipt.oop.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbt.mipt.oop.alarm.Signaling;
import ru.sbt.mipt.oop.command.SignallingActivateCommand;
import ru.sbt.mipt.oop.command.SignallingAlarmCommand;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;

@ExtendWith(MockitoExtension.class)
class SignallingAlarmCommandTest {

    @Mock
    private BasicSmartHome home;
    @Mock
    private Signaling signaling;
    private SignallingAlarmCommand command;

    @Test
    void executeTest() {
        Mockito.when(home.getSignaling()).thenReturn(signaling);
        command = new SignallingAlarmCommand(home);
        command.execute();
        Mockito.verify(signaling).setToAlarm();
    }

}
