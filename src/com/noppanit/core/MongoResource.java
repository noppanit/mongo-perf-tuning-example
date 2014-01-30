package com.noppanit.core;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;

public class MongoResource {

    private final DB mongoClientDB;

    public MongoResource() throws UnknownHostException {

        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        mongoClient.getDB("perfTest").dropDatabase();

        mongoClientDB = mongoClient.getDB("perfTest");

    }

    public DBCollection getCollection() {
        return mongoClientDB.getCollection("products");
    }

}
