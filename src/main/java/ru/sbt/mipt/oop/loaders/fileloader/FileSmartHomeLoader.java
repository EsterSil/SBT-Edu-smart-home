package ru.sbt.mipt.oop.loaders.fileloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.loaders.SmartHomeLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@Component
public class FileSmartHomeLoader implements SmartHomeLoader {
@Override
    public BasicSmartHome load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return mapper.readValue(json, BasicSmartHome.class);
    }


}
