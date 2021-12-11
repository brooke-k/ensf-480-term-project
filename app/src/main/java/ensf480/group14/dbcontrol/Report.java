/**
 * File: Report.java
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
/**
 *  The folder which the class lies in the project. 
 */
package ensf480.group14.dbcontrol;
/**
 * The import statements used in order for the code to work. 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ensf480.group14.external.Property;

/**
 * For creating the report which has the attributs which are in the report which the Manager user can generate when the button 
 * is pressed in their homepage. Writes into the file which can be viewed by the manager for the semenatics about properties, 
 * and other information. Not implemented by anything a standalone class. 
 */
public class Report {
    // Attributes
    private String dateRangeStart;
    private String dateRangeEnd;
    private int numPropertiesListed;
    private int numPropertiesRented;
    private int numPropertiesActive;

    static File reportFile; // Can be changed in the future,
    // used for testing purposes here\
    // Output file for the report to go to.
    static BufferedWriter bufReportWriter;

    // private ArrayList<Property> propertiesRented;
    // Currently throws an error because the Property class has not been made yet.

    // Methods
    /**
    * Default constructor which has the start and end date for the ranges. 
     */
    public Report(String startDate, String endDate) throws IOException {
        dateRangeStart = startDate;
        dateRangeEnd = endDate;
        generateReport();
    }
    /**
    * This create a new file called report.txt with the following requirements mentioned in the document are being w
    * wrriten into here. 
    * Uses a buffered writer to do this. 
    * @params: Takes in nothing 
    * @returns: A String with all of the information displaying. 
     */
    private String generateReport() {
        ManagerDBController dbControl = new ManagerDBController();

        reportFile = new File("./src/main/outputs/report.txt");
        if (reportFile.exists()) {
            reportFile.delete(); // Done to clear any older report copies,
            // for testing
            try {
                reportFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                reportFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            bufReportWriter = new BufferedWriter(new FileWriter(reportFile));
            

            int numberListedProperties = ManagerDBController.getNumProeprtiesListedWithin(dateRangeStart, dateRangeEnd);

            ArrayList<Property> rentalArray = ManagerDBController.getPropertiesRentedWithin(dateRangeStart, dateRangeEnd);
            int numRentals = rentalArray.size();
            int totalActive = ManagerDBController.getActiveProperties().size();

            bufReportWriter.write("MANAGERIAL REPORT\n\r");
            bufReportWriter.write("--------------------------------------------------------------\n\r");
            bufReportWriter.write("Generated for dates between " + dateRangeStart + " and " + dateRangeEnd + "\n\r");
            bufReportWriter.write("\n\n\n\r");

            bufReportWriter.write("\n\rTotal number of properties listed between ");
            bufReportWriter.write(dateRangeStart.toString() + " and ");
            bufReportWriter.write(dateRangeEnd.toString() + ": " + numberListedProperties + "\n\r");
            bufReportWriter.write("--------------------------------------------------------------\n\r");
            
            bufReportWriter.write("\n\rTotal number of properties rented between ");
            bufReportWriter.write(dateRangeStart.toString() + " and ");
            bufReportWriter.write(dateRangeEnd.toString() + ": " + numRentals + "\n\r");        
            bufReportWriter.write("--------------------------------------------------------------\n\r");

            bufReportWriter.write("\n\rTotal number of active properties");
            bufReportWriter.write(dateRangeStart.toString() + " and ");
            bufReportWriter.write(dateRangeEnd.toString() + ": " + totalActive + "\n\r");
            
            bufReportWriter.write("--------------------------------------------------------------\n\r");

            bufReportWriter.write("\n\rProperties rented between ");
            bufReportWriter.write(dateRangeStart.toString() + " and ");
            bufReportWriter.write(dateRangeEnd.toString() + ":\n\r");
            for (Property p : rentalArray) {
                bufReportWriter.write("\n\r" + p.toString());
            }
            bufReportWriter.write("--------------------------------------------------------------\n\r");
            bufReportWriter.write("END OF REPORT\n\r");
            bufReportWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
    /**
    * This is for the getting the date range start which is for starting the period. 
    * @params: Takes in nothing 
    * @returns: A String with the date range start.
     */
    public String getDateRangeStart() {
        return dateRangeStart;
    }

    /**
    * This is for the setting the date range start which is for starting the period. 
    * @params: Takes in the date to start the period. 
    * @returns: Nothing. 
     */
    public void setDateRangeStart(String dateRangeStart) {
        this.dateRangeStart = dateRangeStart;
    }

    /**
    * This is for the getting the date range end which is for ending the period. 
    * @params: Takes in nothing 
    * @returns: A String with the date range end.
     */
    public String getDateRangeEnd() {
        return dateRangeEnd;
    }
    
    /**
    * This is for the setting the date range end which is for ending the period. 
    * @params: Takes in the date for end period. 
    * @returns: Nothing. 
     */
    public void setDateRangeEnd(String dateRangeEnd) {
        this.dateRangeEnd = dateRangeEnd;
    }

    /**
    * This is for the getting the number of properties which are listed in the application system
    * @params: Takes in nothing 
    * @returns: A int with the total amount
     */
    public int getNumPropertiesListed() {
        return numPropertiesListed;
    }   

    /**
    * This is for the setting the number of properties which are listed in the application system
    * @params: Takes in an integer of the properties which are listed
    * @returns: Nothing. 
     */
    public void setNumPropertiesListed(int numPropertiesListed) {
        this.numPropertiesListed = numPropertiesListed;
    }

    /**
    * This is for the getting the number of properties which are rented in the application system
    * @params: Takes in nothing 
    * @returns: A int with the total amount. 
     */
    public int getNumPropertiesRented() {
        return numPropertiesRented;
    }   

    /**
    * This is for the setting the number of properties which are rented in the application system
    * @params: Takes in an integer of the properties which are rented
    * @returns: Nothing. 
     */
    public void setNumPropertiesRented(int numPropertiesRented) {
        this.numPropertiesRented = numPropertiesRented;
    }

    /**
    * This is for the getting the number of properties which are active in the application system
    * @params: Takes in nothing 
    * @returns: A int with the total amount. 
     */
    public int getNumPropertiesActive() {
        return numPropertiesActive;
    }

    /**
    * This is for the setting the number of properties which are active in the application system
    * @params: Takes in an integer of the properties which are active
    * @returns: Nothing. 
     */
    public void setNumPropertiesActive(int numPropertiesActive) {
        this.numPropertiesActive = numPropertiesActive;
    }
}
