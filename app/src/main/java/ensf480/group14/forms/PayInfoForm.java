package ensf480.group14.forms;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PayInfoForm { //TODO: Add back the implements forms
    String firstName;
    String lastName;
    String cardNumber;
    String cvvCode;
    String expiryDate;
    private ActionListener listener;

	

	public JPanel display(){//ActionListener listen) {  //should save payment into database
        //listener = listen;
		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.setBackground(Color.GRAY);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel paymentLabel = new JLabel("Payment $"+" TODO");
		paymentLabel.setFont(new Font("Serif", Font.BOLD, 35));
		paymentLabel.setSize(40, 40);
        paymentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(paymentLabel);
        

		JLabel loginLabel = new JLabel("Enter your Payment Information");
		loginLabel.setFont(new Font("Serif", Font.BOLD, 30));
		loginLabel.setSize(40, 40);
		panel.add(loginLabel);

		panel.add(new JLabel("First Name"));
        JTextField fName = new JTextField();
        fName.setSize(190, 20);
		fName.setMaximumSize(new Dimension(190, 20));
		fName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
                
			}
            public void focusLost(FocusEvent e) {
				setFirstName(fName.getText());
			}
        });
        panel.add(fName);
        
		panel.add(new JLabel("Last Name"));
		JTextField lName = new JTextField();
		lName.setSize(190, 20);
		lName.setMaximumSize(new Dimension(190, 20));
		lName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				setLastName(lName.getText());
			}
		});
		panel.add(lName);


        panel.add(new JLabel("Card Number"));
		JTextField cardNum = new JTextField();
		cardNum.setSize(190, 20);
		cardNum.setMaximumSize(new Dimension(190, 20));
		cardNum.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
                if(checkCard(cardNum.getText()))
				    setCardNumber(cardNum.getText());
			}
		});
		panel.add(cardNum);

		panel.add(new JLabel("CVV"));
        JTextField cvvCode = new JTextField();
        cvvCode.setSize(190, 20);
		cvvCode.setMaximumSize(new Dimension(190, 20));
		cvvCode.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
                
			}
            public void focusLost(FocusEvent e) {
				setCVVCode(cvvCode.getText());
			}
        });
        panel.add(cvvCode);

		panel.add(new JLabel("Expiry Date(MM/YEAR)"));
        JTextField expiryDate = new JTextField();
        expiryDate.setSize(190, 20);
		expiryDate.setMaximumSize(new Dimension(190, 20));
		expiryDate.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
                
			}
            public void focusLost(FocusEvent e) {
				setExpiryDate(expiryDate.getText());
			}
        });
        panel.add(expiryDate);

		panel.add(Box.createRigidArea(new Dimension(1, 5)));

		JButton payButton = new JButton("Submit Payment");
		payButton.addActionListener(listener);
		panel.add(payButton);

		return panel;
	}

    private boolean checkCard(String num){
        if (!num.startsWith("4")) {
            return false;
        }

        // add all of the digits
        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            int digit = Integer.valueOf(num.substring(i, i + 1));
            if (i % 2 == 0) {  // double every other number, add digits
                digit *= 2;
                sum += (digit / 10) + (digit % 10);
            } else {
                sum += digit;
            }
        }
        // valid numbers add up to a multiple of 10
        return (sum % 10 == 0);
    }

       
    
    

	public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getCVVCode() {
        return cvvCode;
    }


    public void setCVVCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }


    public String getExpiryDate() {
        return expiryDate;
    }


    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    // For testing
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PayInfoForm payForm = new PayInfoForm();
		frame.add(payForm.display());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}