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

package ensf480.group14.external;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Email {
    private String recipient;
    private String subject;
    private String body;
    private String sender;
    private ObjectId emailID;

    public Email(String bod, String send) {
        this.body = bod;
        this.sender = send;
    }

    public Email() {

    }

    public static Email getEmail(Document emailDoc) {
        Email email = new Email();
        email.setBody(emailDoc.get("body").toString());
        email.setId((ObjectId) emailDoc.get("_id"));
        email.setRecipient(emailDoc.get("dest_addr").toString());
        email.setSender(emailDoc.get("src_addr").toString());
        email.setSubject(emailDoc.get("subject").toString());
        return email;
    }

    public static Document toDocument(Email email) {
        Document emailDoc = new Document("_id", email.getId());
        emailDoc.append("dest_addr", email.getRecipient());
        emailDoc.append("src_addr", email.getSender());
        emailDoc.append("subject", email.getSubject());
        emailDoc.append("body", email.getBody());
        return emailDoc;
    }

    public Email(String bod, String send, String sub) {
        this.body = bod;
        this.sender = send;
        this.subject = sub;

    }

    public Email(String bod, String send, String sub, String recipient) {
        this.body = bod;
        this.sender = send;
        this.subject = sub;
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setId(ObjectId iD) {
        this.emailID = iD;
    }

    public ObjectId getId() {
        return this.emailID;
    }

}