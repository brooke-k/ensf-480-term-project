/**
 * File: Listener.java
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

package ensf480.group14.eventListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ensf480.group14.dbcontrol.DatabaseSubject;
import ensf480.group14.dbcontrol.LandlordDBControl;
import ensf480.group14.dbcontrol.ManagerDBController;
import ensf480.group14.dbcontrol.RegisteredRenterDBController;
import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.forms.ContactForm;
import ensf480.group14.forms.LandlordSignUpForm;
import ensf480.group14.forms.LoginForm;
import ensf480.group14.forms.PayInfoForm;
import ensf480.group14.forms.PreferenceForm;
import ensf480.group14.forms.PropertyApplication;
import ensf480.group14.forms.RenterSignUpForm;
import ensf480.group14.forms.*;
import ensf480.group14.users.Landlord;
import ensf480.group14.users.Manager;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;
import ensf480.group14.views.HomePage;
import ensf480.group14.views.Inbox;
import ensf480.group14.views.*;

public class Listener implements ActionListener {
    String pageToShow;
    User user;
    ArrayList<Property> properties;
    ArrayList<Email> mail;
    ArrayList<User> users;
    RegisteredRenterDBController renterController;
    ManagerDBController managerController;
    LandlordDBControl landlordController;
    
    Property property;
    Email email;
    boolean refresh;

    RenterSignUpForm signUpForm;
    LandlordSignUpForm landlordSignUpForm;
    ContactForm contactForm;
    PreferenceForm preferenceForm;
    Search searchForm;
    PropertyApplication propertyAppForm;
    Inbox inbox;
    HomePage homePage;
    PropertyPage propertyPage;
    JFrame frame;
    PayInfoForm paymentForm;
    EditPropertyView editPropertyView;

    public Listener(RenterSignUpForm signUpForm,
            ContactForm contactForm, PreferenceForm preferenceForm, Search searchForm,
            PropertyApplication propertyAppForm, Inbox inbox, HomePage homePage, PropertyPage propertyPage,
            JFrame frame, LandlordSignUpForm landlordSignUpForm, PayInfoForm paymentForm,
            EditPropertyView editProperty) {
        this.renterController = new RegisteredRenterDBController();
        this.signUpForm = signUpForm;
        this.contactForm = contactForm;
        this.preferenceForm = preferenceForm;
        this.searchForm = searchForm;
        this.propertyAppForm = propertyAppForm;
        this.inbox = inbox;
        this.homePage = homePage;
        this.propertyPage = propertyPage;
        this.frame = frame;
        this.landlordSignUpForm = landlordSignUpForm;
        this.paymentForm = paymentForm;
        this.pageToShow = "LoginPage";
        this.editPropertyView = editProperty;
        this.refresh = false;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource()); // test this
        if (e.getActionCommand().equals("Or Sign Up")) {
            setPageToShow("SignUpPage");
        }

        else if (e.getActionCommand().equals("Login")) {
            LoginForm login = LoginForm.getOnlyInstance(this);
            user = renterController.checkLogin(login.getUsername(), login.getPassword());
            if (user instanceof RegisteredRenter) {
                renterController = new RegisteredRenterDBController();
            } else if (user instanceof Landlord) {
                landlordController = new LandlordDBControl();
            } else {
                managerController = new ManagerDBController();
            }

            if (user != null) {
                setPageToShow("HomePage");
            } else {
                JOptionPane.showMessageDialog(null, "Username or Password is Incorrect");
                setPageToShow("LoginPage");
            }
        }

        else if (e.getActionCommand().equals("Continue without Logging in")) {
            user = new RegisteredRenter(69);
            setPageToShow("HomePage");
        }

        else if (e.getActionCommand().equals("Sign up as renter")) {
            if(!signUpForm.getConfirmPassword().equals(signUpForm.getPassword())){
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
                
            }else{
                Boolean res = signUpRenter(signUpForm.getUsername(), signUpForm.getPassword(),
                    signUpForm.getConfirmPassword());
                if (res == true) {
                    setPageToShow("HomePage");
                } else {
                    setPageToShow("SignUpPage");
                    JOptionPane.showMessageDialog(null, "Username is taken");
                }
            }
        }

        else if (e.getActionCommand().equals("Sign up as landlord instead")) {
            setPageToShow("LandlordSignUpPage");
        }

        else if (e.getActionCommand().equals("Sign Up as Landlord")) {
            Boolean res = signUpLandlord(landlordSignUpForm.getUsername(), landlordSignUpForm.getPassword(),
                    landlordSignUpForm.getConfirmPassword(), landlordSignUpForm.getFirstName(),
                    landlordSignUpForm.getLastName());
            if (res == true) {
                setPageToShow("HomePage");
            } else {
                setPageToShow("LandlordSignUpPage");
                JOptionPane.showMessageDialog(null, "Username is taken");
            }
        }

        else if (e.getActionCommand().equals("Search Properties")) {
            setPageToShow("SearchPage");
        }

        else if (e.getActionCommand().equals("Search")) {
            boolean res = searchProperties();
            if(res){
                setPageToShow("SearchResultsPage");
            }else{
                JOptionPane.showMessageDialog(null, "Error: Invalid search criteria\n\rPlease correct your search requirements and try again.");
                setPageToShow("SearchPage");
                setRefresh(true);
            }
        }

        else if (e.getActionCommand().equals("Notifications Settings")) {
            setPageToShow("PreferencePage");
        }

        else if (e.getActionCommand().equals("Inbox")) {
            setMail(LandlordDBControl.getAllEmails(user.getEmail()));
            setPageToShow("InboxPage");
        }

        else if (e.getActionCommand().equals("Access Database")) {
            setPageToShow("DatabasePage");
        }

        else if (e.getActionCommand().equals("Manage Properties")) {
            user.setOwnedAddresses(LandlordDBControl.getLandlordsAddresses(user.getId()));
            getLandlordsProperties();
            setPageToShow("ManagePropertiesPage");
        }

        else if (e.getActionCommand().equals("Adjust Fees")) {
            Double currFee = managerController.getCurrentFee();
            Double changedFee = Double.parseDouble((String) JOptionPane.showInputDialog(frame, "Change fees to",
                    "Change Fees", JOptionPane.PLAIN_MESSAGE, null, null, currFee));
            if (changedFee != null) {
                managerController.setNewFee(changedFee);
            }
        }

        else if (e.getActionCommand().equals("New Property Application")) {
            setPageToShow("PropertyApplicationPage");
        }

        else if (e.getActionCommand().equals("Access Properties")) {
            setProperties(managerController.getAllProperties());
            setPageToShow("SearchResultsPage");
        }

        else if (e.getActionCommand().equals("Access Users")) {
            setUsers(managerController.getAllUsers());
            setPageToShow("UserAccessPage");
        }

        else if (e.getActionCommand().equals("Remove Property")) {

            int res = JOptionPane.showConfirmDialog(null,"Remove Property at " + property.getAddress() + "?", 
            "Confirm Delete Property",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                landlordController.removePropertyFromDatabase(property.getAddress());
                getLandlordsProperties();
                setPageToShow("ManagePropertiesPage");
                setRefresh(true); // refresh in driver
            }

        }

        else if (e.getActionCommand().equals("Send")) {

            Email em = new Email(contactForm.getMessage(),contactForm.getSenderEmailAddress(), property.getAddress(), property.getLandlordEmail());
            RegisteredRenterDBController.sendEmail(em);
            JOptionPane.showMessageDialog(frame, "Message Sent.");
            setPageToShow("SearchResultsPage");
        }

        else if (e.getActionCommand().equals("Save Preference")) {
            renterController.savePreference(preferenceForm, user);
            JOptionPane.showMessageDialog(frame, "Preferences Saved.");
            setPageToShow("HomePage");
            // Saves preference form
        }

        else if (e.getActionCommand().equals("Submit Application")) {
            if(propertyAppForm.getPrice() <= 0 || propertyAppForm.getNumBath() <= 0 || propertyAppForm.getNumBed() <= 0){
                JOptionPane.showMessageDialog(null, "Error: Invalid application criteria\n\rPlease correct your application and try again.");
                setRefresh(true);
            }else{
                Property prop = new Property(propertyAppForm.getAddress(), propertyAppForm.getCityQuad(),
                        propertyAppForm.getNumBed(), propertyAppForm.getNumBath(), propertyAppForm.isFurnished(),
                        propertyAppForm.getPrice(), propertyAppForm.getType());
                prop.setLandlordEmail(user.getEmail());
                prop.setLandlordName(user.getFirstName() + " " + user.getLastName());
                prop.setLandlordID(user.getiD());

                property = prop;
                landlordController.addPropertyToDatabase(prop);
                JOptionPane.showMessageDialog(frame, "Property Application Submitted, please pay fee to list now.");
                setPageToShow("PayInfoPage");
            }
        }

        else if (e.getActionCommand().equals("Contact Owner")) {
            setPageToShow("ContactPage");
        }

        else if (e.getActionCommand().equals("Submit Payment")) {
            Boolean res = landlordController.payFee(paymentForm, property);
            if (res) {
                JOptionPane.showMessageDialog(frame, "Property Payment Success");
                setPageToShow("HomePage");
            } else {
                JOptionPane.showMessageDialog(frame, "Property Payment Failed");
                setPageToShow("PayInfoPage");
            }
        }

        else if (e.getActionCommand().equals("Save Changes")) {
            landlordController.editProperty(editPropertyView);
            getLandlordsProperties();
            setPageToShow("ManagePropertiesPage");
        }

        else if (e.getActionCommand().equals("Back to Inbox")){
            setPageToShow("InboxPage");
        }

        else if (e.getActionCommand().equals("Delete Email")){
            LandlordDBControl.deleteEmail(email.getId());
            setMail(LandlordDBControl.getAllEmails(user.getEmail()));
            setPageToShow("InboxPage");
        }

        else if (e.getActionCommand().equals("Adjust Payment Period")){
            Double currPeriod = managerController.getCurrentPeriod();
            Double changedPeriod = Double.parseDouble((String) JOptionPane.showInputDialog(frame, "Change period to",
                    "Change Period", JOptionPane.PLAIN_MESSAGE, null, null, currPeriod));
            if (changedPeriod != null) {
                managerController.setCurrentPeriod(changedPeriod);
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean signUpRenter(String username, String password, String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return false;
        }
        user = renterController.signUp(username, password, "registered_renter");

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean signUpLandlord(String username, String password, String confirmPassword, String firstName,
            String lastName) {
        if (!password.equals(confirmPassword)) {
            return false;
        }
        user = renterController.signUp(username, password, "landlord", firstName, lastName);
        if (user != null) {
            landlordController = new LandlordDBControl();
            return true;
        } else {
            return false;
        }
    }

    public boolean searchProperties() {
        if(searchForm.getMaxPrice() < 0 || searchForm.getMinPrice() > searchForm.getMaxPrice() || searchForm.getNumOfBathrooms() < 0 || searchForm.getNumOfBedrooms() < 0){
            return false;
        }else{
            ArrayList<Property> res = renterController.searchProperties(searchForm);
            setProperties(res);
            return true; 
        }
    }

    public void openProperty(String address) {// property page
        for (Property pp : properties) {
            if (pp.getAddress().equals(address)) {
                property = pp;
            }
        }
        setPageToShow("PropertyPage");
    }

    public void openVisibilityPanel(String address) {
        // takes address clicked by manager
        // opens dialog to change property visiblity
        for (Property pp : properties) {
            if (pp.getAddress().equals(address)) {
                property = pp;
            }
        }
        Object[] possible = { "Visible", "Unlisted" };
        String visible = (property.isVisibleToRenters() ? "Visible" : "Unlisted");
        String s = (String) JOptionPane.showInputDialog(frame, "Change Visibility to",
                "Change Visiblity", JOptionPane.PLAIN_MESSAGE, null, possible, visible);
        if (s != null) {
            managerController.changePropertyState(address, s.equals("Visible") ? true : false);
            setProperties(managerController.getAllProperties());
            setRefresh(true);
        }
    }

    public void openEditProperty(String address) {
        // takes address owned by the landlord
        // check if property is paid for
        // if it is open edit view
        // if it isn't, do something to make them pay
        for (Property pp : properties) {
            if (pp.getAddress().equals(address)) {
                property = pp;
            }
        }
        if (property.isVisibleToRenters() || property.isRented()) {
            setPageToShow("EditPropertyPage");
        } else {
            setPageToShow("PayInfoPage");
        }
    }

    public void openEmail(String emailID) {
        // Is taking the unique emailID
        // Then matches the emailID to extract information from it
        for (Email e : mail) {
            if (e.getId().toString().equals(emailID)) {
                email = e;
            }
        }
        setPageToShow("EmailPage");
    }

    public void getLandlordsProperties() {
        properties = landlordController.getPropertyWithLandlord(user.getId());
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

    public void setEmail(Email e) {
        email = e;
    }

    public Email getEmail() {
        return email;
    }

    public void setRefresh(boolean b) {
        this.refresh = b;
    }

    public boolean getRefresh() {
        return this.refresh;
    }
}