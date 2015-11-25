package com.devtrigger.goeuro;

import com.devtrigger.goeuro.entity.Location;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ApplicationTest {

    LocationService locationService = mock(LocationService.class);

    @SuppressWarnings("unchecked")
    CsvExporter<Location> csvExporter = (CsvExporter<Location>) mock(CsvExporter.class);

    Application app = new Application(locationService, csvExporter);

    @Test
    public void testWrongInput() {
        assertEquals(app.run(new String[] {}),
                "Wrong utility usage: it's expected to pass one city name parameter, e.g. java -jar GoEuroTest.jar \"CITY_NAME\"");
    }

    @Test
    public void testEmptyLocationsList() throws IOException {
        when(locationService.getLocations("Somecity")).thenReturn(new ArrayList<Location>());
        assertEquals(app.run(new String[]{"Somecity"}), "Locations list is empty for the city. Please choose another one.");
        verify(locationService).getLocations("Somecity");
        verify(csvExporter, never()).export(anyString(), anyCollectionOf(Location.class));
    }

    @Test
    public void testCorrectWork() throws IOException {
        Location location = new Location.Builder()
                .id(1)
                .name("Berlin")
                .country("Germany")
                .fullName("Berlin")
                .type("position")
                .geoPosition(52.39886, 13.06566).build();
        List<Location> locations = Collections.singletonList(location);

        when(locationService.getLocations("Berlin")).thenReturn(locations);
        assertEquals(app.run(new String[]{"Berlin"}), "CSV file Berlin.csv is successfully created");
        verify(locationService).getLocations("Berlin");
        verify(csvExporter).export("Berlin.csv", locations);
    }
}
