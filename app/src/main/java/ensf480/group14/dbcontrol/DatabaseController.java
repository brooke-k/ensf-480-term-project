package ensf480.group14.dbcontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import ensf480.group14.external.Property;
import ensf480.group14.forms.Search;
import ensf480.group14.users.Landlord;
import ensf480.group14.users.Manager;
import ensf480.group14.users.RegisteredRenter;

public class DatabaseController implements DatabaseSubject {
    public static boolean databaseOpen; // Public to allow for any
    // calling function or class to mark it as "closed" if an exception
    // regarding the database is thrown.

    private ArrayList<RegisteredRenter> registeredRenters;
    private ArrayList<Manager> managers;
    private ArrayList<Landlord> landlords;

    private static MongoClient mongoClient;
    private static MongoDatabase dbMongo;
    private static MongoCollection usersCollection;
    private static MongoCollection propertiesCollection;
    private static MongoCollection emailCollection;

    public DatabaseController() {
        mongoClient = null;
        createConnection();
    }

    private void createConnection() {
        if (mongoClient == null) {
            File connectionFile = new File("./connection.txt");
            if (!connectionFile.exists()) {
                System.err.println("Connection information file \"./connection\" could not be located.");
                System.err.println("Database is not accessible at this time.");
                return;
            }
            String[] mongoStr = new String[3];
            String rString = "";
            String dbName = "";
            try {
                BufferedReader connectionReader = new BufferedReader(new FileReader(connectionFile));

                rString = connectionReader.readLine();
                int i = 0;
                while (i < 3) {
                    mongoStr[i] = rString;
                    rString = connectionReader.readLine();
                    i++;
                }
                if (rString != null) {
                    dbName = rString;
                }
                connectionReader.close();
            } catch (IOException e) {
                System.err.println("File \"./connection\" could not be read: In DatabaseController.createConnection()");
                e.printStackTrace();
                return;
            }

            String connectionURL = "";
            for (String m : mongoStr) {
                connectionURL = connectionURL + m;
            }

            mongoClient = MongoClients.create(connectionURL);
            dbMongo = mongoClient.getDatabase(dbName);

            usersCollection = dbMongo.getCollection("Users");
            propertiesCollection = dbMongo.getCollection("Properties");
            emailCollection = dbMongo.getCollection("email");
        }
    }

    @Override
    public void addObserver(DatabaseObserver dbo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeObserver(DatabaseObserver dbo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifiyAllObservers() {
        // TODO Auto-generated method stub

    }

    private void printUsers() {
        FindIterable<Document> docIterator = usersCollection.find();
        Iterator<Document> collectionIter = docIterator.iterator();
        while (collectionIter.hasNext()) {
            System.out.println(collectionIter.next());
        }

    }

    private void printEmail() {
        FindIterable<Document> docIterator = emailCollection.find();
        Iterator<Document> collectionIter = docIterator.iterator();
        while (collectionIter.hasNext()) {
            System.out.println(collectionIter.next());
        }

    }

    private void printProperties() {
        FindIterable<Document> docIterator = propertiesCollection.find();
        Iterator<Document> collectionIter = docIterator.iterator();
        while (collectionIter.hasNext()) {
            System.out.println(collectionIter.next());
        }

    }

    public ArrayList<Property> getMatchingProperties(Search searchInfo) {
        return null;
    }

    public ArrayList<Property> getPropertiesRentedIn(Calendar startingDate, Calendar endingDate) {
        return null;
    }

    public Property getPropertyWithLandlord(int landlordID) {
        return null;
    }

    public Property getPropertyByID(int iDnum) {
        return null;
    }

           

    }

    public static void setDatabaseOpen(boolean databaseOpen) {
        DatabaseController.databaseOpen = databaseOpen;
    }

    public ArrayList<RegisteredRenter> getRegisteredRenters() {
        return registeredRenters;
    }

    public void setRegisteredRenters(ArrayList<RegisteredRenter> registeredRenters) {
        this.registeredRenters = registeredRenters;
    }

    p

        }

    

    
    public void setManagers(ArrayList<Manager> managers) {
        this.managers = managers;
    }

    public ArrayList<Landlord> getLandlords() {
        return landlords;
    }

    public void setLandlords(ArrayList<Landlord> landlords) {
        this.landlords = landlords;
    }

    public ArrayList<String> getAllProperties() {
        return null;
    }

    public ArrayList<String> getAllUsers() {
        return null;
    }

    public ArrayList<String> getAllEmails() {
        return null;
    }

}
