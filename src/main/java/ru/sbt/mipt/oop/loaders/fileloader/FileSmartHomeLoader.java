package ru.sbt.mipt.oop.loaders.fileloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.loaders.SmartHomeLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader {
    private String path;


    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public BasicSmartHome load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String json = new String(Files.readAllBytes(Paths.get(path)));
        return mapper.readValue(json, BasicSmartHome.class);
    }


}
