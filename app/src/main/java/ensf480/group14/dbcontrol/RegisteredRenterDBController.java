/**
 * File: RegisteredRenterDBController.java
 * ENSF 480, Fall 2021
 * Term Project
 * Lecture Section: L02
 * Instructor: M. Moshirpour
 * Group 14
 * @author Khosla, Abhay
 * @author Kindleman, Brooke
 * @author Knapton, Nicholas
 * @author Kramer, Brian
 * Created: Dec 2021
 * @version 1.0
 */

package ensf480.group14.dbcontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;

import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.forms.PreferenceForm;
import ensf480.group14.forms.Search;
import ensf480.group14.users.Landlord;
import ensf480.group14.users.Manager;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;

public class RegisteredRenterDBController implements DatabaseSubject {
    public static boolean databaseOpen; // Public to allow for any
    // calling function or class to mark it as "closed" if an exception
    // regarding the database is thrown.

    protected static MongoClient mongoClient;
    protected static MongoDatabase dbMongo;
    protected static MongoCollection<Document> usersCollection;
    protected static MongoCollection<Document> propertiesCollection;
    protected static MongoCollection<Document> emailCollection;
    protected static MongoCollection<Document> preferenceCollection;
    protected static MongoCollection<Document> feeCollection;
    protected static MongoCollection<Document> logCollection;

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
            feeCollection = dbMongo.getCollection("fee");
            logCollection = dbMongo.getCollection("log");
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

    public boolean addUserToDatabase(String email, String password, String userType) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("email", email);
        FindIterable<Document> findIter = usersCollection.find(searchQuery);
        if (findIter.first() != null) {
            System.out.println("A user with the email \"" + email + "\" already exists.");
            System.out.println("The user with email \"" + email + "\" was not added to the database.");
            return false;
        }
        MongoCursor<Document> resultCursor = findIter.iterator();
        if (resultCursor.hasNext()) { // Meaning the user already exists in the database and should
                                      // not be added as a duplicate
            System.out.println("A user with the email \"" + email + "\" already exists.");
            System.out.println("The user with email \"" + email + "\" was not added to the database.");
            resultCursor.close();
            return false;
        }
        resultCursor.close();
        Document newUser = new Document("email", email);
        emailCollection.insertOne(newUser);
        newUser.append("password", password).append("type", userType);
        usersCollection.insertOne(newUser);
        System.out.println("User with email \"" + email + "\" added to the database.");
        return true;
    }

    public boolean addUserToDatabase(String email, String password, String userType, String firstName,
            String lastName) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("email", email);
        FindIterable<Document> findIter = usersCollection.find(searchQuery);
        if (findIter.first() != null) {
            System.out.println("A user with the email \"" + email + "\" already exists.");
            System.out.println("The user with email \"" + email + "\" was not added to the database.");
            return false;
        }
        MongoCursor<Document> resultCursor = findIter.iterator();
        if (resultCursor.hasNext()) { // Meaning the user already exists in the database and should
                                      // not be added as a duplicate
            System.out.println("A user with the email \"" + email + "\" already exists.");
            System.out.println("The user with email \"" + email + "\" was not added to the database.");
            resultCursor.close();
            return false;
        }
        resultCursor.close();
        Document newUser = new Document("email", email);
        emailCollection.insertOne(newUser);
        newUser.append("password", password).append("type", userType).append("first_name", firstName)
                .append("last_name", lastName);
        usersCollection.insertOne(newUser);
        System.out.println("User with email \"" + email + "\" added to the database.");
        return true;
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
        newPreference.append("city_quad", pf.getCityQuadrant());
        newPreference.append("max_price", pf.getMaxPrice());
        newPreference.append("min_price", pf.getMinPrice());
        newPreference.append("renter_id", pf.getRenterID());

        preferenceCollection.insertOne(newPreference);

    }

    // public ArrayList<Property> getMatchingProperties(Search searchInfo) {
    // return null;
    // }

    public ArrayList<Property> getPropertiesIn(String startingDate,
            String endingDate) {
        return null;
    }

    public ArrayList<Property> getPropertyWithLandlord(ObjectId landlordID) {
        ArrayList<Property> propArray = new ArrayList<>(0);
        FindIterable<Document> docIter = propertiesCollection.find(new Document("landlord_id", landlordID));
        MongoCursor<Document> iter = docIter.iterator();
        while (iter.hasNext()) {
            propArray.add(Property.getProperty(iter.next()));
        }
        return propArray;
    
    }

    public Property getPropertyByID(int iDnum) {
        return null;
    }

    public static void setDatabaseOpen(boolean databaseOpen) {
        RegisteredRenterDBController.databaseOpen = databaseOpen;
    }

    // Returns null if no properties found.
    public ArrayList<Property> searchProperties(Search searchForm) {
        BasicDBObject criteria = new BasicDBObject();
        if(searchForm.getBuildingType() != null && !searchForm.getBuildingType().equals("")){
            criteria.append("type", searchForm.getBuildingType());
            
        }
        if(searchForm.getNumOfBedrooms() != 0){
            criteria.append("bedrooms", searchForm.getNumOfBedrooms());
        }
        if(searchForm.getNumOfBathrooms() != 0){
            criteria.append("bathrooms", searchForm.getNumOfBathrooms());
        }
        if(searchForm.getCityQuadrant() != null  && !searchForm.getCityQuadrant().equals("")){
            criteria.append("city_quad", searchForm.getCityQuadrant());
        }
        
        criteria.append("visible_to_renters", true);

        if(!searchForm.getFurnished().equals("")){
            criteria.append("furnished", searchForm.getFurnished().equals("Furnished") ? true : false);   
        }


        ArrayList<Property> resultArray = new ArrayList<Property>(0);
        FindIterable<Document> findIter = propertiesCollection.find(criteria);
        MongoCursor<Document> resultCursor = findIter.iterator();
        while (resultCursor.hasNext()) {
            resultArray.add(Property.getProperty(resultCursor.next()));   
        }

        if(searchForm.getMaxPrice() > 0){
            resultArray.removeIf(s -> s.getRentCost() > searchForm.getMaxPrice());
        }
        resultArray.removeIf(s -> s.getRentCost() < searchForm.getMinPrice());

        // for(int i = 0;i<resultArray.size();i++){
        //     if(re)
        // }

        return resultArray;
    }

    public static void sendEmail(Email email) {
        Document newEmail = new Document("read", Boolean.FALSE);
        newEmail.put("recipient", email.getRecipient());
        newEmail.put("sender", email.getSender());
        newEmail.put("body", email.getBody());
        newEmail.put("subject", email.getSubject());
        newEmail.put("_id", new ObjectId());
        emailCollection.insertOne(newEmail);
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
        query.put("recipient", userEmail);
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
        query.put("recipient", userEmail);
        emailCollection.deleteMany(query);
    }

    public static void deleteAllReadEmails(String userEmail) {
        emailCollection.deleteMany(Filters.and(Filters.eq("recipient", userEmail), Filters.eq("read", Boolean.TRUE)));
    }

    public static ArrayList<Email> getAllUnreadEmails(String userEmail) {
        FindIterable<Document> docIter = emailCollection
                .find(Filters.and(Filters.eq("recipient", userEmail), Filters.eq("read", Boolean.TRUE)));
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

    public User checkLogin(String email, String password) {

        BasicDBObject query = new BasicDBObject();
        query.append("email", email).append("password", password);
        FindIterable<Document> docIter = usersCollection.find(query);
        MongoCursor<Document> iter = docIter.iterator();
        if (!iter.hasNext()) { // User with email does not exist
            return null;
        }
        Document userDoc = iter.next();
        User user;
        String type = userDoc.get("type").toString();
        if (type.equals("registered_renter")) {
            user = RegisteredRenter.getRegisteredRenter(userDoc);
        } else if (type.equals("landlord")) {
            user = Landlord.getLandlord(userDoc);
        } else if (type.equals("manager")) {
            user = Manager.getManager(userDoc);
        } else {
            user = null;
        }

        return user;

    }

    public Document getUserByEmail(String email) {
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        FindIterable<Document> docIter = usersCollection.find(query);
        MongoCursor<Document> iter = docIter.iterator();
        if (!iter.hasNext()) {
            return null;
        }

        return iter.next();
    }

    private boolean emailTaken(String email) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("email", email);
        // usersCollection.
        FindIterable<Document> findIter = usersCollection.find(new Document("email", email));
        // FindIterable<Document> findIter = usersCollection.find(searchQuery);
        if (findIter.first() != null) {
            return true;
        }
        return false;
    }

    // Return new user if sign up successful,
    // Return null if email already taken
    public User signUp(String email, String password, String type) {
        if (emailTaken(email)) {
            return null;
        }

        RegisteredRenter user1;
        Landlord user2;
        Manager user3;
        if (type.equals("registered_renter")) {
            user1 = new RegisteredRenter();
            addUserToDatabase(email, password, type);
            user1 = RegisteredRenter.getRegisteredRenter(getUserByEmail(email));
            return user1;
        } else if (type.equals("landlord")) {
            // user2 = new Landlord();
            addUserToDatabase(email, password, type);
            user2 = Landlord.getLandlord(getUserByEmail(email));
            return user2;
        } else if (type.equals("manager")) {
            user3 = new Manager();
            addUserToDatabase(email, password, type);
            user3 = Manager.getManager(getUserByEmail(email));
            return user3;
        }

        return null;
    }

    // Return new user if sign up successful,
    // Return null if email already taken
    public User signUp(String email, String password, String type, String firstName, String lastName) {
        if (emailTaken(email)) {
            return null;
        }

        RegisteredRenter user1;
        Landlord user2;
        Manager user3;
        if (type.equals("registered_renter")) {
            user1 = new RegisteredRenter();
            addUserToDatabase(email, password, type);
            user1 = RegisteredRenter.getRegisteredRenter(getUserByEmail(email));
            return user1;
        } else if (type.equals("landlord")) {
            // user2 = new Landlord();
            addUserToDatabase(email, password, type, firstName, lastName);
            user2 = Landlord.getLandlord(getUserByEmail(email));
            return user2;
        } else if (type.equals("manager")) {
            user3 = new Manager();
            addUserToDatabase(email, password, type);
            user3 = Manager.getManager(getUserByEmail(email));
            return user3;
        }

        return null;
    }

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

    public void savePreference(PreferenceForm preferenceForm, User user) {

    }
}
