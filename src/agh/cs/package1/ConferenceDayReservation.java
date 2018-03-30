package agh.cs.package1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;


public class ConferenceDayReservation {
    private int ReservationID;
    private int ConferenceDayID;
    private int NumReservations;
    ArrayList<WorkshopReservation> workshopReservations = new ArrayList<>();

    public ConferenceDayReservation(int ReservationID, int ConferenceDayID, ArrayList<ConferenceDayReservation> list, ConferenceDay conferenceDay, ArrayList<Reservation> reservations) {
        Random generator = new Random();
        this.ReservationID = ReservationID;
        this.ConferenceDayID = ConferenceDayID + 1;

        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getConferenceDayID() == this.ConferenceDayID) counter += list.get(i).getNumReservations();
        }

        int howMany = 15;
        int howManyEmployees = reservations.get(ReservationID - 1).howManyEmployees();
        if (howManyEmployees < howMany) howMany = howManyEmployees;
        int numRes = 1 + generator.nextInt(howMany);
        if (numRes + counter <= conferenceDay.getParticipantsLimit()) this.NumReservations = numRes;
        else this.NumReservations = conferenceDay.getParticipantsLimit() - counter;
        if (this.NumReservations <= 0) {
            //System.out.println("!!!!!!!!!!!!!!!!!!!!CDR");
            this.NumReservations = numRes;
            reservations.get(ReservationID - 1).cancel();
        }
    }

    public int getConferenceDayID() {
        return this.ConferenceDayID;
    }

    public int getNumReservations() {
        return NumReservations;
    }

    public int getReservationID() {
        return this.ReservationID;
    }

    public void addWorkshopReservation(WorkshopReservation workshopReservation) {
        this.workshopReservations.add(workshopReservation);
    }

    public ArrayList<WorkshopReservation> getWorkshopReservations() {
        return this.workshopReservations;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT ConferenceDayReservations (ReservationID, ConferenceDayID, NumReservations) VALUES (" + this.ReservationID + "," + this.ConferenceDayID + "," + this.NumReservations + ");");
    }

}
