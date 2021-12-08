package ensf480.group14;

import java.awt.event.ActionListener;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ensf480.group14.dbcontrol.DatabaseController;
import ensf480.group14.eventListeners.Listener;
import ensf480.group14.forms.*;
import ensf480.group14.users.User;
import ensf480.group14.views.HomePage;
import ensf480.group14.views.Inbox;
import ensf480.group14.views.SearchResult;

import java.awt.event.ActionEvent;

public class Driver {
    static JFrame frame1;
    static JPanel frame;

    // static LoginListener loginListener;

    public static void main(String[] args) {
        CardLayout cardLayout = new CardLayout();
        frame = new JPanel(cardLayout);
        frame1 = new JFrame();

        DatabaseController controller = new DatabaseController();
        Listener listener = new Listener(controller);

        LoginForm loginForm = LoginForm.getOnlyInstance(listener);
        RenterSignUpForm signUp = new RenterSignUpForm();
        ContactForm contact = new ContactForm();
        PreferenceForm preferenceForm = new PreferenceForm();
        LandlordSignUpForm landlordSign = new LandlordSignUpForm();
        Search searchForm = new Search();
        PropertyApplication propertyApp = new PropertyApplication();
        HomePage homePage = new HomePage(listener);
        SearchResult searchResults = new SearchResult();
        Inbox inbox = new Inbox();

        frame.add(loginForm.display(listener), "loginForm");
        frame.add(signUp.display(listener), "renterSignUpForm");
        frame.add(contact.display(listener), "contactForm");
        frame.add(preferenceForm.display(listener), "preferencesForm");
        frame.add(searchForm.display(listener), "searchForm");
        frame.add(landlordSign.display(listener), "landlordSignUpForm");
        JPanel homePagePanel = homePage.display(listener.getUser());
        frame.add(homePagePanel, "homePage");
        JPanel searchResultsPanel = searchResults.display(listener.getUser(), listener.getProperties());
        frame.add(searchResultsPanel, "searchResultsPage");
        JPanel inboxPagePanel = inbox.display(listener.getUser(), listener.getMail());
        frame.add(inboxPagePanel, "inboxPage");

        frame1.add(frame);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        while (true) {
            String page = listener.getPageToShow();
            if (page.equals("SignUpPage")) {
                cardLayout.show(frame, "renterSignUpForm");
            } else if (page.equals("LoginPage")) {
                cardLayout.show(frame, "loginForm");
            } else if (page.equals("HomePage")) {
                frame.remove(homePagePanel);
                homePagePanel = homePage.display(listener.getUser());
                frame.add(homePagePanel, "homePage");
                cardLayout.show(frame, "homePage");
            } else if (page.equals("SearchPage")) {
                cardLayout.show(frame, "searchForm");
            } else if (page.equals("SearchResultsPage")) {
                frame.remove(searchResultsPanel);
                searchResultsPanel = searchResults.display(listener.getUser(), listener.getProperties());
                frame.add(searchResultsPanel, "searchResultsPage");
                cardLayout.show(frame, "searchResultsPage");
            } else if (page.equals("InboxPage")) {
                frame.remove(inboxPagePanel);
                inboxPagePanel = inbox.display(listener.getUser(), listener.getMail());
                frame.add(inboxPagePanel, "inboxPage");
                cardLayout.show(frame, "inboxPage");
            } else {
                cardLayout.show(frame, "searchForm");
            }
        }
    }
}
