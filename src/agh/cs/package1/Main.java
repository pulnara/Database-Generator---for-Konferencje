package agh.cs.package1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    static ArrayList<Conference> conferences = new ArrayList<>();
    static ArrayList<Address> locations = new ArrayList<>();
    static ArrayList<Price> prices = new ArrayList<>();
    static ArrayList<ConferenceDay> conferenceDays = new ArrayList<>();
    static ArrayList<Workshop> workshops = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<PrivateCustomer> privateCustomers = new ArrayList<>();
    static ArrayList<Company> companies = new ArrayList<>();
    static ArrayList<Attendee> attendees = new ArrayList<>();
    static ArrayList<Payment> payments = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static ArrayList<ConferenceDayReservation> conferenceDayReservations = new ArrayList<>();
    static ArrayList<WorkshopReservation> workshopReservations = new ArrayList<>();
    static ArrayList<ConferenceDayRegistration> conferenceDayRegistrations = new ArrayList<>();
    static ArrayList<WorkshopRegistration> workshopRegistrations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File file = new File("generator.txt");
        if (file.isFile()) file.delete();
        file.createNewFile();

        PrintWriter printWriter = new PrintWriter(file.getName());
        Filler filler = new Filler();

        filler.setLocations(locations);
        filler.makeConferences(conferences, locations, conferenceDays);
        filler.makePrices(conferences, prices);
        filler.makeWorkshops(conferenceDays, workshops);
        filler.makeCustomers(locations, customers, privateCustomers, companies);
        filler.makeAttendees(customers, attendees);
        filler.makeReservations(customers, reservations, conferenceDayReservations, conferenceDays, workshops, workshopReservations);
        filler.makeCDREgistrations(conferenceDayReservations, reservations, customers, conferenceDayRegistrations);
        filler.makeWRegistrations(conferenceDayRegistrations, conferenceDayReservations, workshops, workshopRegistrations);

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getDate() == null) {
                reservations.remove(i);
                i--;
            }
        }
        makePayments();
        fillPayments();

        fillGenerator(printWriter);
    }

    private static void fillGenerator(PrintWriter printWriter) {
        // PRZEPISYWANIE
        for (Address element : locations) {
            element.generate(printWriter);
        }

        for (Conference element : conferences) {
            element.generate(printWriter);
        }

        for (Price element : prices) {
            element.generate(printWriter);
        }

        for (ConferenceDay element : conferenceDays) {
            element.generate(printWriter);
        }

        for (Workshop element : workshops) {
            element.generate(printWriter);
        }

        for (Customer element : customers) {
            element.generate(printWriter);
        }

        for (PrivateCustomer element : privateCustomers) {
            element.generate(printWriter);
        }

        for (Company element : companies) {
            element.generate(printWriter);
        }

        for (Attendee element : attendees) {
            element.generate(printWriter);
        }

        for (Payment element : payments) {
            element.generate(printWriter);
        }

        for (Reservation element : reservations) {
            element.generate(printWriter);
        }

        for (ConferenceDayReservation element : conferenceDayReservations) {
            if (element.getNumReservations() > 0) element.generate(printWriter);
        }

        for (WorkshopReservation element : workshopReservations) {
            if (element.getNumReservations() > 0) element.generate(printWriter);
        }

        for (ConferenceDayRegistration element : conferenceDayRegistrations) {
            element.generate(printWriter);
        }

        for (WorkshopRegistration element : workshopRegistrations) {
            element.generate(printWriter);
        }

        printWriter.close();
    }

    private static void makePayments() {
        for (int i = 0; i < conferenceDayReservations.size(); i++) {
            int reservationID = conferenceDayReservations.get(i).getReservationID();
            int conferenceDayID = conferenceDayReservations.get(i).getConferenceDayID();
            int conferenceID = conferenceDays.get(conferenceDayID - 1).getConferenceID();
            Date reserved = reservations.get(reservationID - 1).getDate();
            Date starts = conferences.get(conferenceID - 1).getStartTime();
            int howManyDays = starts.getDifference(reserved);
            float toPay = 0;
            //System.out.println(reservationID + " " + howManyDays);
            for (int j = 0; j < prices.size(); j++) {
                if (prices.get(j).getConferenceID() == conferenceID && prices.get(j).getDaysBeforeStart() >= howManyDays && prices.get(j).getDaysBeforeStart() - 30 < howManyDays) {
                    toPay += conferenceDayReservations.get(i).getNumReservations() * prices.get(j).getPrice();
                    reservations.get(reservationID - 1).increaseToBePaid(toPay);
                    //System.out.println(prices.get(j).getDaysBeforeStart() + " " + prices.get(j).getPrice());
                }
            }
        }

        for (int i = 0; i < workshopReservations.size(); i++) {
            int reservationID = workshopReservations.get(i).getReservationID();
            int workshopID = workshopReservations.get(i).getWorkshopID();
            float price = workshops.get(workshopID - 1).getPrice();
            float toPay = workshopReservations.get(i).getNumReservations() * price;
            reservations.get(reservationID - 1).increaseToBePaid(toPay);
        }
    }

    private static void fillPayments() {
        int ID = 1;
        for (int i = 0; i < reservations.size(); i++) {
            if (!reservations.get(i).isCancelled()) {
                // zapłacił, ale czy w terminie?
                float shouldBePaid = reservations.get(i).getTobePaid();
                Random generator = new Random();
                int helper = generator.nextInt(20);
                if (helper % 7 == 0) {
                    // zapłacił, ale nie w terminie
                    Date reserved = reservations.get(i).getDate();
                    //if (reserved == null) System.out.println("nullll");
                    int daysNum = 8 + generator.nextInt(7);
                    Date paid = reserved.add(daysNum);
                    float value = reservations.get(i).getTobePaid();
                    //System.out.println(value);
                    Payment payment = new Payment(paid, value, ID);
                    payments.add(payment);
                    reservations.get(i).setPayment(ID);
                    ID++;
                }
                else {
                    // zapłacił w terminie
                    Date reserved = reservations.get(i).getDate();
                    int daysNum = generator.nextInt(7);
                    Date paid = reserved.add(daysNum);
                    float value = reservations.get(i).getTobePaid();
                    //if (value == 0) System.out.println(value);
                    Payment payment = new Payment(paid, value, ID);
                    payments.add(payment);
                    reservations.get(i).setPayment(ID);
                    ID++;
                }
            }
            else {
                Random generator = new Random();
                int flag = generator.nextInt(2);
                // nie zapłacił i anulował
                // nie rób nic

                //zapłacił i anulował
                if (flag == 1) {
                    Date reserved = reservations.get(i).getDate();
                    int daysNum = generator.nextInt(7);
                    Date paid = reserved.add(daysNum);
                    float value = (float) (1300 + generator.nextInt(3000) * 0.9888872);
                    Payment payment = new Payment(paid, value, ID);
                    payments.add(payment);
                    reservations.get(i).setPayment(ID);
                    ID++;
                }
            }
        }
    }
}
