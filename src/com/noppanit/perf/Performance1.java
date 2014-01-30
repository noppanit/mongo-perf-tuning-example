package com.noppanit.perf;

import com.mongodb.*;
import com.noppanit.core.MongoResource;
import com.noppanit.csv.MetaDataReader;
import com.noppanit.csv.ProductReader;

import java.io.IOException;

/**
 * This is going to be just insert one by one
 */
public class Performance1 {

    public static void main(String args[]) throws IOException {
        DBCollection dbCollection = new MongoResource().getCollection();

        ProductReader productReader = new ProductReader();
        BasicDBObject product;
        System.out.println("Inserting products ...");
        while((product = productReader.read()) != null) {
            dbCollection.save(product);
        }
        System.out.println("Finished.");

        MetaDataReader metaDataReader = new MetaDataReader();
        BasicDBObject metadata;

        System.out.println("Updating products ...");
        while((metadata = metaDataReader.read()) != null) {
            BasicDBObject existingProduct = new BasicDBObject("itemNumber", metadata.get("itemNumber"));
            dbCollection.update(existingProduct, metadata, true, false);
        }
        System.out.println("Finished.");
    }
}
