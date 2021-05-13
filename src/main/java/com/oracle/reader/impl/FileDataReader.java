package com.oracle.reader.impl;

import com.oracle.model.Data;
import com.oracle.reader.DataReader;
import com.oracle.utils.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDataReader implements DataReader {

    private static final Logger LOGGER = Logger.getLogger(FileDataReader.class.getName());

    @Override
    public List<Data> readData(String location) {
        try {

            List<Data> dataList = FileUtils.readFileAndTransform(location);

            return dataList;
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Exception occurred during reading file.", ex);
        }
        return null;
    }
}
