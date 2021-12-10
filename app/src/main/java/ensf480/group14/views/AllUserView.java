/**
 * File: AllUserView.java
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

package ensf480.group14.views;

import ensf480.group14.eventListeners.Listener;
import ensf480.group14.external.Property;
import ensf480.group14.users.Landlord;
import ensf480.group14.users.RegisteredRenter;
import ensf480.group14.users.User;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.bson.types.ObjectId;

/**
 * Displays in a nice format for the showing the properties with it's attributes
 */
public class AllUserView {

    private Listener listener;

    /**
     * Default constructor
     */
    public AllUserView() {

    }

    
    /**
     * Takes in the User and it's properties which are assigned to it
     * 
     * @param: User object, and users for the Properties
     * @returns: All of them displayed in a JTable
     */
    public JPanel display(ArrayList<User> users, ActionListener listen) {
        listen = listener;
        JPanel master = new JPanel(new BorderLayout());
        DefaultTableModel model = null;
        User user;

        if (!user.getType().equals("manager")) {
            String[] columns = { "Email", "Type", "First Name", "Last Name"};
            if (!users.isEmpty()) {
                String[][] userList = new String[users.size()][7];
                int i = 0;
                for (User u : users) {
                    userList[i][0] = u.getEmail();
                    userList[i][1] = u.getType();
                    if(u.getType().equals("landlord")) {
                        userList[i][2] = u.getFirstName();
                        userList[i][3] = u.getLastName();
                    }
                    else {
                        userList[i][2] = "N/A";
                        userList[i][3] = "N/A";
                    }
                    i++;
                }
                  model = new DefaultTableModel(userList, columns);
            }

            JTable jTable = new JTable(model) {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }

            };
            jTable.getTableHeader().setReorderingAllowed(false);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
            jTable.setRowSorter(rowSorter);
            jTable.setBackground(Color.GRAY);
            jTable.setForeground(Color.PINK);
            jTable.setFont(new Font("Serif", Font.BOLD, 14));
            jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) { 
                  //do something maybe
                }
            });
            master.add(new JScrollPane(jTable), BorderLayout.CENTER);
            master.setBackground(Color.GRAY);
        }
        return master;
    }

} 