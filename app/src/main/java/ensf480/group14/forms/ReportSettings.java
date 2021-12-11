/**
 * File: ReportSettings.java
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

package ensf480.group14.forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

import org.bson.types.ObjectId;

import ensf480.group14.eventListeners.Listener;
import ensf480.group14.external.Property;

/**
 * View for landlords to edit property
 *
 */
public class ReportSettings implements Form{
    // private String userEmailAddress;
    // private String message;

    String startDate;
    String endDate;


    int startDay;
    int startMonth;
    int startYear;


    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public int getStartDay() {
        return startDay;
    }
    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }
    public int getStartMonth() {
        return startMonth;
    }
    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }
    public int getStartYear() {
        return startYear;
    }
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    public int getEndDay() {
        return endDay;
    }
    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }
    public int getEndMonth() {
        return endMonth;
    }
    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }
    public int getEndYear() {
        return endYear;
    }
    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
    int endDay;
    int endMonth;
    int endYear;

    Pattern pat;
    Matcher mat;
    boolean notNumerical;
    private Listener listener;

    public ReportSettings(){
        pat = Pattern.compile("[^0-9]");
    }
    /**
     *
     * basically preference form except we overwrite an existing property
     */
    public JPanel display(Listener listen) {

        listener = listen;
        JPanel panel = new JPanel();

        Dimension expectDimension = new Dimension(300, 300);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        panel.setPreferredSize(expectDimension);
        panel.setMaximumSize(expectDimension);
        panel.setMinimumSize(expectDimension);

        panel.setBackground(Color.GRAY);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel reportSettingsLabel = new JLabel("Report Settings");
        reportSettingsLabel.setFont(new Font("Serif", Font.BOLD, 35));
        reportSettingsLabel.setSize(40, 40);
        panel.add(reportSettingsLabel);


        panel.add(new JLabel("Start Date"));
        JTextField startDayField = new JTextField("Day");
        startDayField.setSize(190, 20);
        startDayField.setMaximumSize(new Dimension(190, 20));
        startDayField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(startDayField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setStartDay(-1);
                } else {
                    setStartDay(Integer.parseInt(startDayField.getText()));
                }
            }
        });
        panel.add(startDayField);
        JTextField startMonthField = new JTextField("Month");
        startMonthField.setSize(190, 20);
        startMonthField.setMaximumSize(new Dimension(190, 20));
        startMonthField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(startMonthField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setStartMonth(-1);
                } else {
                    setStartMonth(Integer.parseInt(startMonthField.getText()));
                }
                
            }
        });
        panel.add(startMonthField);
        JTextField startYearField = new JTextField("Year");
        startYearField.setSize(190, 20);
        startYearField.setMaximumSize(new Dimension(190, 20));
        startYearField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                if(notNumerical){
                    setStartYear(-1);
                } else {
                    setStartYear(Integer.parseInt(startYearField.getText()));
                }
            }
        });
        panel.add(startYearField);

        panel.add(new JLabel("End Date"));
        JTextField endDayField = new JTextField("Day");
        endDayField.setSize(190, 20);
        endDayField.setMaximumSize(new Dimension(190, 20));
        endDayField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(endDayField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setEndDay(-1);
                } else {
                    setEndDay(Integer.parseInt(endDayField.getText()));
                }
                
            }
        });
        panel.add(endDayField);
        JTextField endMonthField = new JTextField("Month");
        endMonthField.setSize(190, 20);
        endMonthField.setMaximumSize(new Dimension(190, 20));
        endMonthField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(endMonthField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setEndMonth(-1);
                } else {
                    setEndMonth(Integer.parseInt(endMonthField.getText()));
                }
                
            }
                
                
        });
        panel.add(endMonthField);
        JTextField endYearField = new JTextField("Year");
        endYearField.setSize(190, 20);
        endYearField.setMaximumSize(new Dimension(190, 20));
        endYearField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                mat = pat.matcher(endYearField.getText());
                notNumerical = mat.find();
                if(notNumerical){
                    setEndYear(-1);
                } else {
                    setEndYear(Integer.parseInt(endYearField.getText()));
                }
          
            }
        });
        panel.add(endYearField);



        panel.add(Box.createRigidArea(new Dimension(1, 5)));

        JButton makeReportButton = new JButton("Get Report");
        makeReportButton.addActionListener(listener);
        panel.add(makeReportButton);

        

        return panel;
    }



    // For testing
    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // ReportSettings editView = new ReportSettings();
    // Property temp = new Property();
    // temp.setRentCost(500.1);
    // temp.setType("House");
    // temp.setAddress("111111");
    // temp.setCityQuad("NW");
    // temp.setNumBedrooms(2);
    // temp.setNumBathrooms(3.0);
    // temp.setFurnished(true);

    // frame.add(editView.display(temp));
    // frame.setPreferredSize(new Dimension(624, 768));

    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.pack();
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);
    // }
}
