package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.command.CommandFactory;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.managers.EventManager;
import ru.sbt.mipt.oop.remotecontrol.Controller;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlFactory;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;

public class Application {

    public static void main(String... args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        CommandFactory factory = context.getBean(CommandFactory.class);
        RemoteControlFactory rcFactory = context.getBean(RemoteControlFactory.class);
        Controller controller = rcFactory.newController();

        controller.setCommandOnButton("A", factory.getCommand(CommandType.ALL_LIGHTS_ON));
        controller.setCommandOnButton("4", factory.getUndoCommandForController(controller));
        System.out.println("Execute AllLightsOn");
        controller.onButtonPressed("A");
        System.out.println("Execute Undo");
        controller.onButtonPressed("4");

        System.out.println("________Starts event manager______");
        RemoteControlRegistry registry = context.getBean(RemoteControlRegistry.class);
        registry.registerRemoteControl(controller);
        EventManager eventManager = context.getBean(EventManager.class);
        eventManager.runEventLoop();
    }

}
