
package ensf480.group14.eventListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {
    String stringEvent;

    public void actionPerformed(ActionEvent e) {
        stringEvent = e.getActionCommand();
        // return e.getActionCommand();

    }

    public LoginListener() {
        stringEvent = "Login";
    }

    public String getStringEvent() {
        return stringEvent;
    }

}