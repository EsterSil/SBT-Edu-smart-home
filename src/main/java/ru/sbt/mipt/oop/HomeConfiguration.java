package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.loaders.SmartHomeLoader;
import ru.sbt.mipt.oop.loaders.fileloader.FileSmartHomeLoader;
import ru.sbt.mipt.oop.managers.EventManager;
import ru.sbt.mipt.oop.managers.EventManagerAdapter;
import ru.sbt.mipt.oop.processor.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@ComponentScan("ru.sbt.mipt.oop")
public class HomeConfiguration {


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

    @Bean
    public SmartHomeLoader smartHomeLoader() {
        FileSmartHomeLoader loader = new FileSmartHomeLoader();
        loader.setPath("smart-home-1.js");
        return loader;
    }

    @Bean
    public BasicSmartHome basicSmartHome(SmartHomeLoader smartHomeLoader) {
        BasicSmartHome smartHome = null;
        try {
            smartHome = smartHomeLoader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return smartHome;
    }

    @Bean
    public EventManager eventManager(BasicSmartHome smartHome) {
        EventManager manager = new EventManagerAdapter();
        configureManager(smartHome, manager);
        return manager;
    }

    private void configureManager(BasicSmartHome smartHome, EventManager manager) {
        Collection<HomeEventProcessor> processors = configureEventProcessors(smartHome);
        for (HomeEventProcessor p : processors) {
            manager.addEventProcessor(p);
        }
    }
}
