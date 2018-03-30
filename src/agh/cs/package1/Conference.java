package agh.cs.package1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Conference {
    private String conferenceName;
    private Date startDate;
    private Date endDate;
    private int locationID;
    private int studentDiscount;
    private int numDays;

    public Conference(Filler nameSetter, ArrayList<Address> locations) {
        this.conferenceName = nameSetter.getName();
        Random generator = new Random();
        int day = 1 + generator.nextInt(31);
        int month = 1 + generator.nextInt(12);
        int year = 2015 + generator.nextInt(4);
        this.startDate = new Date(day, month, year);

        numDays = 1 + generator.nextInt(3);
        day = day + numDays;
        this.endDate = new Date(day, month, year);
        endDate = endDate.check(endDate);

        this.studentDiscount = generator.nextInt(100);

        int pos = generator.nextInt(locations.size());
        this.locationID = pos + 1;
        locations.get(pos).take();
    }

    public int getNumDays() {
        return numDays;
    }

    public Date getStartTime() {
        return  startDate;
    }

    public void generate (PrintWriter printWriter) {
        Random generator = new Random();
        int weight = generator.nextInt(6);
        if (weight == 5) printWriter.println("INSERT Conferences (ConferenceName, StartDate, EndDate, LocationID) VALUES ('" + this.conferenceName + "','"
                + this.startDate.toString() + "','" + this.endDate.toString() + "'," + this.locationID + ");");
        else printWriter.println("INSERT Conferences (ConferenceName, StartDate, EndDate, LocationID, StudentDiscount) VALUES ('" + this.conferenceName + "','"
                + this.startDate.toString() + "','" + this.endDate.toString() + "'," + this.locationID + "," + this.studentDiscount + ");");
    }
}
