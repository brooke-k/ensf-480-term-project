package ensf480.group14.dbcontrol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.text.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class Report {
    // Attributes
    private Calendar dateRangeStart;
    private Calendar dateRangeEnd;
    private int numPropertiesListed;
    private int numPropertiesRented;
    private int numPropertiesActive;

    File reportFile; // Can be changed in the future,
                     // used for testing purposes here\
                     // Output file for the report to go to.
    BufferedWriter bufReportWriter;

    // private ArrayList<Property> propertiesRented;
    // Currently throws an error because the Property class has not been made yet.

    // Methods
    public Report(Calendar startDate, Calendar endDate, RegisteredRenterDBController controller) throws IOException {
        dateRangeStart = startDate;
        dateRangeEnd = endDate;
        generateReport(controller);
    }

    public String generateReport(RegisteredRenterDBController dbControl) throws IOException {

        /*
         * reportFile = new File("./src/main/outputs/report.txt");
         * if (reportFile.exists()) {
         * reportFile.delete(); // Done to clear any older report copies,
         * // for testing
         * reportFile.createNewFile();
         * } else {
         * reportFile.createNewFile();
         * }
         *
         * bufReportWriter = new BufferedWriter(new FileWriter(reportFile));
         *
         * String reportString; // String-version of report to write
         * MongoCollection propertyCollection = dbControl.getAllProperties();
         *
         * FindIterable<Document> docIterator = propertyCollection.find();
         * Iterator collectionIter = docIterator.iterator();
         * while (collectionIter.hasNext()) {
         * bufReportWriter.write(collectionIter.next().toString());
         * }
         * bufReportWriter.close();
         */
        return null;
    }

    public Calendar getDateRangeStart() {
        return dateRangeStart;
    }

    public void setDateRangeStart(Calendar dateRangeStart) {
        this.dateRangeStart = dateRangeStart;
    }

    public Calendar getDateRangeEnd() {
        return dateRangeEnd;
    }

    public void setDateRangeEnd(Calendar dateRangeEnd) {
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
