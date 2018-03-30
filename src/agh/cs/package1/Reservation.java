package agh.cs.package1;

import java.io.PrintWriter;
import java.util.Random;

public class Reservation {
    private int CustomerID;
    private Integer paymentID;
    private Date date;
    private int isCancelled;
    private Date ConferenceDayDate;
    private float tobePaid;

    public Reservation(int CustomerID) {
        this.CustomerID = CustomerID;
        this.tobePaid = 0;
        this.paymentID = -1;

    }

    public void setConferenceDayDate (Date date) {
        this.ConferenceDayDate = date;
    }

    public void setDate() {
        Random generator = new Random();
        int day = 1 + generator.nextInt(31);
        int month = 1 + generator.nextInt(12);
        int year = 2015 + generator.nextInt(4);
        int hour = 0 + generator.nextInt(24);
        int minute = 0 + generator.nextInt(60);
        Date date  = new Date(day, month, year, hour, minute);
        while (this.ConferenceDayDate.getDifference(date) <= 0 || this.ConferenceDayDate.getDifference(date) > 180) {
            day = 1 + generator.nextInt(31);
            month = 1 + generator.nextInt(12);
            year = 2015 + generator.nextInt(4);
            hour = 0 + generator.nextInt(24);
            minute = 0 + generator.nextInt(60);
            date = new Date(day, month, year, hour, minute);
        }
        this.date = date;
    }

    public void setDate(int day, int month, int year, int hour, int minute) {
        this.date = new Date(day, month, year, hour, minute);
    }

    public Date getDate() {
        return this.date;
    }

    public void generate (PrintWriter printWriter) {
        if (this.paymentID > 0)
            printWriter.println("INSERT Reservations (CustomerID, Date, PaymentID,  IsCancelled) VALUES ("
                    + this.CustomerID + ",'" + this.date + "'," + this.paymentID + "," + this.isCancelled + ");");
        else  printWriter.println("INSERT Reservations (CustomerID, Date, IsCancelled) VALUES ("
                + this.CustomerID + ",'" + this.date + "'," + this.isCancelled + ");");
    }

    public void cancel() {
        this.isCancelled = 1;
    }

    public int getCustomerID() {
        return  this.CustomerID;
    }

    public int howManyEmployees() {
        return Main.customers.get(CustomerID - 1).attendees.size();
    }

    public void increaseToBePaid(float howMuch) {
        this.tobePaid += howMuch;
    }

    public float getTobePaid() {
        return this.tobePaid;
    }

    public void setPayment(int ID) {
        this.paymentID = ID;
    }

    public boolean isCancelled() {
        return this.isCancelled == 1;
    }

}
