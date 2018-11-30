package ru.sbt.mipt.oop.loaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.homecomponents.Room;
import ru.sbt.mipt.oop.loarers.SmartHomeLoader;
import ru.sbt.mipt.oop.loarers.fileloader.FileSmartHomeLoader;

import java.io.IOException;
import java.util.ArrayList;

public class FileSmartHomeLoaderTest {

    private SmartHomeLoader loader = new FileSmartHomeLoader();

    @Test
    void loadTest() {
        BasicSmartHome home = null;
        try {
            home = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(home);
        Assertions.assertEquals(4, home.getComponents().size());
        Assertions.assertTrue(((ArrayList<HomeComponent>) home.getComponents()).get(0) instanceof Room);


    }
}
