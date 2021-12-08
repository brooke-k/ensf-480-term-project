package ensf480.group14.dbcontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.print.Doc;
import javax.swing.text.TabStop;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObjectCodec;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.types.ObjectId;

import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.forms.PreferenceForm;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;

public class RegisteredRenterDBController implements DatabaseSubject {
    public static boolean databaseOpen; // Public to allow for any
    // calling function or class to mark it as "closed" if an exception
    // regarding the database is thrown.

    /*
     * private ArrayList<RegisteredRenter> registeredRenters;
     * private ArrayList<Manager> managers;
     * private ArrayList<Landlord> landlords;
     */

    private static MongoClient mongoClient;
    private static MongoDatabase dbMongo;
    protected static MongoCollection usersCollection;
    protected static MongoCollection propertiesCollection;
    protected static MongoCollection emailCollection;
    protected static MongoCollection preferenceCollection;

    private ArrayList<DatabaseObserver> observers;

    public RegisteredRenterDBController() {
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

            usersCollection = dbMongo.getCollection("users");
            propertiesCollection = dbMongo.getCollection("properties");
            emailCollection = dbMongo.getCollection("email");
            preferenceCollection = dbMongo.getCollection("preferences");

        }
    }

    @Override
    public void addObserver(DatabaseObserver dbo) {
        observers.add(dbo);
    }

    @Override
    public void removeObserver(DatabaseObserver dbo) {
        observers.remove(dbo);
    }

    @Override
    public void notifiyAllObservers() {
        for (DatabaseObserver o : observers) {
            o.getNotifiedOfDBChange();
        }

    }

    public void addUserToDatabase(String email, String password, String userType) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("email", email);
        FindIterable<Document> findIter = usersCollection.find(searchQuery);
        if (findIter.first() != null) {
            System.out.println("A user with the email \"" + email + "\" already exists.");
            System.out.println("The user with email \"" + email + "\" was not added to the database.");
            return;
        }
        MongoCursor<Document> resultCursor = findIter.iterator();
        if (resultCursor.hasNext()) { // Meaning the user already exists in the database and should
                                      // not be added as a duplicate
            System.out.println("A user with the email \"" + email + "\" already exists.");
            System.out.println("The user with email \"" + email + "\" was not added to the database.");
            resultCursor.close();
            return;
        }
        resultCursor.close();
        Document newUser = new Document("email", email);
        emailCollection.insertOne(newUser);
        newUser.append("password", password).append("type", userType);
        usersCollection.insertOne(newUser);
        System.out.println("User with email \"" + email + "\" added to the database.");

    }

    public void addPreferenceFormToDatabase(PreferenceForm pf) {
        if (pf.getID() != null) {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", pf.getID());
            FindIterable<Document> findIter = propertiesCollection.find(searchQuery);
            MongoCursor<Document> resultCursor = findIter.iterator();
            if (resultCursor.hasNext()) { // Meaning the preference form already exists in the
                                          // database and should not be added as a duplicate
                System.out.println("A preference form with the address \"" + pf.getID() +
                        "\" already exists.");
                System.out.println(
                        "The preference form with address \"" + pf.getID() + "\" was not added to the database.");
                resultCursor.close();
                return;
            }
            resultCursor.close();
        }

        Document newPreference = new Document("building_type", pf.getBuildingType());
        newPreference.append("bedrooms", pf.getNumOfBathrooms());
        newPreference.append("bathrooms", pf.getNumOfBathrooms());
        newPreference.append("furnished", (pf.isFurnished()));
        newPreference.append("city_quadrant", pf.getCityQuadrant());
        newPreference.append("max_price", pf.getMaxPrice());
        newPreference.append("min_price", pf.getMinPrice());
        newPreference.append("renter_id", pf.getRenterID());

        preferenceCollection.insertOne(newPreference);

    }

    // public ArrayList<Property> getMatchingProperties(Search searchInfo) {
    // return null;
    // }

    public ArrayList<Property> getPropertiesRentedIn(String startingDate,
            String endingDate) {
        return null;
    }

    public Property getPropertyWithLandlord(int landlordID) {
        return null;
    }

    public Property getPropertyByID(int iDnum) {
        return null;
    }

    public static void setDatabaseOpen(boolean databaseOpen) {
        RegisteredRenterDBController.databaseOpen = databaseOpen;
    }

    public static void sendEmail(Email email) {
        BasicDBObject dbo = new BasicDBObject();
        dbo.put("_id", email.getId());
        FindIterable<Document> docIter = emailCollection.find(dbo);
        if (docIter != null) { // Email already exists
            return;
        }
        emailCollection.insertOne(Email.toDocument(email));
    }

    public static boolean userHasEmails(String userEmail) {
        BasicDBObject dbo = new BasicDBObject();
        FindIterable<Document> docIter = emailCollection
                .find(Filters.and(Filters.eq("email", userEmail), Filters.eq("read", Boolean.FALSE)));
        return docIter != null;
    }

    public static void deleteEmail(ObjectId emailID) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", emailID);
        emailCollection.deleteMany(query);
        return;
    }

    public static ArrayList<Email> getAllEmails(String userEmail) {
        BasicDBObject query = new BasicDBObject();
        query.put("dest_addr", userEmail);
        FindIterable<Document> docIter = emailCollection.find(query);
        MongoCursor<Document> iter = docIter.cursor();
        if (!iter.hasNext()) {
            return null;
        }

        ArrayList<Email> emails = new ArrayList<Email>(0);
        while (iter.hasNext()) {
            emails.add(Email.getEmail(iter.next()));

        }
        if (emails.isEmpty()) {
            return null;
        }
        return emails;
    }

    public static void deleteAllEmails(String userEmail) {
        BasicDBObject query = new BasicDBObject();
        query.put("dest_addr", userEmail);
        emailCollection.deleteMany(query);
    }

    public static void deleteAllReadEmails(String userEmail) {
        emailCollection.deleteMany(Filters.and(Filters.eq("dest_addr", userEmail), Filters.eq("read", Boolean.TRUE)));
    }

    public static ArrayList<Email> getAllUnreadEmails(String userEmail) {
        FindIterable<Document> docIter = emailCollection
                .find(Filters.and(Filters.eq("dest_addr", userEmail), Filters.eq("read", Boolean.TRUE)));
        MongoCursor<Document> iter = docIter.iterator();
        if (!iter.hasNext()) {
            return null;
        }
        ArrayList<Email> emails = new ArrayList<>(0);
        while (iter.hasNext()) {
            emails.add(Email.getEmail(iter.next()));
        }
        return emails;
    }

    /*
     * public String checkLogin(String email, String password) {
     * BasicDBObject query = new BasicDBObject();
     * query.append("email", email).append("password", password);
     * FindIterable<Document> docIter = usersCollection.find(query);
     * MongoCursor<Document> iter = docIter.iterator();
     * if (!iter.hasNext()) { // User with email does not exist
     * return null;
     * }
     * User user;
     * // if(renter){
     * // User user = new RegisteredRenter();
     * // else if (landlord)
     * // User user = new Landlord();
     *
     * Document foundUser = docIter.first();
     * String userType = foundUser.get("type").toString();
     *
     * if (userType.equals("registered_renter")) {
     * user = new RegisteredRenter();
     * // ((PreferenceForm)
     * // user).setBuildingType(foundUser.getString("building_type"));
     *
     * }
     * }
     */
    // public ArrayList<RegisteredRenter> getRegisteredRenters() {
    // return registeredRenters;
    // }

    // public void setRegisteredRenters(ArrayList<RegisteredRenter>
    // registeredRenters) {
    // this.registeredRenters = registeredRenters;
    // }

    // public void setManagers(ArrayList<Manager> managers) {
    // this.managers = managers;
    // }

    // public ArrayList<Landlord> getLandlords() {
    // return landlords;
    // }

    // public void setLandlords(ArrayList<Landlord> landlords) {
    // this.landlords = landlords;
    // }

    public ArrayList<Property> getAllProperties() {
        ArrayList<Property> propArray = new ArrayList<>(0);
        FindIterable<Document> docIter = propertiesCollection.find();
        MongoCursor<Document> iter = docIter.iterator();
        while (iter.hasNext()) {
            propArray.add(Property.getProperty(iter.next()));
        }
        return propArray;
    }

    public Document getFirstObject(String field, String content, String collection) {
        BasicDBObject query = new BasicDBObject();
        query.put(field, content);
        FindIterable<Document> docIter;
        if (collection.equals("users")) {
            docIter = usersCollection.find(query);
            return docIter.first();
        } else if (collection.equals("properties")) {
            docIter = propertiesCollection.find(query);
            return docIter.first();
        } else if (collection.equals("preferences")) {
            docIter = preferenceCollection.find(query);
            return docIter.first();
        } else if (collection.equals("email")) {
            docIter = emailCollection.find(query);
            return docIter.first();
        } else {
            return null;
        }
    }

}
