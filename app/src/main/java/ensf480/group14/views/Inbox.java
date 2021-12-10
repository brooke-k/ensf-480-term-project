/**
 * File: Inbox.java
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
import ensf480.group14.external.Email;

/**
 * Makes the emails in a neat form as a table into the Inbox
 */
public class Inbox {

    private Listener listener;

    /**
     * Constructors
     */
    public Inbox() {

    }

    /**
     * Takes in the mail and the action listener
     *
     * @param: Emails in an arraylist and the listener
     * @returns: All of them displayed in a JTable
     */
    public JPanel display(ArrayList<Email> mail, Listener listen) {

        listener = listen;
        JPanel master = new JPanel(new BorderLayout());
       
        if (mail == null||mail.isEmpty()) {
            JLabel noEmails = new JLabel("NO EMAILS :(");
            noEmails.setBackground(Color.GRAY);
            noEmails.setForeground(Color.PINK);
            noEmails.setFont(new Font("Serif", Font.BOLD, 65));
            master.add(noEmails,BorderLayout.CENTER);
            return master;
        }

        String[] columns = { "Sender", "Address", "Body", "ID" };
        String[][] mails = new String[mail.size()][4];
        int i = 0;
        for (Email m : mail) {
            // String s = new DecimalFormat("#.0#").format(p.getListingPrice());

            mails[i][0] = m.getSender();
            mails[i][1] = m.getSubject();
            mails[i][2] = m.getBody();
            mails[i][3] = m.getId().toString();
            // mails[i][1] = m.getAddress();
            // mails[i][2] = m.getEmailID();
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(mails, columns);

        JTable jTable = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }

        };
        jTable.getColumnModel().getColumn(3).setMinWidth(0);
        jTable.getColumnModel().getColumn(3).setMaxWidth(0);
        jTable.getTableHeader().setReorderingAllowed(false);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(rowSorter);
        jTable.setBackground(Color.GRAY);
        jTable.setFont(new Font("Serif", Font.BOLD, 14));
        jTable.setForeground(Color.PINK);
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // takes an email ID opens email view
                listener.openEmail(jTable.getValueAt(jTable.getSelectedRow(), 3).toString());
            }
        });
        master.add(new JScrollPane(jTable), BorderLayout.CENTER);
        master.setBackground(Color.GRAY);
        return master;
    }

    // For testing

    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // Inbox s = new Inbox();
    // // User user = new RegisteredRenter("an email", "an ID",
    // "registered_renter");
    // ArrayList<Email> emailTest = new ArrayList<Email>();
    // for (int i = 0; i < 40; i++) {
    // emailTest.add(new Email("HI", "Renter" + i + "@aol.com", "231" + i + "12
    // NorthMount Dr"));

    // }
    // JPanel p = new JPanel();
    // p = s.display(emailTest);
    // // JScrollPane sp = new JScrollPane(p);
    // // frame.setContentPane(sp);
    // frame.add(p);
    // frame.setPreferredSize(new Dimension(900, 600));

    // frame.pack();
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);

    // }

}
