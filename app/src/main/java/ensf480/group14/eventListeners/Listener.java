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

/**
 *  The folder which the class lies in the project. 
 */
package ensf480.group14.eventListeners;
/**
 * The import statements used in order for the code to work. 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ensf480.group14.dbcontrol.DatabaseSubject;
import ensf480.group14.dbcontrol.LandlordDBControl;
import ensf480.group14.dbcontrol.ManagerDBController;
import ensf480.group14.dbcontrol.RegisteredRenterDBController;
import ensf480.group14.dbcontrol.Report;
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
/**
 * For the class which basically interacts with the views and the controllers and connect them together and 
 * completes the functionality also implements the ActionListerner. Responsible for handling all 
 * action events such as when the user clicks on a component.
 */
public class Listener implements ActionListener {
    /**
     * Objects and variables being created here but not initalized. 
     */
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
    ReportSettings reportSettings;
    /**
     * Default constructor for our listener class which initializes them. 
     * @params: Takes in all of the events which can occur and the forms as well. 
     * @returns: Nothing. 
     */
    public Listener(RenterSignUpForm signUpForm,
            ContactForm contactForm, PreferenceForm preferenceForm, Search searchForm,
            PropertyApplication propertyAppForm, Inbox inbox, HomePage homePage, PropertyPage propertyPage,
            JFrame frame, LandlordSignUpForm landlordSignUpForm, PayInfoForm paymentForm,
            EditPropertyView editProperty, ReportSettings reportSettings) {
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
        this.reportSettings = reportSettings;
        this.refresh = false;
    }
    
    /**
     * Once the application starts it will check for valid payments or not. 
     * @params: Takes in nothing 
     * @returns: Nothing.  
     */
    public void startUpRoutine(){
        renterController.checkPayments();
    }

    /**
     * Actions being performed by the user and based on the users choices taken the pages switch and display
     * the correct output on the action commanded being passed in the button. 
     * Bunch of else if statements due to the events which are being done in the application. 
     * @params: Takes in the action event e. 
     * @returns: Displaying the proper page. 
     */
    public void actionPerformed(ActionEvent e){
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
            Double currFee = ManagerDBController.getCurrentFee();
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
                landlordController.cancelProperty(property.getAddress());
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
                boolean res = landlordController.addPropertyToDatabase(prop);
                if(res){
                    JOptionPane.showMessageDialog(frame, "Property Application Submitted, please pay fee to list now.");
                    setPageToShow("PayInfoPage");
                }else{
                    JOptionPane.showMessageDialog(null, "Property with this address already exists");
                    setPageToShow("PropertyApplicationPage");
                    setRefresh(true);
                }
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
            landlordController.editProperty(editPropertyView,property);
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

        else if (e.getActionCommand().equals("Generate Report")){
            setPageToShow("ReportSettingsPage");
        }

        else if (e.getActionCommand().equals("Get Report")){

            if(reportSettings.getStartYear() < 1 || reportSettings.getStartDay() < 1||reportSettings.getStartMonth() < 1||reportSettings.getEndYear() < 1
            || reportSettings.getEndDay() < 1 
            ||reportSettings.getEndMonth()<1
            || reportSettings.getStartYear() > reportSettings.getStartYear()
            ){
            JOptionPane.showMessageDialog(null, "Error: Invalid Dates.");
            setRefresh(true);
            }
            else{
            String start = reportSettings.getStartYear() + "-" + reportSettings.getStartMonth() + "-" + reportSettings.getStartDay();
            String end = reportSettings.getEndYear()+"-"+reportSettings.getEndMonth()+"-"+reportSettings.getEndDay();
            
            try{
                Report report = new Report(start, end);
                JOptionPane.showMessageDialog(frame, "Report Generated");
            }
            catch(IOException error){
                JOptionPane.showMessageDialog(frame, "Report Could Not Be Generated");
            }
            setPageToShow("HomePage");
            }
        }
    }

    /**
     * Gets all of the users in an array. 
     * @params: Takes in nothing
     * @returns: An user arraylist which are in the system. 
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets the users which are being passed. 
     * @params: Takes in an arraylist of the User object which has users created. 
     * @returns: Nothing. 
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    /**
     * Signs up the renter based on the username, password and gives an option for confirming the password. 
     * And if the user is not equal it will not let the renter sign up the passwords need to match. 
     * @params: Takes in a username, password, and confirmPassword. 
     * @returns: Returns the boolean based on the success on it.  
     */
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

    /**
     * Signs up the landlord based on the username, password and gives an option for confirming the password.
     * And if the user is not equal it will not let the landlord sign up the passwords need to match. 
     * @params: Takes in a username, password, and confirmPassword, firstName and lastName of the user. 
     * @returns: Returns the boolean based on the success on it.  
     */
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

    /**
     * Goes and searches for properties and does error checking for correct values which are being passed in by the user. 
     * @params: Takes in nothing
     * @returns: Returns the boolean based on a sucessful search or not.
     */
    public boolean searchProperties() {
        if(searchForm.getMaxPrice() < 0 || searchForm.getMinPrice() > searchForm.getMaxPrice() || searchForm.getNumOfBathrooms() < 0 || searchForm.getNumOfBedrooms() < 0){
            return false;
        }else{
            ArrayList<Property> res = renterController.searchProperties(searchForm);
            setProperties(res);
            return true; 
        }
    }

    /**
     * Opens the property page when the user decides to click on a specific property in a view. 
     * @params: Takes in the address which is a unique id for the property. 
     * @returns: Returns nothing just displays the property page. 
     */
    public void openProperty(String address) {// property page
        for (Property pp : properties) {
            if (pp.getAddress().equals(address)) {
                property = pp;
            }
        }
        setPageToShow("PropertyPage");
    }

    /**
     * Opens the visibility dialog and can be only clicked by the manager and changed to a proper visibility. 
     * @params: Takes in the address which is a unique id for the property. 
     * @returns: Returns nothing just displays the visbility panel which can be changed based on the manager options. 
     */
    public void openVisibilityPanel(String address) {
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

    /**
     * Opens edit property page to edit the attributes of a property. 
     * @params: Takes in the address which is a unique id for the property. 
     * @returns: Returns nothing just displays edit property page for a user to edit their application based on changes which 
     * could be made. 
     */
    public void openEditProperty(String address) {
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

    /**
     * Opens the email page when the user presses in the inbox and takes you to the viewing of the email which is for
      * the user. 
     * @params: Takes in the email which is a unique id for the user
     * @returns: Returns nothing just displays the email page. 
     */
    public void openEmail(String emailID) {
        for (Email e : mail) {
            if (e.getId().toString().equals(emailID)) {
                email = e;
            }
        }
        setPageToShow("EmailPage");
    }

    /**
     * Gets the current fee from the Manager database controller. 
     * @params: Takes in nothing
     * @returns: Returns a double which is the fee. 
     */
    public double getCurrentFee(){
        return ManagerDBController.getCurrentFee(); 
    }

    /**
     * Gets the landlord properties from the landlord controller. 
     * @params: Takes in nothing
     * @returns: Returns a property which is associated with their id. 
     */
    public void getLandlordsProperties() {
        properties = landlordController.getPropertyWithLandlord(user.getId());
    }

    /**
     * Sets the page to show and passes in the page name into it. 
     * @params: Takes in the string of the page which needs to be shown to the user. 
     * @returns: Returns nothing. 
     */
    public void setPageToShow(String pageToShow) {
        this.pageToShow = pageToShow;
    }

    /**
     * Gets the current page to show in the page to show on the panel/ 
     * @params: Takes in nothing
     * @returns: Returns a string which will need to be shown. 
     */
    public String getPageToShow() {
        return pageToShow;
    }

    /**
     * Gets the user which is in the system
     * @params: Takes in nothing
     * @returns: Returns an User object. 
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user into the object based on it. 
     * @params: Takes in the User object of user which can be assigned.  
     * @returns: Returns nothing. 
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the properties in the system which is collected in an array list. 
     * @params: Takes in nothing
     * @returns: Returns an arraylist of the properties. 
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * Sets the properties into the array list. 
     * @params: Takes in the an array list of object property. 
     * @returns: Returns nothing. 
     */
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    /**
     * Gets the emails in the system
     * @params: Takes in nothing
     * @returns: Returns an arraylist of the emails. 
     */
    public ArrayList<Email> getMail() {
        return mail;
    }

    /**
     * Sets the mails into the array list. 
     * @params: Takes in the an array list of object email. 
     * @returns: Returns nothing. 
     */
    public void setMail(ArrayList<Email> mail) {
        this.mail = mail;
    }

    /**
     * Gets the property in the system
     * @params: Takes in nothing
     * @returns: Returns a object of a Property. 
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Sets the property into the object. 
     * @params: Takes in the object of property and sets it.
     * @returns: Returns nothing. 
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * Sets the email e into it. 
     * @params: Takes in the object of email and sets it.
     * @returns: Returns nothing. 
     */
    public void setEmail(Email e) {
        email = e;
    }

    /**
     * Gets the email in the system
     * @params: Takes in nothing
     * @returns: Returns a object of an email. 
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Sets the refresh the of the boolean status into it. 
     * @params: Takes in the boolean of the refresh flag. 
     * @returns: Returns nothing. 
     */
    public void setRefresh(boolean b) {
        this.refresh = b;
    }

    /**
     * For refreshing between the pages this flag helps us keep in check to update a screen. 
     * @params: Takes in nothing
     * @returns: Returns a boolean of the current status of the refresh. 
     */
    public boolean getRefresh() {
        return this.refresh;
    }
}