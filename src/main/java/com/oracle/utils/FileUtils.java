package com.oracle.utils;

import com.oracle.model.Data;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    /**
     * Read the file specified and transform in to Data model
     * @param fileName File to be read and transform
     * @return List of transformed data.
     * @throws IOException
     */
    public static List<Data> readFileAndTransform(String fileName) throws IOException {
        return Files.lines(FileSystems.getDefault().getPath(fileName))
                .map(FileUtils::transform)
                .collect(Collectors.toList());

    }

    /**
     * Transform input string to Data.
     * @param input
     * @return
     */
    static Data transform(String input) {
        String[] details = input.split(",");
        if (details.length != 6) {
            return null;
        }
        String duration = details[5].substring(0, details[5].indexOf('s'));
        return new Data(Integer.parseInt(details[0]), Integer.parseInt(details[1]), details[2], details[3],
                details[4], Integer.parseInt(duration));
    }
}
