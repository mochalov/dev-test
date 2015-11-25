package com.devtrigger.goeuro;

import java.io.IOException;
import java.util.Collection;

/**
 * An exporter that allows to write collections of entities to a CSV file.
 */
public interface CsvExporter<T> {

    /**
     * Exports collection of items into a CSV file
     * @param fileName name of CSV file created. If a file with the same name already exists, it will be overridden
     * @param data collection of items to export
     * @throws IOException
     */
    void export(String fileName, Collection<T> data) throws IOException;
}
