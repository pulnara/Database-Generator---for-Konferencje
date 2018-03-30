package agh.cs.package1;

import java.io.PrintWriter;
import java.util.Random;

public class Workshop {
    private String name;
    private int conferenceDayID;
    private Date startTime;
    private Date endTime;
    private String location;
    private float price;
    private int participantsLimit;

    public Workshop(int confDayID, Filler nameSetter, Date dayStart) {
        this.name = nameSetter.getName();
        this.conferenceDayID = confDayID;
        Random generator = new Random();
        int hour = 10 + generator.nextInt(10);
        int minute = generator.nextInt(60);
        this.startTime = new Date(dayStart.day, dayStart.month, dayStart.year, hour, minute);
        this.endTime = new Date(dayStart.day, dayStart.month, dayStart.year, hour + 2, minute);
        this.location = nameSetter.getBuildingName();
        this.price = (float) 0.9999 * generator.nextInt(105);
        this.participantsLimit = 10 + generator.nextInt(20);
    }

    public int getConferenceDayID() {
        return this.conferenceDayID;
    }

    public int getParticipantsLimit() {
        return this.participantsLimit;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public float getPrice() {
        return this.price;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT Workshops (Name, ConferenceDayID, StartTime, EndTime, Location, Price, ParticipantsLimit) " +
                "VALUES ('" + this.name + "'," + conferenceDayID + "," + startTime.toStringWithTime() + "," + endTime.toStringWithTime() + ",'" + location +"'," + price + "," + participantsLimit + ");");
    }
}
