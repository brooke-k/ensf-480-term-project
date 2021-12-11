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

package ensf480.group14.dbcontrol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ensf480.group14.external.Property;

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
    public Report(String startDate, String endDate) throws IOException {
        dateRangeStart = startDate;
        dateRangeEnd = endDate;
        generateReport();
    }

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

            ArrayList<Property> propertyArray = dbControl.getPropertiesListedWithin(dateRangeStart, dateRangeEnd);

            bufReportWriter.write("\n\rTotal number of properties listed between ");
            bufReportWriter.write(dateRangeStart.toString() + " and ");
            bufReportWriter.write(dateRangeEnd.toString() + ": " + propertyArray.size() + "\n\r");

            propertyArray.clear();
            propertyArray = dbControl.getPropertiesRentedWithin(dateRangeStart, dateRangeEnd);



            bufReportWriter.write("\n\rTotal number of properties rented between ");
            bufReportWriter.write(dateRangeStart.toString() + " and ");
            bufReportWriter.write(dateRangeEnd.toString() + ": " + propertyArray.size() + "\n\r");



            propertyArray.clear();
            propertyArray = dbControl.getActiveProperties();

            bufReportWriter.write("\n\rTotal number of active properties");
            bufReportWriter.write(dateRangeStart.toString() + " and ");
            bufReportWriter.write(dateRangeEnd.toString() + ": " + propertyArray.size() + "\n\r");

            propertyArray.clear();
            propertyArray = dbControl.getPropertiesRentedWithin(dateRangeStart, dateRangeEnd);

            bufReportWriter.write("\n\rProperties rented between ");
            bufReportWriter.write(dateRangeStart.toString() + " and ");
            bufReportWriter.write(dateRangeEnd.toString() + ":\n\r");
            for (Property p : propertyArray) {
                bufReportWriter.write("\n\r" + p.toString());
            }

            bufReportWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String getDateRangeStart() {
        return dateRangeStart;
    }

    public void setDateRangeStart(String dateRangeStart) {
        this.dateRangeStart = dateRangeStart;
    }

    public String getDateRangeEnd() {
        return dateRangeEnd;
    }

    public void setDateRangeEnd(String dateRangeEnd) {
        this.dateRangeEnd = dateRangeEnd;
    }

    public int getNumPropertiesListed() {
        return numPropertiesListed;
    }

    public void setNumPropertiesListed(int numPropertiesListed) {
        this.numPropertiesListed = numPropertiesListed;
    }

    public int getNumPropertiesRented() {
        return numPropertiesRented;
    }

    public void setNumPropertiesRented(int numPropertiesRented) {
        this.numPropertiesRented = numPropertiesRented;
    }

    public int getNumPropertiesActive() {
        return numPropertiesActive;
    }

    public void setNumPropertiesActive(int numPropertiesActive) {
        this.numPropertiesActive = numPropertiesActive;
    }
}
