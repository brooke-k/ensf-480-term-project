/**
 * File: Email.java
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

/**
 *  The folder which the class lies in the project. 
 */
package ensf480.group14.external;
/**
 * The import statements used in order for the code to work. 
 */
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * This class basically creates an email object with the following attributes and is used when 
 * creating a new email object we can use the default constructors to instantiate them. Also
 * keeps a track of the recipient, subject, and the body. 
 */
public class Email {
   //Attributes of the email class. 
    private String recipient;
    private String subject;
    private String body;
    private String sender;
    private ObjectId emailID;

    /**
     * This is for the constructor which takes in the body and sender. 
     * @params: Body, Sender is taken in. 
     * @returns: Nothing. 
     */
    public Email(String bod, String send) {
        this.body = bod;
        this.sender = send;
    }

    /**
     * This is for the default constructor. 
     * @params: Nothing. 
     * @returns: Nothing. 
     */
    public Email() {

    }

    /**
     * This is for getting all of th emails in the database by using the database to communicate within it. 
     * @params: Takes in an emailcollection.
     * @returns: An email object. 
     */
    public static Email getEmail(Document emailDoc) {
        Email email = new Email();
        email.setBody(emailDoc.get("body").toString());
        email.setId((ObjectId) emailDoc.get("_id"));
        email.setRecipient(emailDoc.get("recipient").toString());
        email.setSender(emailDoc.get("sender").toString());
        email.setSubject(emailDoc.get("subject").toString());
        return email;
    }
    
    /**
     * This is for appending an email with the reciepient and is taken in by the email object. 
     * @params: Takes in an email object. 
     * @returns: A collection. 
     */
    public static Document toDocument(Email email) {
        Document emailDoc = new Document("_id", email.getId());
        emailDoc.append("recipient", email.getRecipient());
        emailDoc.append("sender", email.getSender());
        emailDoc.append("subject", email.getSubject());
        emailDoc.append("body", email.getBody());
        return emailDoc;
    }

    /**
     * This is for the constructor which takes in the body and sender, and subject
     * @params: Body, Sender, and Subject is taken in. 
     * @returns: Nothing. 
     */
    public Email(String bod, String send, String sub) {
        this.body = bod;
        this.sender = send;
        this.subject = sub;

    }

   /**
     * This is for the constructor which takes in the body and sender, subject, and recipient. 
     * @params: Body, Sender, Subject, and recipient is taken in. 
     * @returns: Nothing. 
     */
    public Email(String bod, String send, String sub, String recipient) {
        this.body = bod;
        this.sender = send;
        this.subject = sub;
        this.recipient = recipient;
    }

    /**
     * This is for getting the reciepents their email address. 
     * @params: Takes in nothing. 
     * @returns: Gives back a string with the reciepent email address. 
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * This is for setting the reciepents their email address. 
     * @params: Takes in the reciepent and sets it. 
     * @returns: Nothing. 
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * This is for getting the subject which is in the email object
     * @params: Takes in nothing. 
     * @returns: Gives back a string which is the subject of the email. 
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This is for setting the subjects their email address. 
     * @params: Takes in the subject and sets it. 
     * @returns: Nothing. 
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This is for getting the body which is in the email object
     * @params: Takes in nothing. 
     * @returns: Gives back a string which is the body of the email. 
     */
    public String getBody() {
        return body;
    }

    /**
     * This is for setting the body their email address. 
     * @params: Takes in the body and sets it. 
     * @returns: Nothing. 
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This is for getting the sender which is in the email object
     * @params: Takes in nothing. 
     * @returns: Gives back a string which is the sender of the email. 
     */
    public String getSender() {
        return sender;
    }

    /**
     * This is for setting the sender their email address. 
     * @params: Takes in the sender and sets it. 
     * @returns: Nothing. 
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * This is for getting the ID which is in the email object
     * @params: Takes in nothing. 
     * @returns: Gives back a string which is the ID of the email. 
     */
    public void setId(ObjectId iD) {
        this.emailID = iD;
    }
    
    /**
     * This is for setting the ID which is in the email object
     * @params: Takes in the ID of the email and sets it. 
     * @returns: Nothing. 
     */
    public ObjectId getId() {
        return this.emailID;
    }

}