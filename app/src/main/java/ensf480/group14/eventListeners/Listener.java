
package ensf480.group14.eventListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {
    String pageToShow;

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Or Sign In")) {
            pageToShow = "SignInPage";
        }
        // return e.getActionCommand();

    }

    public Listener() {
        pageToShow = "Login";
    }

    public String getPageToShow() {
        return pageToShow;
    }

}