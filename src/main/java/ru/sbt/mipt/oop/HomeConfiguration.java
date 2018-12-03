package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.sbt.mipt.oop.command.CommandFactory;
import ru.sbt.mipt.oop.command.CommandHistory;
import ru.sbt.mipt.oop.command.CommandHistoryImpl;
import ru.sbt.mipt.oop.loaders.SmartHomeLoader;
import ru.sbt.mipt.oop.loaders.fileloader.FileSmartHomeLoader;
import ru.sbt.mipt.oop.managers.EventManager;
import ru.sbt.mipt.oop.managers.EventManagerAdapter;
import ru.sbt.mipt.oop.processor.*;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlFactory;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@ComponentScan
public class HomeConfiguration {

    //private BasicSmartHome smartHome;
    //private EventManager manager;

    public HomeConfiguration() {
    }
    @Bean
    public SmartHomeLoader smartHomeLoader() {
        FileSmartHomeLoader loader =  new FileSmartHomeLoader();
        loader.setPath("smart-home-1.js");
        return loader;
    }
    @Bean
    public CommandHistory commandHistory() {
        return new CommandHistoryImpl();
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Bean
    public BasicSmartHome basicSmartHome() {
        BasicSmartHome smartHome = null;
        try {
            smartHome = smartHomeLoader().load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return smartHome;
    }
    @Bean
    public RemoteControlFactory remoteControlFactory() {
        return new RemoteControlFactory(commandHistory());
    }

    @Bean
    public CommandFactory commandFactory(SmartHomeLoader loader) {
        return new CommandFactory(basicSmartHome(), commandHistory());
    }

    @Bean
    public EventManager eventManager(SmartHomeLoader loader) {
        EventManager manager = new EventManagerAdapter();
        configureManager(basicSmartHome(), manager);
        return manager;
    }


    private void configureManager(BasicSmartHome smartHome, EventManager manager) {
        Collection<HomeEventProcessor> processors = configureEventProcessors(smartHome);
        for (HomeEventProcessor p : processors) {
            manager.addEventProcessor(p);
        }
    }

    private static Collection<HomeEventProcessor> configureEventProcessors(BasicSmartHome smartHome) {
        Collection<HomeEventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new LightEventProcessor(smartHome),
                smartHome), smartHome));
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new DoorEventProcessor(smartHome), smartHome),
                smartHome));
        eventProcessors.add(new SMSSenderDecorator(new SignalingDecorator(new HallEventProcessor(smartHome), smartHome),
                smartHome));
        eventProcessors.add(new SignalingEventProcessor(smartHome));
        return eventProcessors;
    }
}
