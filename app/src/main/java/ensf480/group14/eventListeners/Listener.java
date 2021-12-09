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
    RegisteredRenterDBController renterController;
    ManagerDBController managerController;
    LandlordDBControl landlordController;

    Property property;
    Email email;

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

    public Listener(RenterSignUpForm signUpForm,
            ContactForm contactForm, PreferenceForm preferenceForm, Search searchForm,
            PropertyApplication propertyAppForm, Inbox inbox, HomePage homePage, PropertyPage propertyPage,
            JFrame frame, LandlordSignUpForm landlordSignUpForm, PayInfoForm paymentForm) {
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
            user = null;
            setPageToShow("HomePage");
        }

        else if (e.getActionCommand().equals("Sign up as renter")) {
            Boolean res = signUpRenter(signUpForm.getUsername(), signUpForm.getPassword(),
                    signUpForm.getConfirmPassword());
            if (res == true) {
                setPageToShow("HomePage");
            } else {
                setPageToShow("SignUpPage");
                JOptionPane.showMessageDialog(null, "Username is taken");
            }
        }

        else if (e.getActionCommand().equals("Sign up as landlord instead")) {
            setPageToShow("LandlordSignUpPage");
        }

        else if (e.getActionCommand().equals("Sign up as landlord")) {
            Boolean res = signUpLandlord(landlordSignUpForm.getUsername(), landlordSignUpForm.getPassword(),
                    landlordSignUpForm.getConfirmPassword());
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
            searchProperties();
            setPageToShow("SearchResultsPage");
        }

        else if (e.getActionCommand().equals("Notifications Settings")) {
            setPageToShow("PreferencePage");
        }

        else if (e.getActionCommand().equals("Access Database")) {
            setPageToShow("DatabasePage");
        }

        else if (e.getActionCommand().equals("Manage Properties")) {
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

        else if (e.getActionCommand().equals("Access Database")) {
            setPageToShow("SearchResultPage");
        }

        else if (e.getActionCommand().equals("Send")) {
            RegisteredRenterDBController.sendEmail(contactForm, property);
            JOptionPane.showMessageDialog(frame, "Message Sent.");
            setPageToShow("SearchResultsPage");
            // Send Contact Form
        }

        else if (e.getActionCommand().equals("Save Preference")) {
            RegisteredRenterDBController.savePreference(preferenceForm, user);
            JOptionPane.showMessageDialog(frame, "Preferences Saved.");
            setPageToShow("HomePage");
            // Saves preference form
        }

        else if (e.getActionCommand().equals("Submit Application")) {
            LandlordDBControl.submitPropertyApplication(propertyAppForm, user);
            JOptionPane.showMessageDialog(frame, "Property Application Submitted, please pay fee to list now.");
            setPageToShow("PayInfoPage");
        }

        else if (e.getActionCommand().equals("Contact Owner")) {
            setPageToShow("ContactPage");
        }

        else if (e.getActionCommand().equals("Submit Payment")) {
            Boolean res = landlordController.payFee(paymentForm, propertyAppForm);
            if (res) {
                JOptionPane.showMessageDialog(frame, "Property Payment Success");
                setPageToShow("HomePage");
            } else {
                JOptionPane.showMessageDialog(frame, "Property Payment Failed");
                setPageToShow("PayInfoPage");
            }
        }
    }

    public boolean signUpRenter(String username, String password, String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return false;
        }
        user = renterController.signUp(username, password, "Renter");

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean signUpLandlord(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return false;
        }
        user = renterController.signUp(username, password, "Landlord");
        if (user != null) {
            landlordController = new LandlordDBControl();
            return true;
        } else {
            return false;
        }
    }

    public void searchProperties() {
        ArrayList<Property> res = renterController.searchProperties(searchForm);
        setProperties(res);
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
        Object[] possible = { "Visible", "Unvisible" };
        String visible = (property.isVisibleToRenters() ? "Visible" : "Unvisible");
        String s = (String) JOptionPane.showOptionDialog(frame, "Set Property Visibility", "Change Visibility",
                JOptionPane.PLAIN_MESSAGE, null, possible, visible);
        if (s != null) {
            managerController.changePropertyState(address, s);
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
        if (property.isVisibleToRenters()) {
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
}