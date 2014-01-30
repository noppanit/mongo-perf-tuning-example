package com.noppanit.csv;

import com.mongodb.BasicDBObject;

import java.io.IOException;
import java.util.List;

public abstract class IReadCSV {
    abstract BasicDBObject read() throws IOException;

    public int getHeader(List<String> headers, String columnName) {
        int columnIndex = headers.indexOf(columnName);
        if (columnIndex == -1) {
            throw new RuntimeException(columnName + " is not found");
        }

        return columnIndex;

    }
}
