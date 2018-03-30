package agh.cs.package1;

import java.io.PrintWriter;
import java.util.Random;

public class ConferenceDay {
    private int number;
    private int ConferenceDayID;
    private int conferenceID;
    private int participantsLimit;
    private Date startDate;

    public ConferenceDay(int number, int conferenceID, Date conferenceStart, int ID) {
        this.number = number;
        this.conferenceID = conferenceID;
        Random generator = new Random();
        this.participantsLimit = 100 + generator.nextInt(100);
        this.startDate = new Date(conferenceStart.day, conferenceStart.month, conferenceStart.year);
        this.startDate.add(number);
        this.ConferenceDayID = ID;
    }

    public int getConferenceID() {
        return  this.conferenceID;
    }

    public Date getDate() {
        return this.startDate;
    }

    public int getParticipantsLimit() {
        return this.participantsLimit;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT ConferenceDays (ConferenceID, Day, ParticipantsLimit) VALUES (" + this.conferenceID + "," + this.number + "," + this.participantsLimit + ");");
    }
}
