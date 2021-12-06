package ensf480.group14;

import java.awt.event.ActionListener;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ensf480.group14.eventListeners.Listener;
import ensf480.group14.forms.*;

import java.awt.event.ActionEvent;

enum FormState {
    LOGIN,
    RENTER_SIGNUP,
    CONTACT,
    PREFERENCE,
    LANDLORD_SIGNUP,
    SEARCH,
    PROPERTY_APPLICATION,
    ERROR;
}

public class Driver {
    static JFrame frame1;
    static JPanel frame;

    // static LoginListener loginListener;

    public static void main(String[] args) {
        CardLayout cardLayout = new CardLayout();
        frame = new JPanel(cardLayout);
        frame1 = new JFrame();

        Listener listener = new Listener();

        LoginForm loginForm = LoginForm.getOnlyInstance(listener);
        RenterSignUpForm signUp = new RenterSignUpForm();
        ContactForm contact = new ContactForm();
        PreferenceForm preferenceForm = new PreferenceForm();
        LandlordSignUpForm landlordSign = new LandlordSignUpForm();
        Search searchForm = new Search();
        PropertyApplication propertyApp = new PropertyApplication();

        frame.add(loginForm.display(), "loginForm");
        frame.add(signUp.display(), "renterSignUpForm");
        frame.add(contact.display(), "contactForm");
        frame.add(preferenceForm.display(), "preferencesForm");
        frame.add(searchForm.display(), "searchForm");
        frame.add(landlordSign.display(), "landlordSignUpForm");

        frame1.add(frame);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        while (true) {
            String page = listener.getPageToShow();
            if (page.equals("SignInPage")) {
                cardLayout.show(frame, "renterSignUpForm");
                System.out.println(loginForm.getUsername() + ", " + loginForm.getPassword());
            } else if (page.equals("LoginPage")) {
                cardLayout.show(frame, "loginForm");
            } else {
                cardLayout.show(frame, "loginForm");
            }
        }

        /*
         * FormState currState = FormState.LOGIN;
         * LoginListener loginListener = new LoginListener();
         * frame = new JFrame();
         * LoginForm form = LoginForm.getOnlyInstance(loginListener);
         * // ^^ Does not work. Need another package to deal with this.
         * frame.add(form.display());
         * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         * frame.pack();
         * frame.setLocationRelativeTo(null);
         * frame.setVisible(true);
         * // System.out.println("\u001B[1;1H\u001B[2J");
         * int s;
         * while (true) {
         * FormState curr = FormState.LOGIN;
         * s = loginListener.getStringEvent();
         * // System.out.println(s);
         * // if (s != null && s.equals("Login")) {
         * // System.out.println("LOGIN");
         * // curr = FormState.LOGIN;
         *
         * // } else if (s != null && s.equals("Or Sign Up")) {
         * // System.out.println("SIGNUP");
         * // curr = FormState.SIGNUP;
         * // }
         * // System.out.println("\u001B[1;1H\u001B[2K" + curr + ", " + currState);
         * // System.out.println(currState);
         * // System.out.println(curr);
         * // System.out.print(" ");
         * // if (currState != curr) {
         * // // System.out.println("\u001B[1;2H\u001B[2K" + "Test");
         * // currState = curr;
         * // System.out.println("te");
         *
         * switch (s) {
         * case 1:
         * System.out.println("LOGIN");
         * break;
         * case 2:
         * System.out.println("SIGNUP");
         * break;
         * default:
         * System.out.println("No Match");
         * }
         *
         * }
         *
         * }
         * // return;
         */
    }
}


    
    

    