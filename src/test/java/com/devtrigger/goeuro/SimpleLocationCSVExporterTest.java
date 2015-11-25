package com.devtrigger.goeuro;

import com.devtrigger.goeuro.entity.Location;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleLocationCSVExporterTest {

    SimpleLocationCSVExporter exporter = new SimpleLocationCSVExporter();

    private static final String FILE_NAME = "test.csv";

    @After
    public void clearFile() throws IOException {
        Files.deleteIfExists(Paths.get(FILE_NAME));
    }

    @Test
    public void testBasicExport() throws IOException {

        Location location1 = new Location.Builder()
                .id(1)
                .name("Berlin")
                .country("Germany")
                .fullName("Berlin")
                .type("position")
                .geoPosition(52.39886, 13.06566).build();

        Location location2 = new Location.Builder()
                .id(2)
                .name("Moscow")
                .country("Russia")
                .fullName("Moscow")
                .type("position")
                .geoPosition(42.39886, 23.06566).build();

        exporter.export(FILE_NAME, Arrays.asList(location1, location2));

        List<String> lines = Files.readAllLines(Paths.get(FILE_NAME), Charset.defaultCharset());

        assertEquals(lines.size(), 3);
        assertEquals(lines.get(0), "_id,name,type,latitude,longitude");
        assertEquals(lines.get(1), "1,Berlin,position,52.39886,13.06566");
        assertEquals(lines.get(2), "2,Moscow,position,42.39886,23.06566");
    }
}