package agh.cs.package1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class WorkshopReservation {
    private int reservationID;
    private int workshopID;
    private int numReservations;
    private int workshopReservationID;

    public WorkshopReservation(int reservationID, int workshopID, int maxRes, ArrayList<WorkshopReservation> list, int partLimit, ArrayList<Reservation> reservations) {
        this.reservationID = reservationID;
        this.workshopID = workshopID;

        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWorkshopID() == this.workshopID) counter += list.get(i).getNumReservations();
        }

        Random generator = new Random();
        int numRes = 1 + generator.nextInt(maxRes);
        if (numRes + counter <= partLimit) this.numReservations = numRes;
        else this.numReservations = partLimit - counter;
        if (this.numReservations <= 0) {
            //System.out.println("!!!!!!!!!!!!!!!!!!!WR");
            this.numReservations = numRes;
            reservations.get(reservationID - 1).cancel();
        }
    }

    public void setID(ArrayList<WorkshopReservation> list) {
        this.workshopReservationID = list.size();
    }

    public int getWorkshopReservationID() {
        return this.workshopReservationID;
    }

    public int getWorkshopID() {
        return this.workshopID;
    }

    public int getNumReservations() {
        return this.numReservations;
    }

    public int getReservationID() {
        return this.reservationID;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT WorkshopReservations (ReservationID, WorkshopID, NumReservations) VALUES (" + this.reservationID + "," + this. workshopID + "," + this.numReservations + ");");
    }
}
