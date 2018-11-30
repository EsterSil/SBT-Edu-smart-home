package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.command.CommandFactory;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.managers.EventManager;

import ru.sbt.mipt.oop.remotecontrol.Controller;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;

// this class has one purpose - start event circle processing
public class Application {

    public static void main(String... args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        CommandFactory factory = context.getBean(CommandFactory.class);
        String rcID = "001";
        Controller controller = new Controller(rcID);
        controller.setCommandOnButton("A", factory.getCommand(CommandType.ALL_LIGHTS_OFF, rcID));
        controller.setCommandOnButton("4", factory.getCommand(CommandType.UNDO, rcID));
        RemoteControlRegistry registry = context.getBean(RemoteControlRegistry.class);
        registry.registerRemoteControl(controller);
        EventManager eventManager = context.getBean(EventManager.class);
        eventManager.runEventLoop();
    }

}
