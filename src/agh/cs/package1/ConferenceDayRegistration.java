package agh.cs.package1;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ConferenceDayRegistration {
    private int conferenceDayReservationID;
    private int attendeeID;
    ArrayList<Attendee> attendees = new ArrayList<>();

    public ConferenceDayRegistration(int conferenceDayReservationID, int attendeeID) {
        this.conferenceDayReservationID = conferenceDayReservationID;
        this.attendeeID = attendeeID;
    }

    public void addAttendee(Attendee attendee) {
        this.attendees.add(attendee);
    }

    public ArrayList<Attendee> getAttendees() {
        return this.attendees;
    }

    public int getConferenceDayReservationID() {
        return this.conferenceDayReservationID;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT ConferenceDayRegistrations (ConferenceDayReservationID, AttendeeID) VALUES (" + this.conferenceDayReservationID + "," + this.attendeeID + ");");
    }
}
