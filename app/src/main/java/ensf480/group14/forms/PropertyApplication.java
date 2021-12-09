package ensf480.group14.forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PropertyApplication implements Form {
    // private String userEmailAddress;
    // private String message;
    int id;
    String addr;
    int numBed;
    double numBath;
    boolean furnish;
    String cityQuad;
    double price;

    public JPanel display(ActionListener listener){
        JPanel panel = new JPanel();
        Dimension expectDimension = new Dimension(300, 300);

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));

        panel.setPreferredSize(expectDimension);
        panel.setMaximumSize(expectDimension);
        panel.setMinimumSize(expectDimension);

        panel.setBackground(Color.GRAY);
        
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contactLabel = new JLabel("Property Application");
        contactLabel.setFont(new Font("Serif", Font.BOLD, 35));
        contactLabel.setSize(40,40);
        panel.add(contactLabel);

        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        panel.add(new JLabel("Address"));
        JTextField address = new JTextField();
        address.setSize(190, 20);
        address.setMaximumSize(new Dimension(190, 20));
        address.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                setAddress(address.getText());
            }
        });
        panel.add(address);

        panel.add(new JLabel("Number of Bedrooms"));
        JTextField numBedrooms = new JTextField();
        numBedrooms.setSize(190, 20);
        numBedrooms.setMaximumSize(new Dimension(190, 20));
        numBedrooms.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                setNumBed(Integer.parseInt(numBedrooms.getText()));
            }
        });
        panel.add(numBedrooms);

        panel.add(new JLabel("Number of Bathrooms"));
        JTextField numBathrooms = new JTextField();
        numBathrooms.setSize(190, 20);
        numBathrooms.setMaximumSize(new Dimension(190, 20));
        numBathrooms.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                setNumBath(Double.parseDouble(numBathrooms.getText()));
            }
        });
        panel.add(numBathrooms);
        
        JLabel checkLabel = new JLabel("Furnished");
        panel.add(checkLabel);
        JCheckBox furnished = new JCheckBox();
        furnished.setOpaque(false);

        //furnished.setText("Furnished");
        //furnished.setSize(20, 20);
        furnished.setMaximumSize(new Dimension(190, 20));
        furnished.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                setFurnish(furnished.isSelected());
            }
        });
        panel.add(furnished);

        panel.add(new JLabel("City Quadrant"));
        JTextField quad = new JTextField();
        quad.setSize(190, 20);
        quad.setMaximumSize(new Dimension(190, 20));
        quad.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                setCityQuad(quad.getText());
            }
        });
        panel.add(quad);

        panel.add(new JLabel("Price"));
        JTextField price = new JTextField();
        price.setSize(190, 20);
        price.setMaximumSize(new Dimension(190, 20));
        price.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                setPrice(Double.parseDouble(price.getText()));
            }
        });
        panel.add(price);


        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton submitButton = new JButton("Submit Application");
        submitButton.addActionListener(listener);  //needs to open pay form
        panel.add(submitButton);

        return panel;
    }


    

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return addr;
    }


    public void setAddress(String address) {
        this.addr = address;
    }

    public int getNumBed() {
        return numBed;
    }


    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    public double getNumBath() {
        return numBath;
    }

    public void setNumBath(double numBath) {
        this.numBath = numBath;
    }


    public boolean isFurnished() {
        return furnish;
    }


    public void setFurnish(boolean furnish) {
        this.furnish = furnish;
    }

    public String getCityQuad() {
        return cityQuad;
    }


    public void setCityQuad(String cityQuad) {
        this.cityQuad = cityQuad;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }







    // For testing
    // public static void main(String[] args) {
    //     JFrame frame = new JFrame();
    //     PropertyApplication form = new PropertyApplication();
    //     frame.add(form.display());
    //     frame.setPreferredSize(new Dimension(624,768));
        
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.pack();
    //     frame.setLocationRelativeTo(null);
    //     frame.setVisible(true);
    // }
}

