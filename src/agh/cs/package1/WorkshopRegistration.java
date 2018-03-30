package agh.cs.package1;

import java.io.PrintWriter;

public class WorkshopRegistration {
    private int conferenceDayReservationID;
    private int workshopReservationID;
    private int attendeeID;

    public WorkshopRegistration(int conferenceDayReservationID, int workshopReservationID, int attendeeID) {
        this.conferenceDayReservationID = conferenceDayReservationID;
        this.workshopReservationID = workshopReservationID;
        this.attendeeID = attendeeID;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT WorkshopRegistrations (ConferenceDayReservationID, WorkshopReservationID, AttendeeID) VALUES ("
                + this.conferenceDayReservationID + "," + this.workshopReservationID + "," + this.attendeeID + ");");
    }
}
