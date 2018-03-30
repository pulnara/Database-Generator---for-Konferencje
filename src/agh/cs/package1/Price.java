package agh.cs.package1;

import java.io.PrintWriter;
import java.util.Random;

public class Price {
    private int conferenceID;
    private int daysBeforeStart;
    private float price;

    public Price(int confID, int daysNum, float price) {
        this.conferenceID = confID;
        this.daysBeforeStart = daysNum;
        Random generator = new Random();
        this.price = price + 100 * generator.nextFloat();
    }

    public float getPrice() {
        return this.price;
    }

    public int getConferenceID() {
        return this.conferenceID;
    }

    public int getDaysBeforeStart() {
        return this.daysBeforeStart;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT Prices (ConferenceID, DaysBeforeStart, Price) VALUES (" + this.conferenceID + "," + this.daysBeforeStart + "," + this.price + ");");
    }
}
