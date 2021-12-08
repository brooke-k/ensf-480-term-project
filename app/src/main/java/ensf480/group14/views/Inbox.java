package ensf480.group14.views;


import ensf480.group14.dbcontrol.DatabaseController;
import ensf480.group14.external.Email;
import ensf480.group14.external.Property;
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

public class Inbox {


	private ActionListener listener;

	public Inbox() {

	}

	public Inbox(ActionListener listen) {
		listener = listen;
	}


	public JPanel display(User user,Email [] mail) {

        JPanel master = new JPanel(new BorderLayout());
        String[] columns ={"Sender","Body"};
        String [][] mails = new String[mail.length][2];
        int i = 0;
        for(Email m :mail){
            //String s = new DecimalFormat("#.0#").format(p.getListingPrice());
			mails[i][0] = m.getSender();
			mails[i][1] = m.getBody();
			i++;
        }


		DefaultTableModel model = new DefaultTableModel(mails,columns);
		
		JTable jTable = new JTable(model){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
             }

        };		
        jTable.getTableHeader().setReorderingAllowed(false);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(rowSorter);
        jTable.setBackground(Color.GRAY);
        jTable.setFont(new Font("Serif", Font.BOLD, 14));
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                
                System.out.println(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());
            }
        });
        master.add(new JScrollPane(jTable),BorderLayout.CENTER);
        master.setBackground(Color.GRAY);
        return master;
	}
	
	// For testing

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Inbox s = new Inbox();
        User user = new RegisteredRenter("an email", "an ID", "registered_renter");
        Property [] propertyTest = new Property[40];
        for(int i = 0; i < propertyTest.length;i++){
            propertyTest[i] = new Property();
			propertyTest[i].setListingPrice((i+1)*50000.1);
			propertyTest[i].setAddress("111111"+i);
			propertyTest[i].setCityQuad("NW");
			propertyTest[i].setNumBedrooms(2);
			propertyTest[i].setNumBathrooms(3.0);
			propertyTest[i].setFurnished(true);
        }
		JPanel p = new JPanel();
        p = s.display(user, propertyTest);
        //JScrollPane sp = new JScrollPane(p);
        //frame.setContentPane(sp);
        frame.add(p);
        frame.setPreferredSize(new Dimension(900, 600));
       
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       
    }

}


