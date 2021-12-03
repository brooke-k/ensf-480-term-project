package com.mongodb.quickstart;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    private MongoDatabase db;
    private MongoCollection users;
    private MongoCollection properties;
    public MongoDatabase getDb() {
        return db;
    }

    public void setDb(MongoDatabase db) {
        this.db = db;
    }

    public MongoCollection getUsers() {
        return users;
    }

    public void setUsers(MongoCollection users) {
        this.users = users;
    }

    public MongoCollection getProperties() {
        return properties;
    }

    public void setProperties(MongoCollection properties) {
        this.properties = properties;
    }

    public MongoCollection getEmail() {
        return email;
    }

    public void setEmail(MongoCollection email) {
        this.email = email;
    }

    private MongoCollection email;
    
    public MongoCollection getUserCollection(){
        return this.users;
    }

    public Connection(){
        this.db
    }

    public static void main(String[] args) {
        File file = new File("./connection.txt");
        BufferedReader br;
        String st = "";
        try {
            br = new BufferedReader(new FileReader(file));
            st = br.readLine();
        } catch (IOException e) {
            
        }

        System.out.println(st);
    
        
        String connectionString = "mongodb+srv://480team:" + st + "@cluster0.socxq.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase db = mongoClient.getDatabase("480project");
            MongoCollection users = db.getCollection("Users");
            MongoCollection properties = db.getCollection("Properties")
            Document renter = new Document("_id", new ObjectId());
            renter.append("username", "nick").append("password", "niceTry");
            users.insertOne(renter);
        }
    }
}