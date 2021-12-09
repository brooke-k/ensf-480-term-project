/**
 * File: Driver.java
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

package ensf480.group14;

import java.awt.event.ActionListener;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ensf480.group14.dbcontrol.DatabaseSubject;
import ensf480.group14.eventListeners.Listener;
import ensf480.group14.forms.*;
import ensf480.group14.users.User;
import ensf480.group14.views.EditPropertyView;
import ensf480.group14.views.EmailView;
import ensf480.group14.views.HomePage;
import ensf480.group14.views.Inbox;
import ensf480.group14.views.PropertyPage;
import ensf480.group14.views.SearchResult;

import java.awt.event.ActionEvent;

public class Driver {
    static JFrame mainFrame;
    static JPanel mainPanel;

    // static LoginListener loginListener;

    public static void main(String[] args) {
        CardLayout cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainFrame = new JFrame();

        RenterSignUpForm signUp = new RenterSignUpForm();
        ContactForm contact = new ContactForm();
        PreferenceForm preferenceForm = new PreferenceForm();
        Search searchForm = new Search();
        PropertyApplication propertyApp = new PropertyApplication();
        SearchResult searchResults = new SearchResult();
        Inbox inbox = new Inbox();
        HomePage homePage = new HomePage();
        PropertyPage propertyPage = new PropertyPage();
        LandlordSignUpForm landlordSignUpForm = new LandlordSignUpForm();
        PayInfoForm paymentForm = new PayInfoForm();
        EditPropertyView editProperty = new EditPropertyView();
        EmailView emailPage = new EmailView();

        Listener listener = new Listener(signUp, contact, preferenceForm, searchForm, propertyApp, inbox, homePage,
                propertyPage, mainFrame, landlordSignUpForm, paymentForm);

        LoginForm loginForm = LoginForm.getOnlyInstance(listener);

        mainPanel.add(loginForm.display(listener), "loginForm");
        mainPanel.add(signUp.display(listener), "renterSignUpForm");
        mainPanel.add(contact.display(listener), "contactForm");
        mainPanel.add(preferenceForm.display(listener), "preferencesForm");
        mainPanel.add(searchForm.display(listener), "searchForm");
        mainPanel.add(propertyApp.display(listener), "propertyApplicationPage");
        mainPanel.add(landlordSignUpForm.display(listener));
        mainPanel.add(paymentForm.display(listener));
        mainPanel.add(editProperty.display(listener.getProperty(), listener));
        mainPanel.add(emailPage.display(listener.getEmail(), listen))
        JPanel homePagePanel = homePage.display(listener.getUser(), listener);
        mainPanel.add(homePagePanel, "homePage");
        JPanel searchResultsPanel = searchResults.display(listener.getUser(), listener.getProperties());
        mainPanel.add(searchResultsPanel, "searchResultsPage");
        JPanel inboxPagePanel = inbox.display(listener.getMail(), listener);
        mainPanel.add(inboxPagePanel, "inboxPage");
        JPanel propertyPagePanel = propertyPage.display(listener.getProperty(), listener);
        mainPanel.add(propertyPagePanel, "propertyPage");

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        while (true) {
            String page = listener.getPageToShow();
            if (page.equals("SignUpPage")) {
                cardLayout.show(mainPanel, "renterSignUpForm");
            }

            else if (page.equals("LoginPage")) {
                cardLayout.show(mainPanel, "loginForm");
            }

            else if (page.equals("HomePage")) {
                mainPanel.remove(homePagePanel);
                homePagePanel = homePage.display(listener.getUser(), listener);
                mainPanel.add(homePagePanel, "homePage");
                cardLayout.show(mainPanel, "homePage");
            }

            else if (page.equals("SearchPage")) {
                cardLayout.show(mainPanel, "searchForm");
            }

            else if (page.equals("SearchResultsPage")) {
                mainPanel.remove(searchResultsPanel);
                searchResultsPanel = searchResults.display(listener.getUser(), listener.getProperties());
                mainPanel.add(searchResultsPanel, "searchResultsPage");
                cardLayout.show(mainPanel, "searchResultsPage");
            }

            else if (page.equals("InboxPage")) {
                mainPanel.remove(inboxPagePanel);
                inboxPagePanel = inbox.display(listener.getMail(), listener);
                mainPanel.add(inboxPagePanel, "inboxPage");
                cardLayout.show(mainPanel, "inboxPage");
            }

            else if (page.equals("PropertyPage")) {
                mainPanel.remove(propertyPagePanel);
                propertyPagePanel = propertyPage.display(listener.getProperty(), listener);
                mainPanel.add(propertyPagePanel, "propertyPage");
                cardLayout.show(mainPanel, "propertyPage");
            }

            else if (page.equals("PreferencePage")) {

            }

            else if (page.equals("DatabasePage")) {

            }

            else if (page.equals("ManagePropertiesPage")) {

            }

            else if (page.equals("PropertyApplicationPage")) {

            }

            else if (page.equals("PayInfoPage")) {

            }

            else if (page.equals("ContactPage")) {

            }

            else if (page.equals("EditPropertyPage")) {

            }

            else if (page.equals("EmailPage")) {

            }

            else {
                cardLayout.show(mainPanel, "searchForm");
            }
        }
    }
}
