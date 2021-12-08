
package ensf480.group14.eventListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ensf480.group14.dbcontrol.DatabaseController;
import ensf480.group14.dbcontrol.DatabaseSubject;
import ensf480.group14.dbcontrol.LandlordDBControl;
import ensf480.group14.dbcontrol.ManagerDBController;
import ensf480.group14.dbcontrol.RegisteredRenterDBController;
import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.forms.ContactForm;
import ensf480.group14.forms.LoginForm;
import ensf480.group14.forms.PreferenceForm;
import ensf480.group14.forms.PropertyApplication;
import ensf480.group14.forms.RenterSignUpForm;
import ensf480.group14.forms.Search;
import ensf480.group14.users.Landlord;
import ensf480.group14.users.Manager;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;
import ensf480.group14.views.HomePage;
import ensf480.group14.views.Inbox;
import ensf480.group14.views.PropertyPage;

public class Listener implements ActionListener {
    String pageToShow;
    User user;
    ArrayList<Property> properties;
    ArrayList<Email> mail;
    DatabaseSubject controller;

    Property property;

    RenterSignUpForm signUpForm;
    ContactForm contactForm;
    PreferenceForm preferenceForm;
    Search searchForm;
    PropertyApplication propertyAppForm;
    Inbox inbox;
    HomePage homePage; 
    PropertyPage propertyPage;

    public Listener(RenterSignUpForm signUpForm,
        ContactForm contactForm, PreferenceForm preferenceForm, Search searchForm,
        PropertyApplication propertyAppForm, Inbox inbox, HomePage homePage, PropertyPage propertyPage) {
        this.signUpForm = signUpForm;
        this.contactForm = contactForm;
        this.preferenceForm = preferenceForm;
        this.searchForm = searchForm;
        this.propertyAppForm = propertyAppForm;
        this.inbox = inbox;
        this.homePage = homePage;
        this.propertyPage = propertyPage;
    }


    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource()); // test this
        if (e.getActionCommand().equals("Or Sign Up")) {
            pageToShow = "SignUpPage";
        } else if (e.getActionCommand().equals("Login")) {
            LoginForm login = LoginForm.getOnlyInstance(this);
            user = controller.checkLogin(login.getUsername(), login.getPassword());
            if (user instanceof RegisteredRenter){
                controller = new RegisteredRenterDBController();
            }else if (user instanceof Landlord){
                controller = new LandlordDBControl();
            }else {
                controller = new ManagerDBController();
            }

            if (user != null) {
                pageToShow = "HomePage";
            } else {
                JOptionPane.showMessageDialog(null, "Username or Password is Incorrect");
                pageToShow = "LoginPage";
            }
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
        } else if (e.getActionCommand().equals("Adjust Fees")){

        } else if (e.getActionCommand().equals("New Property Application")){

        } else if (e.getActionCommand().equals("Access Database")){

        }

    }


    public Boolean signUpRenter(String username, String password, String confirmPassword){

    }

    public Boolean signUpLandlord(String username, String password, String confirmPassword){

    }

    public void searchProperties(){

    }

    public void openProperty(String address){

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