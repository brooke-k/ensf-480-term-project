
package ensf480.group14.eventListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ensf480.group14.dbcontrol.DatabaseController;
import ensf480.group14.forms.LoginForm;
import ensf480.group14.users.User;

public class Listener implements ActionListener {
    String pageToShow;
    User user;
    DatabaseController controller;

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Or Sign In")) {
            pageToShow = "SignInPage";
        } else if (e.getActionCommand().equals("Login")) {
            LoginForm login = LoginForm.getOnlyInstance(this);
            user = controller.checkLogin(login.getUsername(), login.getPassword());
            if (user != null) {
                pageToShow = "HomePage";
            } else {
                JOptionPane.showMessageDialog(null, "Username or Password is Incorrect", Error);
                pageToShow = "LoginPage";
            }
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

    public String getPageToShow() {
        return pageToShow;
    }

}