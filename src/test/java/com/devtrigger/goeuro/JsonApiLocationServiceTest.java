package com.devtrigger.goeuro;

import com.devtrigger.goeuro.entity.Location;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class JsonApiLocationServiceTest {

    JsonApiLocationService service = new JsonApiLocationService();

    @Test
    public void testCity() throws IOException {
        List<Location> locations = service.getLocations("Berlin");
        assertTrue(locations.size() > 1);
        Location location = locations.get(0);
        assertNotEquals(location.getId(), 0);
        assertNotNull(location.getCountry());
        assertNotNull(location.getFullName());
        assertNotNull(location.getName());
        assertNotNull(location.getType());
        assertNotNull(location.getGeoPosition());
        assertNotNull(location.getGeoPosition().getLatitude());
        assertNotNull(location.getGeoPosition().getLongitude());
    }

    @Test
    public void testNonexistentCity() throws IOException {
        List<Location> locations = service.getLocations("Somethingnonexistent");
        assertTrue(locations.isEmpty());
    }
}
