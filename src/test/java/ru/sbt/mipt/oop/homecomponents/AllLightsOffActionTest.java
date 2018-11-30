package ru.sbt.mipt.oop.homecomponents;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AllLightsOffActionTest {


    @Mock
    private Light lightMock;
    @Mock
    private BasicSmartHome smartHome; //= new BasicSmartHome();


    @Test
    void executeActionTest() {
        Mockito.doCallRealMethod().when(smartHome).allLightsOff();
        smartHome.allLightsOff();
        Mockito.verify(smartHome).executeAction(Mockito.any());
    }

    @Test
    void executeActionOnLightTest() {

        BasicSmartHome smartHome = new BasicSmartHome();
        Mockito.doCallRealMethod().when(lightMock).executeAction(Mockito.any(Action.class));
        smartHome.addChild(lightMock);
        smartHome.allLightsOff();
        Mockito.verify(lightMock).executeAction(Mockito.any());
    }

}
