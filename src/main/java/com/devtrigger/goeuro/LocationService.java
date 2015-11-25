package com.devtrigger.goeuro;

import com.devtrigger.goeuro.entity.Location;

import java.io.IOException;
import java.util.List;

/**
 * Defines a service that provides locations to the application
 */
public interface LocationService {

    /**
     * Loads a list of locations for a city specified
     * @param cityName name of a city
     * @return a list of locations or empty list if no locations were found
     * @throws java.io.IOException if underlying provider cannot load an information requested
     */
    List<Location> getLocations(String cityName) throws IOException;
}
