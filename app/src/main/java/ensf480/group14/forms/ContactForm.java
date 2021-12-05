package ensf480.group14.forms;

import ensf480.group14.external.Email;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ContactForm implements Form {
    // private String userEmailAddress;
    // private String message;

    public JPanel display(){
        JPanel panel = new JPanel();
        Dimension expectDimension = new Dimension(300, 300);

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));

        panel.setPreferredSize(expectDimension);
        panel.setMaximumSize(expectDimension);
        panel.setMinimumSize(expectDimension);

        panel.setBackground(Color.GRAY);
        
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contactLabel = new JLabel("Contact");
        contactLabel.setFont(new Font("Serif", Font.BOLD, 35));
        contactLabel.setSize(40,40);
        panel.add(contactLabel);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        panel.add(new JLabel("Enter Your Email Address"));
        JTextField userEmail = new JTextField();
        userEmail.setSize(190, 20);
        userEmail.setMaximumSize(new Dimension(190, 20));
        panel.add(userEmail);

        panel.add(new JLabel("Body"));
        JTextArea emailBody = new JTextArea(50,50);
        //JScrollPane sPane = new JScrollPane(emailBody);
        emailBody.setSize(300, 60);
        emailBody.setMaximumSize(new Dimension(300, 60));
        emailBody.setColumns(300);
        emailBody.setLineWrap(true);
        panel.add(emailBody);


        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(evt.getSource() == sendButton){
                    parseEmail(emailBody.getText(),userEmail.getText());
                    
                }
            }
        });
        panel.add(sendButton);

        return panel;
    }


    Email parseEmail(String body,String sendAddr){
        Email mail = new Email(body,sendAddr);
        return mail;
    }

   

    

   

   

   

   

   



    // For testing
    // public static void main(String[] args) {
    //     JFrame frame = new JFrame();
    //     ContactForm form = new ContactForm();
    //     frame.add(form.display());
    //     frame.setPreferredSize(new Dimension(524,368));
        
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.pack();
    //     frame.setLocationRelativeTo(null);
    //     frame.setVisible(true);
    // }
}

