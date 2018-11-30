package ru.sbt.mipt.oop.loaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.homecomponents.HomeComponent;
import ru.sbt.mipt.oop.homecomponents.Room;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class JacksonFileLoaderTest {

    @Test
    void deserializeTest() {
        BasicSmartHome home = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            home = mapper.readValue(new File("smart-home-1.js"), BasicSmartHome.class);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Assertions.assertNotNull(home);
        Assertions.assertEquals(4, home.getComponents().size());
        Assertions.assertTrue(((ArrayList<HomeComponent>) home.getComponents()).get(0) instanceof Room);

    }
}
