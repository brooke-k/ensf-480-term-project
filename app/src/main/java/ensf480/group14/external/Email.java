package ensf480.group14.external;



public class Email  {
private String recipient;
private String subject;
private String body;
private String sender;


public Email(String bod,String send){
    this.body = bod;
    this.sender = send;



}

public Email(String bod,String send,String sub){
    this.body = bod;
    this.sender = send;
    this.subject = sub;

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


}