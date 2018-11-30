package ru.sbt.mipt.oop.homecomponents;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SmartHomeCompositeTest {

    private BasicSmartHome smartHome = new BasicSmartHome();
    @Mock
    private Room room;
    @Mock
    private Door door;
    @Mock
    private Light light;





    @Test
    void executeActionTestOnCompositeTreeFirstLevel() {

        Mockito.doCallRealMethod().when(room).executeAction(Mockito.any(Action.class));
        smartHome.addChild(room);
        smartHome.executeAction(object -> System.out.println("1"));
        Mockito.verify(room, Mockito.atLeast(1)).executeAction(Mockito.any());

    }

    @Test
    void executeActionOnCompositeTreeLowerLevel() {
        Mockito.doCallRealMethod().when(room).addChild(Mockito.any(HomeComponent.class));
        Mockito.doCallRealMethod().when(room).executeAction(Mockito.any(Action.class));
        Mockito.doCallRealMethod().when(door).executeAction(Mockito.any(Action.class));
        Mockito.doCallRealMethod().when(light).executeAction(Mockito.any(Action.class));
        room.addChild(door);
        room.addChild(light);
        smartHome.addChild(room);
        //int counter = 0;
        smartHome.executeAction(object -> System.out.println("1"));

        Mockito.verify(light).executeAction(Mockito.any());
        Mockito.verify(door).executeAction(Mockito.any());
    }

    @Test
    void executeActionOnAllObjectsOfCompositeTree() {
        Mockito.doCallRealMethod().when(room).addChild(Mockito.any(HomeComponent.class));
        Mockito.doCallRealMethod().when(room).executeAction(Mockito.any(Action.class));
        Mockito.doCallRealMethod().when(door).executeAction(Mockito.any(Action.class));
        Mockito.doCallRealMethod().when(light).executeAction(Mockito.any(Action.class));
        room.addChild(door);
        room.addChild(door);
        room.addChild(light);
        room.addChild(light);
        smartHome.addChild(room);
        Counter counter = new Counter();
        smartHome.executeAction(object -> counter.inc());
        Assertions.assertEquals(6, counter.getCounter());
    }


}

class Counter {
    private static int counter = 0;

    int getCounter() {
        return counter;
    }

    void inc() {
        counter++;
    }

}



