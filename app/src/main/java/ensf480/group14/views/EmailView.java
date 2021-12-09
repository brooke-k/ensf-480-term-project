package ensf480.group14.views;

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
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.concurrent.ThreadLocalRandom;


public class EmailView {

    private ActionListener listener;

	public EmailView() {

	}



	public JPanel display(Email email)//,ActionListener listen) {
{
        //listener = listen;
		JPanel panel = new JPanel(new BorderLayout(50,50));
            JPanel header = new JPanel(new GridLayout(0,1));
            // Top panel (address) 
            JLabel top = new JLabel(email.getSender(),SwingConstants.CENTER);
            top.setBackground(Color.GRAY);
            top.setForeground(Color.PINK);
            top.setFont(new Font("Serif", Font.BOLD, 25));
            header.add(top);
            
            JLabel top1 = new JLabel(email.getSubject(),SwingConstants.CENTER);
            top1.setBackground(Color.GRAY);
            top1.setForeground(Color.PINK);
            top1.setFont(new Font("Serif", Font.BOLD, 20));
            header.add(top1);
            header.setBackground(Color.GRAY);
            header.setForeground(Color.PINK);
            panel.add(header,BorderLayout.NORTH);


            // //Bottom Panel Contact Popout
            // JButton contactButton = new JButton("Contact Owner");
            // contactButton.setPreferredSize(new Dimension(5,40));
            // contactButton.addActionListener(listener);      //opens contact form ;  NEED TO KEEP TRACK OF CURRENT PROP use getCurrentProp
            // panel.add(contactButton,BorderLayout.SOUTH);

          
        
            
            JTextArea details = new JTextArea(email.getBody());
            details.setEditable(false);
            details.setBackground(Color.GRAY);
            details.setForeground(Color.PINK);
            details.setLineWrap(true);
            details.setWrapStyleWord(true);
            details.setFont(new Font("Serif", Font.BOLD, 20));
            panel.add(new JScrollPane(details),BorderLayout.CENTER);
            panel.setBackground(Color.GRAY);
            
            //Left/Center Panel
            // int i = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            // ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("images/"+i+".png"));
            // Image image = imageIcon.getImage();
            // Image newimg = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
            // imageIcon = new ImageIcon(newimg);
            // JLabel img = new JLabel(imageIcon);
            // test.setBackground(Color.GRAY);
            // test.setForeground(Color.PINK);
            // test.setFont(new Font("Serif", Font.BOLD, 40));
            // test.setEditable(false);
            // img.setPreferredSize(new Dimension(500,500));
            // // test.setHighlighter(null);
            // panel.add(img,BorderLayout.WEST);
            // panel.setBackground(Color.GRAY);



            
		return panel;
	}

	// For testing

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		// LoginForm login = getOnlyInstance();
        Email temp = new Email("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.","Tester@fake.com","12312 SouthHill ");
        
        EmailView em = new EmailView();
		
        frame.add(em.display(temp));
        //frame.setBackground(Color.GRAY);
        frame.setPreferredSize(new Dimension(900, 600));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

}
