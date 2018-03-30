package agh.cs.package1;

import java.io.PrintWriter;

public class Payment {
    private int paymentID;
    private float value;
    private Date date;

    public Payment(Date date, float value, int ID) {
        this.date = date;
        this.value = value;
        this.paymentID = ID;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT Payments (Value, Date) VALUES (" + this.value + ",'" + this.date.toString() + "');");
    }
}
