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
import java.util.concurrent.TimeUnit;
import java.awt.CardLayout;

import javax.swing.JFrame; 
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import ensf480.group14.dbcontrol.DatabaseSubject;
import ensf480.group14.eventListeners.Listener;
import ensf480.group14.forms.*;
import ensf480.group14.forms.ReportSettings;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;

import ensf480.group14.views.*;

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
        AllUserView allUserPage = new AllUserView();
        ReportSettings reportSettingsPage = new ReportSettings();

        Listener listener = new Listener(signUp, contact, preferenceForm, searchForm, propertyApp, inbox, homePage,
                propertyPage, mainFrame, landlordSignUpForm, paymentForm, editProperty, reportSettingsPage);

        listener.startUpRoutine();

        LoginForm loginForm = LoginForm.getOnlyInstance(listener);

        JPanel allUserPagePanel = allUserPage.display(listener.getUsers(), listener);
        mainPanel.add(allUserPagePanel, "UserAccessPage");
        mainPanel.add(loginForm.display(listener), "loginForm");
        mainPanel.add(signUp.display(listener), "renterSignUpForm");
        mainPanel.add(contact.display(listener), "contactForm");
        mainPanel.add(preferenceForm.display(listener), "preferencesForm");
        JPanel searchFormPanel = searchForm.display(listener);
        mainPanel.add(searchFormPanel, "searchForm");
        JPanel propertyAppPanel = propertyApp.display(listener);
        mainPanel.add(propertyAppPanel, "propertyApplicationPage");
        mainPanel.add(landlordSignUpForm.display(listener), "landLordSignUpForm");
        mainPanel.add(paymentForm.display(listener), "paymentForm");
        JPanel editPropertyPanel = editProperty.display(listener.getProperty(), listener);
        mainPanel.add(editPropertyPanel);

        JPanel reportSettingsPanel = reportSettingsPage.display(listener);
        mainPanel.add(reportSettingsPanel, "reportSettingPage");
        JPanel emailJPanel = emailPage.display(listener.getEmail(), listener);
        mainPanel.add(emailJPanel, "emailPage");
        JPanel homePagePanel = homePage.display(new RegisteredRenter(1), listener);
        mainPanel.add(homePagePanel, "homePage");
        JPanel searchResultsPanel = searchResults.display(listener.getUser(), listener.getProperties(), listener);
        mainPanel.add(searchResultsPanel, "searchResultsPage");
        JPanel inboxPagePanel = inbox.display(listener.getMail(), listener);
        mainPanel.add(inboxPagePanel, "inboxPage");
        JPanel propertyPagePanel = propertyPage.display(listener.getProperty(), listener);
        mainPanel.add(propertyPagePanel, "propertyPage");

        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("HomePage");
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                listener.setPageToShow("HomePage");
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        String page = "LoginPage";
        String prevPage = "null";
        while (true) {
            page = listener.getPageToShow();

            if (prevPage.equals(page) && !listener.getRefresh()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;

            }

            prevPage = page;
            if (page.equals("LoginPage")) {
                cardLayout.show(mainPanel, "loginForm");
            }

            else if (page.equals("SignUpPage")) {
                cardLayout.show(mainPanel, "renterSignUpForm");
            }

            else if (page.equals("LandlordSignUpPage")) {
                cardLayout.show(mainPanel, "landLordSignUpForm");
            }

            else if (page.equals("HomePage")) {
                mb.add(menu);
                mainFrame.setJMenuBar(mb);
                mainPanel.remove(homePagePanel);
                homePagePanel = homePage.display(listener.getUser(), listener);
                mainPanel.add(homePagePanel, "homePage");
                cardLayout.show(mainPanel, "homePage");
            }

            else if (page.equals("SearchPage")) {
                mainPanel.remove(searchFormPanel);
                searchFormPanel = searchForm.display(listener);
                mainPanel.add(searchFormPanel, "searchForm");
                cardLayout.show(mainPanel, "searchForm");
            }

            else if (page.equals("SearchResultsPage")) {
                mainPanel.remove(searchResultsPanel);
                searchResultsPanel = searchResults.display(listener.getUser(), listener.getProperties(), listener);
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
                cardLayout.show(mainPanel, "preferencesForm");
            }

            else if (page.equals("DatabasePage")) {
                mainPanel.remove(searchResultsPanel);
                searchResultsPanel = searchResults.display(listener.getUser(), listener.getProperties(), listener);
                mainPanel.add(searchResultsPanel, "searchResultsPage");
                cardLayout.show(mainPanel, "searchResultsPage");
            }

            else if (page.equals("ManagePropertiesPage")) {
                mainPanel.remove(searchResultsPanel);
                searchResultsPanel = searchResults.display(listener.getUser(), listener.getProperties(), listener);
                mainPanel.add(searchResultsPanel, "searchResultsPage");
                cardLayout.show(mainPanel, "searchResultsPage");
            }

            else if (page.equals("PropertyApplicationPage")) {
                mainPanel.remove(propertyAppPanel);
                propertyAppPanel = propertyApp.display(listener);
                mainPanel.add(propertyAppPanel, "propertyApplicationPage");
                cardLayout.show(mainPanel, "propertyApplicationPage");
            }

            else if (page.equals("PayInfoPage")) {
                cardLayout.show(mainPanel, "paymentForm");
            }

            else if (page.equals("ContactPage")) {
                cardLayout.show(mainPanel, "contactForm");
            }

            else if (page.equals("EditPropertyPage")) {
                mainPanel.remove(editPropertyPanel);
                editPropertyPanel = editProperty.display(listener.getProperty(), listener);
                mainPanel.add(editPropertyPanel, "editPropertyPage");
                cardLayout.show(mainPanel, "editPropertyPage");
            }

            else if (page.equals("EmailPage")) {
                mainPanel.remove(emailJPanel);
                emailJPanel = emailPage.display(listener.getEmail(), listener);
                mainPanel.add(emailJPanel, "emailPage");
                cardLayout.show(mainPanel, "emailPage");
            }

            else if (page.equals("UserAccessPage")){
                mainPanel.remove(allUserPagePanel);
                allUserPagePanel = allUserPage.display(listener.getUsers(), listener);
                mainPanel.add(allUserPagePanel, "UserAccessPage");
                cardLayout.show(mainPanel,"UserAccessPage");
            }

            else if (page.equals("ReportSettingsPage")){
                mainPanel.remove(reportSettingsPanel);
                reportSettingsPanel = reportSettingsPage.display(listener);
                mainPanel.add(reportSettingsPanel, "reportSettingPage");
                cardLayout.show(mainPanel, "reportSettingPage");
            }


            listener.setRefresh(false);
        }
    }
}
