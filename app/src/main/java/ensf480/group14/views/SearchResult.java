/**
 * File: SearchResult.java
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
public class SearchResult {

    private Listener listener;

    /**
     * Default constructor
     */
    public SearchResult() {

    }

    /**
     * The listeners need to be defined in the constructor
     */

    /**
     * Takes in the User and it's properties which are assigned to it
     *
     * @param: User object, and props for the Properties
     * @returns: All of them displayed in a JTable
     */
    public JPanel display(User user, ArrayList<Property> props) {

        JPanel master = new JPanel(new BorderLayout());
        DefaultTableModel model = null;
        if (user == null)
            return master;

        if (props == null || props.isEmpty()) {
            JLabel noResults = new JLabel("NO RESULTS :(");
            noResults.setBackground(Color.GRAY);
            noResults.setForeground(Color.RED);
            noResults.setFont(new Font("Serif", Font.BOLD, 65));
            master.add(noResults);
            return master;
        }

        if (!user.getType().equals("manager") && !user.getType().equals("landlord")) {
            String[] columns = { "Rent", "Address", "City Quadrant", "Type", "Number of Bedrooms",
                    "Number of Bathrooms",
                    "Furnished Status" };
            if (!props.isEmpty()) {
                String[][] properties = new String[props.size()][7];
                int i = 0;
                for (Property p : props) {
                    // String s = new DecimalFormat("#.0#").format(p.getListingPrice());
                    String s = "$";
                    s += String.format("%.02f", p.getRentCost());
                    properties[i][0] = s;
                    properties[i][1] = p.getAddress();
                    properties[i][2] = p.getCityQuad();
                    properties[i][3] = p.getType();
                    properties[i][4] = p.getNumBedrooms().toString();
                    properties[i][5] = p.getNumBathrooms().toString();
                    properties[i][6] = (p.isFurnished()) ? "Furnished" : "Unfurnished";
                    i++;
                }
                model = new DefaultTableModel(properties, columns);
            }
        }

        else if (user.getType().equals("manager") || user.getType().equals("landlord")) {
            String[] columns2 = { "Rent", "Address", "City Quadrant", "Type", "Number of Bedrooms",
                    "Number of Bathrooms",
                    "Furnished Status", "Visibility", "Rental Status" };
            if (!props.isEmpty()) {
                String[][] properties2 = new String[props.size()][9];
                int i = 0;
                for (Property p : props) {
                    // String s = new DecimalFormat("#.0#").format(p.getListingPrice());
                    String s = "$";
                    s += String.format("%.02f", p.getRentCost());
                    properties2[i][0] = s;
                    properties2[i][1] = p.getAddress();
                    properties2[i][2] = p.getCityQuad();
                    properties2[i][3] = p.getType();
                    properties2[i][4] = p.getNumBedrooms().toString();
                    properties2[i][5] = p.getNumBathrooms().toString();
                    properties2[i][6] = (p.isFurnished()) ? "Furnished" : "Unfurnished";
                    properties2[i][7] = (p.isVisibleToRenters()) ? "Visible" : "Unlisted";
                    properties2[i][8] = (p.isRented()) ? "Rented" : "Vacant";
                    i++;
                }
                model = new DefaultTableModel(properties2, columns2);
            }

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
            public void valueChanged(ListSelectionEvent event) { // all these have the address of the property in a
                                                                 // string
                if (!user.getType().equals("manager") && !user.getType().equals("landlord")) {
                    listener.openProperty(jTable.getValueAt(jTable.getSelectedRow(), 1).toString()); // opens a
                                                                                                     // property
                                                                                                     // page
                } else if (user.getType().equals("manager")) {
                    listener.openVisibilityPanel(jTable.getValueAt(jTable.getSelectedRow(), 1).toString()); // opens
                                                                                                            // //
                                                                                                            // the
                                                                                                            // manager
                                                                                                            // dialog
                                                                                                            // // to
                                                                                                            // edit
                                                                                                            // visibility
                } else if (user.getType().equals("landlord")
                        && (user.owns(jTable.getValueAt(jTable.getSelectedRow(), 1).toString()))) { // opens the
                    // edit property
                    // page

                    listener.openEditProperty(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());

                }
            }
        });
        master.add(new JScrollPane(jTable), BorderLayout.CENTER);
        master.setBackground(Color.GRAY);
        return master;
    }
}

// For testing

// public static void main(String[] args) {
// JFrame frame = new JFrame();
// SearchResult s = new SearchResult();
// ObjectId id = new ObjectId();
// User user = new RegisteredRenter("an email", id, "registered_renter");
// ArrayList<Property> propertyTest = new ArrayList<Property>();
// for (int i = 0; i < 100; i++) {
// Property temp = new Property();
// temp.setRentCost((i + 1) * 500.1);
// temp.setAddress("111111" + i);
// temp.setCityQuad("NW");
// temp.setNumBedrooms(2);
// temp.setNumBathrooms(3.0);
// temp.setFurnished(true);
// propertyTest.add(temp);
//
// JPanel p = new JPanel();
// p = s.display(user, propertyTest);
// // JScrollPane sp = new JScrollPane(p);
// // frame.setContentPane(sp);
// frame.add(p);
// frame.setPreferredSize(new Dimension(900, 600));

// frame.pack();
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// frame.setLocationRelativeTo(null);
// frame.setVisible(true);

// }
