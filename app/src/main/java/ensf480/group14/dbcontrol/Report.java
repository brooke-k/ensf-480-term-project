package ensf480.group14.dbcontrol;

import java.util.Calendar;


public class Report {
    // Attributes
    private Calendar dateRangeStart;
    private Calendar dateRangeEnd;
    private int numPropertiesListed;
    private int numPropertiesRented;
    private int numPropertiesActive;

    // private ArrayList<Property> propertiesRented;
    // Currently throws an error because the Property class has not been made yet.

    // Methods
    public Report(Calendar startDate, Calendar endDate){
        dateRangeStart = startDate;
        dateRangeEnd = endDate;
    }

    public String generateReport() {
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
};
