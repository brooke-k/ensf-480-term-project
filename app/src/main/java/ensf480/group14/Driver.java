package ensf480.group14;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ensf480.group14.forms.LoginForm;

import java.awt.event.ActionEvent;

public class Driver {
    static JFrame frame;

    // static LoginListener loginListener;

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Before If's");
            if (e.getSource() == "loginButton") {
                System.out.println("login");
            } else if (e.getSource() == "signUpButton") {
                System.out.println("SignUp");
            }
        }
    }

    public static void main(String[] args) {
        // loginListener = LoginListener();
        frame = new JFrame();
        LoginForm form = LoginForm.getOnlyInstance(Driver.class.new LoginListener());
        // ^^ Does not work. Need another package to deal with this.
        frame.add(form.display());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return;
    }
}
