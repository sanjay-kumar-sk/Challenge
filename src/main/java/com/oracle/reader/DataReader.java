package com.oracle.reader;

import com.oracle.model.Data;

import java.util.List;

public interface DataReader {

    List<Data> readData(String location);
}
