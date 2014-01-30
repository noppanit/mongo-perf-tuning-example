package com.noppanit.perf;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.noppanit.core.MongoResource;
import com.noppanit.csv.MetaDataReader;
import com.noppanit.csv.ProductReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is going to be bulk insert
 */
public class Performance2 {

    public static void main(String args[]) throws IOException {
        DBCollection dbCollection = new MongoResource().getCollection();

        ProductReader productReader = new ProductReader();
        BasicDBObject product;
        System.out.println("Inserting products ...");
        List<DBObject> products = new ArrayList<DBObject>();
        while((product = productReader.read()) != null) {
            products.add(product);
        }
        dbCollection.insert(products);
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
