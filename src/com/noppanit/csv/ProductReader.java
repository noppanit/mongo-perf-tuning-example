package com.noppanit.csv;

import au.com.bytecode.opencsv.CSVReader;
import com.mongodb.BasicDBObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ProductReader extends IReadCSV {

    private final int itemNumberIndex;
    private final int nameIndex;
    private final int descriptionIndex;
    private final CSVReader reader;

    public ProductReader() throws IOException {
        reader = new CSVReader(new InputStreamReader(new FileInputStream("resource/products.csv"), "UTF-8"));

        List<String> headers = Arrays.asList(reader.readNext());

        itemNumberIndex = getHeader(headers, "ITEM_NUMBER");
        nameIndex = getHeader(headers, "NAME");
        descriptionIndex = getHeader(headers, "DESCRIPTION");

    }

    @Override
    public BasicDBObject read() throws IOException {
        String[] row = reader.readNext();

        if (row == null || row.length <= 1) return null;

        String itemNumber = row[itemNumberIndex];
        String name = row[nameIndex];
        String description = row[descriptionIndex];

        BasicDBObject product = new BasicDBObject();
        product.put("itemNumber", itemNumber);
        product.put("name", name);
        product.put("description", description);

        return product;
    }
}
