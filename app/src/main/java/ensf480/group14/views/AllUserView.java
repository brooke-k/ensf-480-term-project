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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ensf480.group14.eventListeners.Listener;
import ensf480.group14.users.User;

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
    public JPanel display(ArrayList<User> users, Listener listen) {
        listen = listener;
        JPanel master = new JPanel(new BorderLayout());
        DefaultTableModel model = null;
        if (users == null)
            return master;
        if (users.isEmpty()) {
            JLabel noUsers = new JLabel("NO USERS");
            noUsers.setBackground(Color.GRAY);
            noUsers.setForeground(Color.PINK);
            noUsers.setFont(new Font("Serif", Font.BOLD, 65));
            master.add(noUsers);
            return master;
        }
        String[] columns = { "Email", "Type", "First Name", "Last Name" };
        if (!users.isEmpty()) {
            String[][] userList = new String[users.size()][7];
            int i = 0;
            for (User u : users) {
                userList[i][0] = u.getEmail();
                userList[i][1] = u.getType();
                if (u.getType().equals("landlord")) {
                    userList[i][2] = u.getFirstName();
                    userList[i][3] = u.getLastName();
                } else if (u.getType().equals("registered_renter")) {
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
                // do nothing for now
            }
        });
        master.add(new JScrollPane(jTable), BorderLayout.CENTER);
        master.setBackground(Color.GRAY);
        return master;
    }
}
