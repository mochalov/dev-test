package com.devtrigger.goeuro;

import com.devtrigger.goeuro.entity.Location;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

/**
 * Exporter that writes locations main information to a CSV file using {@link java.io.FileWriter}
 */
public class SimpleLocationCSVExporter implements CsvExporter<Location> {

    private static final String FIELD_DELIMITER = ",";
    private static final String HEADER = "_id,name,type,latitude,longitude";

    @Override
    public void export(String fileName, Collection<Location> locations) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(HEADER);
            writer.newLine();
            for (Location location: locations) {
                writeLocation(writer, location);
                writer.newLine();
            }
        }
    }

    private void writeLocation(Writer writer, Location location) throws IOException {
        writer.write(String.valueOf(location.getId()));
        writer.write(FIELD_DELIMITER);
        writer.write(location.getName());
        writer.write(FIELD_DELIMITER);
        writer.write(location.getType());
        writer.write(FIELD_DELIMITER);
        writer.write(String.valueOf(location.getGeoPosition().getLatitude()));
        writer.write(FIELD_DELIMITER);
        writer.write(String.valueOf(location.getGeoPosition().getLongitude()));
    }
}
