package com.noppanit.csv;

import au.com.bytecode.opencsv.CSVReader;
import com.mongodb.BasicDBObject;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MetaDataReader extends IReadCSV {
    private final CSVReader reader;
    private final int eanIndex;
    private final int itemNumberIndex;
    private final int descriptionIndex;

    public MetaDataReader() throws IOException {
        reader = new CSVReader(new InputStreamReader(new FileInputStream("resource/metadata.csv"), "UTF-8"));

        List<String> headers = Arrays.asList(reader.readNext());

        eanIndex = getHeader(headers, "EAN");
        itemNumberIndex = getHeader(headers, "ITEM_NUMBER");
        descriptionIndex = getHeader(headers, "DESCRIPTION_1");
    }

    @Override
    public BasicDBObject read() throws IOException {
        String[] row = reader.readNext();

        if (row == null || row.length <= 1) return null;

        String itemNumber = row[itemNumberIndex];
        String name = row[eanIndex];
        String description = row[descriptionIndex];

        BasicDBObject metadata = new BasicDBObject();
        metadata.put("ean", name);
        metadata.put("itemNumber", itemNumber);
        metadata.put("description_1", description);

        return metadata;
    }
}
