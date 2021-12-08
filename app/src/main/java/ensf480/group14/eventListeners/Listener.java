
package ensf480.group14.eventListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ensf480.group14.dbcontrol.DatabaseController;
import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.forms.ContactForm;
import ensf480.group14.forms.LoginForm;
import ensf480.group14.forms.PreferenceForm;
import ensf480.group14.forms.PropertyApplication;
import ensf480.group14.forms.RenterSignUpForm;
import ensf480.group14.forms.Search;
import ensf480.group14.users.User;
import ensf480.group14.views.Inbox;

public class Listener implements ActionListener {
    String pageToShow;
    User user;
    ArrayList<Property> properties;
    ArrayList<Email> mail;
    DatabaseController controller;
    Property property;

    RenterSignUpForm signUpForm;
    ContactForm contactForm;
    PreferenceForm preferenceForm;
    Search searchForm;
    PropertyApplication propertyAppForm;
    Inbox inbox;

    public Listener(DatabaseController controller, RenterSignUpForm signUpForm,
            ContactForm contactForm, PreferenceForm preferenceForm, Search searchForm,
            PropertyApplication propertyAppForm, Inbox inbox) {
        this.controller = controller;
        this.signUpForm = signUpForm;
        this.contactForm = contactForm;
        this.preferenceForm = preferenceForm;
        this.searchForm = searchForm;
        this.propertyAppForm = propertyAppForm;
        this.inbox = inbox;
    }


    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource()); // test this
        if (e.getActionCommand().equals("Or Sign Up")) {
            pageToShow = "SignUpPage";
        } else if (e.getActionCommand().equals("Login")) {
            LoginForm login = LoginForm.getOnlyInstance(this);
            // user = controller.checkLogin(login.getUsername(), login.getPassword());
            // if (user != null) {
            // pageToShow = "HomePage";
            // } else {
            // JOptionPane.showMessageDialog(null, "Username or Password is Incorrect",
            // Error);
            // pageToShow = "LoginPage";
            // }
        } else if (e.getActionCommand().equals("Sign up as renter")) {
            Boolean res = signUpRenter(signUpForm.getUsername(), signUpForm.getPassword(), signUpForm.getConfirmPassword());
            if(res == true){
                setPageToShow("HomePage");
            }else{
                setPageToShow("SignUpPage");
                JOptionPane.showMessageDialog(null, "Username is taken");
            }
        } else if (e.getActionCommand().equals("Sign up as landlord")) {
            Boolean res = signUpLandlord(signUpForm.getUsername(), signUpForm.getPassword(), signUpForm.getConfirmPassword());
            if(res == true){
                setPageToShow("HomePage");
            }else{
                setPageToShow("SignUpPage");
                JOptionPane.showMessageDialog(null, "Username is taken");
            }
        } else if (e.getActionCommand().equals("Search Properties")) {
            pageToShow = "SearchPage";
        } else if (e.getActionCommand().equals("Search")){
            searchProperties();
            setPageToShow("SearchResultsPage");
        } 
        else if (e.getActionCommand().equals("Notifications Settings")) {
            pageToShow = "PreferencePage";
        } else if (e.getActionCommand().equals("Access Database")) {
            pageToShow = "DatabasePage";
        } else if (e.getActionCommand().equals("Manage Properties")) {
            pageToShow = "ManagePropertysPage";
        }

    }


    public Boolean signUpRenter(String username, String password, String confirmPassword){

    }

    public Boolean signUpLandlord(String username, String password, String confirmPassword){

    }

    public void searchProperties(){

    }

    public void setPageToShow(String pageToShow) {
        this.pageToShow = pageToShow;
    }

    public String getPageToShow() {
        return pageToShow;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DatabaseController getController() {
        return controller;
    }

    public void setController(DatabaseController controller) {
        this.controller = controller;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public ArrayList<Email> getMail() {
        return mail;
    }

    public void setMail(ArrayList<Email> mail) {
        this.mail = mail;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

}