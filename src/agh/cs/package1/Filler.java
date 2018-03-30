package agh.cs.package1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Filler {
    protected ArrayList<String> workshopNames1 = new ArrayList<>();
    protected ArrayList<String> workshopNames2 = new ArrayList<>();
    protected  ArrayList<String> firstNames = new ArrayList<>();
    protected ArrayList<String> lastNames = new ArrayList<>();
    protected  ArrayList<String> companyNames = new ArrayList<>();
    protected ArrayList<String> cities = new ArrayList<>();
    protected ArrayList<String> countries = new ArrayList<>();
    protected String[] streets1 = {"North ", "", "West ", "South ", "St ", "", "", "", "", "", "", ""};
    protected String[] streets2 = {"Green ", "White ", "Blue ", "Red ", "", "", "", "", "", ""};
    protected String[] streets3 = {"Nobel ", "Fabien ", "Hague ", "Oak ", "Second ", "First ", "Cowley ", "Clarendon ", "New ", "Old ", "Milton "};
    protected String[] streets4 = {"Way ", "Street ", "St. ", "Avenue ", "Road ", "Parkway ", "Freeway ", "Drive ", "Boulevard ", "Blvd. "};

    public Filler() throws FileNotFoundException {
        File file = new File("names1.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            workshopNames1.add(scanner.nextLine());
        }
        scanner.close();

        file = new File("names2.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            workshopNames2.add(scanner.nextLine());
        }
        scanner.close();

        file = new File("firstnames.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            firstNames.add(scanner.nextLine());
        }
        scanner.close();

        file = new File("lastnames.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            lastNames.add(scanner.nextLine());
        }
        scanner.close();

        file = new File("companynames.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            companyNames.add(scanner.nextLine());
        }
        scanner.close();

        file = new File("cities.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            cities.add(scanner.nextLine());
        }
        scanner.close();

        file = new File("countries.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            countries.add(scanner.nextLine());
        }
        scanner.close();
    }

    public String getName() {
        Random generator = new Random();
        int pos = generator.nextInt(workshopNames1.size());
        StringBuilder stringBuilder = new StringBuilder(workshopNames1.get(pos));
        stringBuilder.append(" ");
        pos = generator.nextInt(workshopNames2.size());
        stringBuilder.append(workshopNames2.get(pos));
        return stringBuilder.toString();
    }

    public String getFirstName() {
        Random generator = new Random();
        int pos = generator.nextInt(firstNames.size());
        return firstNames.get(pos);
    }

    public String getLastName() {
        Random generator = new Random();
        int pos = generator.nextInt(lastNames.size());
        return lastNames.get(pos);
    }

    public String getCompanyName() {
        Random generator = new Random();
        int pos = generator.nextInt(companyNames.size());
        return companyNames.get(pos);
    }

    public String getBuildingName() {
        Random generator = new Random();
        char building = (char) (65 + generator.nextInt(26));
        int room = generator.nextInt(200);
        StringBuilder stringBuilder = new StringBuilder("Building ");
        stringBuilder.append(building + ", " + "room " + room);
        return stringBuilder.toString();
    }

    public String getCity() {
        Random generator = new Random();
        int pos = generator.nextInt(cities.size());
        return cities.get(pos);
    }

    public String getCountry() {
        Random generator = new Random();
        int pos = generator.nextInt(countries.size());
        return countries.get(pos);
    }

    public String getStreet() {
        Random generator = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(streets1[generator.nextInt(streets1.length)]);
        stringBuilder.append(streets2[generator.nextInt(streets2.length)]);
        stringBuilder.append(streets3[generator.nextInt(streets3.length)]);
        stringBuilder.append(1 + generator.nextInt(99));
        return stringBuilder.toString();
    }

    public String getZIP() {
        Random generator = new Random();
        String ZIP = String.valueOf(1 + generator.nextInt(9));
        StringBuilder zipBuilder = new StringBuilder(ZIP);
        for (int i = 0; i < 5; i++) {
            zipBuilder.append(String.valueOf(generator.nextInt(10)));
        }
        ZIP = zipBuilder.toString();
        return ZIP;
    }

    public String getNIP() {
        Random generator = new Random();
        String NIP = String.valueOf(1 + generator.nextInt(9));
        StringBuilder nipBuilder = new StringBuilder(NIP);
        for (int i = 0; i < 9; i++) {
            nipBuilder.append(String.valueOf(generator.nextInt(10)));
        }
        NIP = nipBuilder.toString();
        return NIP;
    }

    public String getNumber() {
        Random generator = new Random();
        String number = String.valueOf(5 + generator.nextInt(5));
        StringBuilder numberBuilder = new StringBuilder(number);
        for (int i = 1; i < 9; i++) {
            if (i % 3 == 0) numberBuilder.append("-");
            numberBuilder.append(String.valueOf(generator.nextInt(10)));
        }
        number = numberBuilder.toString();
        return number;
    }

    public void setLocations(ArrayList<Address> locations) {
        for (int i = 0; i < 800; i++) {
            locations.add(new Address(i+1, this));
        }
    }

    public void makeConferences(ArrayList<Conference> conferences, ArrayList<Address> locations, ArrayList<ConferenceDay> conferenceDays) {
        int numConfDays = 1;
        for (int i = 0; i < 72; i++) {
            conferences.add(new Conference(this, locations));
            for (int j = 0; j < conferences.get(i).getNumDays(); j++) {
                conferenceDays.add(new ConferenceDay(j+1, i+1, conferences.get(i).getStartTime(), numConfDays));
                numConfDays++;
            }
        }
    }

    public void makePrices(ArrayList<Conference> conferences, ArrayList<Price> prices) {
        for (int i = 0; i < conferences.size(); i++) {
            float price = 100;
            for (int j = 180; j > 0; j-= 30) {
                prices.add(new Price(i+1, j, (float)(price * 0.99876)));
                price = prices.get(prices.size()-1).getPrice();
            }
        }
    }

    public void makeWorkshops(ArrayList<ConferenceDay> conferenceDays, ArrayList<Workshop> workshops) {
        for (int i = 0; i < conferenceDays.size(); i++) {
            for (int j = 0; j < 4; j++) {
                workshops.add(new Workshop(i+1, this, conferenceDays.get(i).getDate()));
            }
        }
    }

    public void makeCustomers(ArrayList<Address> locations, ArrayList<Customer> customers, ArrayList<PrivateCustomer> privateCustomers, ArrayList<Company> companies) {
        for (int i = 0; i < 700; i++) {
            Random generator = new Random();
            int pos = generator.nextInt(locations.size());
            while (locations.get(pos).ifTaken()) pos = generator.nextInt(locations.size());
            customers.add(new Customer(pos+1, this));
        }
        for (int i = 0; i < 400; i++) {
            privateCustomers.add(new PrivateCustomer(this, i+1, customers));
        }

        for (int i = 0; i < 300; i++) {
            companies.add(new Company(400 + i + 1, this, customers));

        }
    }

    public void makeAttendees(ArrayList<Customer> customers, ArrayList<Attendee> attendees) {
        int id = 1;
        for (int i = 0; i < customers.size(); i++) {
            Random generator = new Random();
            int howMany = 1 + generator.nextInt(15);
            for (int j = 0; j < howMany; j++) {
                Attendee newAttendee = new Attendee(this, id, attendees);
                newAttendee.setWorksForID(i+1, customers);
                attendees.add(newAttendee);
                id++;
                int flag = generator.nextInt(10);
                if (flag == 0) newAttendee.setSC();
            }
            int flag = generator.nextInt(10);
            Attendee newAttendee = new Attendee(this, id, attendees);
            newAttendee.setWorksForID(i+1, customers);
            if (flag % 3 == 0) {
                attendees.add(newAttendee);
                id++;
            }
            flag = generator.nextInt(10);
            if (flag == 7 || flag == 3) newAttendee.setSC();
        }
    }

    public void makeReservations(ArrayList<Customer> customers, ArrayList<Reservation> reservations,
                                 ArrayList<ConferenceDayReservation> conferenceDayReservations,
                                 ArrayList<ConferenceDay> conferenceDays, ArrayList<Workshop> workshops,
                                 ArrayList<WorkshopReservation> workshopReservations) {

        for (int i = 0; i < 10000; i++) {
            Random generator = new Random();
            int customerID = 1 + generator.nextInt(customers.size());
            reservations.add(new Reservation(customerID));
        }

        for (int i = 0; i < 1000; i++) {
            Random generator = new Random();
            int confDay = generator.nextInt(conferenceDays.size());
            conferenceDayReservations.add(new ConferenceDayReservation(i+1, confDay, conferenceDayReservations, conferenceDays.get(confDay), reservations));
            reservations.get(i).setConferenceDayDate(conferenceDays.get(confDay).getDate());
            reservations.get(i).setDate();
            if (i < 2749 && confDay + 1 < conferenceDays.size() && conferenceDays.get(confDay + 1).getConferenceID() == conferenceDays.get(confDay).getConferenceID()) {
                conferenceDayReservations.add(new ConferenceDayReservation(i + 2, confDay + 1, conferenceDayReservations, conferenceDays.get(confDay + 1), reservations));
                reservations.get(i + 1).setConferenceDayDate(conferenceDays.get(confDay + 1).getDate());
                reservations.get(i + 1).setDate();
                //i++;
                int flag = generator.nextInt(10);
                if (flag % 3 == 0 && i < 2749 && confDay + 2 < conferenceDays.size() && conferenceDays.get(confDay + 2).getConferenceID() == conferenceDays.get(confDay).getConferenceID()) {
                    conferenceDayReservations.add(new ConferenceDayReservation(i + 3, confDay + 2, conferenceDayReservations, conferenceDays.get(confDay + 2), reservations));
                    reservations.get(i + 2).setConferenceDayDate(conferenceDays.get(confDay + 2).getDate());
                    reservations.get(i + 2).setDate();
                    i += 2;
                }
                else i++;
            }
        }

        int counter = 1;
        for (int i = 0; i < conferenceDayReservations.size(); i++) {
            int conferenceDayID = conferenceDayReservations.get(i).getConferenceDayID() - 1;
            int reservationID = conferenceDayReservations.get(i).getReservationID();
            if (reservations.get(reservationID - 1).isCancelled()) {
                Random generator = new Random();
                int day = 1 + generator.nextInt(31);
                int month = 1 + generator.nextInt(12);
                int year = 2015 + generator.nextInt(4);
                int hour = 0 + generator.nextInt(24);
                int minute = 0 + generator.nextInt(60);
                Date date = new Date(day, month, year, hour, minute);
                reservations.get(reservationID - 1).setDate(day, month, year, hour, minute);
                continue;
            }
            int maxRes = conferenceDayReservations.get(i).getNumReservations();
            if (maxRes < 1) maxRes = 1;
            for (int j = 0; j < workshops.size(); j++) {
                Random generator = new Random();
                int flag = generator.nextInt(10);
                if (workshops.get(j).getConferenceDayID() == conferenceDayID && flag % 3 == 0) {
                    Workshop workshop = workshops.get(j);
                    WorkshopReservation workshopReservation = new WorkshopReservation(1000 + counter, j+1, maxRes, workshopReservations, workshop.getParticipantsLimit(), reservations);
                    workshopReservations.add(workshopReservation);
                    workshopReservation.setID(workshopReservations);
                    conferenceDayReservations.get(i).addWorkshopReservation(workshopReservation);
                    int index = 1000 + counter - 1;
                    reservations.get(index).setConferenceDayDate(conferenceDays.get(workshop.getConferenceDayID()-1).getDate());
                    reservations.get(index).setDate();
                    counter++;
                }
            }

        }
    }

    public void makeCDREgistrations(ArrayList<ConferenceDayReservation> conferenceDayReservations,
                                    ArrayList<Reservation> reservations, ArrayList<Customer> customers,
                                    ArrayList<ConferenceDayRegistration> conferenceDayRegistrations) {
        Random generator = new Random();
        for (int i = 0; i < conferenceDayReservations.size(); i++) {
            ConferenceDayReservation conferenceDayReservation = conferenceDayReservations.get(i);
            int conferenceDayID = conferenceDayReservation.getConferenceDayID();
            int reservationID = conferenceDayReservation.getReservationID();
            if (!reservations.get(reservationID - 1).isCancelled()) {
                // zapisujemy
                for (int j = 0; j < conferenceDayReservation.getNumReservations(); j++) {
                    int helper = generator.nextInt(12);
                    if (helper != 7) { // helper == 7 => miejsce nie zostało wykorzystane
                        int customerID = reservations.get(reservationID - 1).getCustomerID();
                        ArrayList<Attendee> customerEmployees = customers.get(customerID - 1).getEmployees();
                        int number = 0;
                        while (number < customerEmployees.size() && number < conferenceDayReservation.getNumReservations()) {
                            helper = generator.nextInt(15);
                            if (helper != 13 && !customerEmployees.get(number).checkParticipation(conferenceDayID)) { // helper == 13 => pracownik nie zarejestrował sobie miejsca na dniu
                                ConferenceDayRegistration cdr = new ConferenceDayRegistration(i+1, customerEmployees.get(number).getAttendeeID());
                                cdr.addAttendee(customerEmployees.get(number));
                                conferenceDayRegistrations.add(cdr);
                                customerEmployees.get(number).addParticipation(conferenceDayID);
                            }
                            number++;
                        }

                    }
                }
            }
        }
    }

    public void makeWRegistrations(ArrayList<ConferenceDayRegistration> conferenceDayRegistrations,
                                   ArrayList<ConferenceDayReservation> conferenceDayReservations,
                                   ArrayList<Workshop> workshops, ArrayList<WorkshopRegistration> workshopRegistrations) {
        for (int i = 0; i < conferenceDayRegistrations.size(); i++) {
            ConferenceDayRegistration cdr = conferenceDayRegistrations.get(i);
            ArrayList<Attendee> cdrAttendees = cdr.getAttendees(); // tylko oni mogą zarejestrować się na warsztaty

            for (int c = 0; c < cdrAttendees.size(); c++) {
                cdrAttendees.get(c).resetTakings();
            }

            ConferenceDayReservation conferenceDayReservation = conferenceDayReservations.get(cdr.getConferenceDayReservationID() - 1);
            ArrayList<WorkshopReservation> workshopRes = conferenceDayReservation.getWorkshopReservations();

            for (int j = 0; j < workshopRes.size(); j++) {
                int number = 0;
                Random generator = new Random();
                int helper = generator.nextInt(20);
                if (helper != 18) { // helper == 18 => nie wykorzystano rezerwacji
                    while (number < cdrAttendees.size() && number < workshopRes.get(j).getNumReservations()) {
                        // rejestrujemy uczestnika
                        Attendee attendee = cdrAttendees.get(number);
                        int workshopID = workshopRes.get(j).getWorkshopID();
                        Date workshopStartTime = workshops.get(workshopID - 1).getStartTime();
                        Date workshopEndTime = workshops.get(workshopID - 1).getEndTime();
                        if (attendee.canParticipate(workshopStartTime, workshopEndTime)) {
                            workshopRegistrations.add(new WorkshopRegistration(cdr.getConferenceDayReservationID(), workshopRes.get(j).getWorkshopReservationID(), attendee.getAttendeeID()));

                        }
                        number++;
                    }
                }
            }
        }
    }
}
