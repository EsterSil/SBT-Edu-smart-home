package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.sbt.mipt.oop.command.CommandFactory;
import ru.sbt.mipt.oop.command.CommandHistory;
import ru.sbt.mipt.oop.loaders.SmartHomeLoader;
import ru.sbt.mipt.oop.loaders.fileloader.FileSmartHomeLoader;
import ru.sbt.mipt.oop.managers.EventManager;
import ru.sbt.mipt.oop.managers.EventManagerAdapter;
import ru.sbt.mipt.oop.processor.*;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@ComponentScan
public class HomeConfiguration {

    private BasicSmartHome smartHome;
    private EventManager manager;

    public HomeConfiguration() {
    }
    @Bean
    public CommandHistory commandHistory() {
        return new CommandHistory();
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Autowired
    public BasicSmartHome basicSmartHome(SmartHomeLoader loader) {
        try {
            this.smartHome = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return smartHome;
    }

    //@Bean
    @Autowired
    public CommandFactory commandFactory(BasicSmartHome basicSmartHome) {
        //if (smartHome == null) basicSmartHome(new FileSmartHomeLoader());
        return new CommandFactory(basicSmartHome);
    }

    @Bean
    public EventManager eventManager() {
        manager = new EventManagerAdapter();
        if (smartHome == null) {
            basicSmartHome(new FileSmartHomeLoader());
        }
        configureManager(smartHome);
        return manager;
    }


    private void configureManager(BasicSmartHome smartHome) {
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
