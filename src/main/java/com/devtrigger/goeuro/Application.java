package com.devtrigger.goeuro;

import com.devtrigger.goeuro.entity.Location;

import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

/**
 * Main application class. Defines base execution flow, input validation and interaction with a user.
 */
public class Application {

    private final LocationService locationService;
    private final CsvExporter<Location> csvExporter;

    Application() {
        this(new JsonApiLocationService(), new SimpleLocationCSVExporter());
    }

    Application(LocationService locationService, CsvExporter<Location> csvExporter) {
        this.locationService = locationService;
        this.csvExporter = csvExporter;
    }

    public static void main(String[] args) {
        Application app = new Application();
        out.println(app.run(args));
    }

    String run(String[] args) {
        if (args.length != 1 || args[0].isEmpty()) {
            return "Wrong utility usage: it's expected to pass one city name parameter, " +
                    "e.g. java -jar GoEuroTest.jar \"CITY_NAME\"";
        }
        String cityName = args[0];

        out.println("Loading locations for city: " + cityName + "...");
        List<Location> locations;
        try {
            locations = locationService.getLocations(cityName);
        } catch (IOException e) {
            return "Unable to load locations, error: " + e.getMessage();
        }
        if (locations.isEmpty())
            return "Locations list is empty for the city. Please choose another one.";

        out.println(locations.size() + " found, writing to csv file...");
        String fileName = cityName + ".csv";
        try {
            csvExporter.export(fileName, locations);
        } catch (IOException e) {
            return "Unable to write a csv file, error: " + e;
        }
        return "CSV file " + fileName + " is successfully created";
    }
}
