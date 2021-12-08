
package ensf480.group14.eventListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ensf480.group14.dbcontrol.DatabaseController;
import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
import ensf480.group14.forms.LoginForm;
import ensf480.group14.users.User;

public class Listener implements ActionListener {
    String pageToShow;
    User user;
    ArrayList<Property> properties;
    ArrayList<Email> mail;
    DatabaseController controller;

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
        } else if (e.getActionCommand().equals("Sign up as renter")){

        } else if (e.getActionCommand().equals("Sign up as landlord")){

        } else if (e.getActionCommand().equals("Search")) {
            pageToShow = "SearchPage";
        } else if (e.getActionCommand().equals("Notifications Settings")) {
            pageToShow = "PreferencePage";
        } else if (e.getActionCommand().equals("Access Database")) {
            pageToShow = "DatabasePage";
        } else if (e.getActionCommand().equals("Manage Properties")) {
            pageToShow = "ManagePropertysPage";
        }

    }

    public Listener(DatabaseController control) {
        controller = control;
        pageToShow = "Login";
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

}