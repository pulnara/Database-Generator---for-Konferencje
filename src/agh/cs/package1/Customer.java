package agh.cs.package1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Customer {
    private int addressID;
    private String email;
    private String number;
    ArrayList<Attendee> attendees = new ArrayList<>();

    public Customer(int addressID, Filler nameSetter) {
        this.number = nameSetter.getNumber();
        this.addressID = addressID;
    }

    public ArrayList<Attendee> getEmployees() {
        return this.attendees;
    }

    public void setEmail(String nick) {
        StringBuilder stringBuilder = new StringBuilder(nick);
        Random generator = new Random();
        int charNum = 97 + generator.nextInt(25);
        stringBuilder.append((char)charNum);
        charNum = 97 + generator.nextInt(25);
        stringBuilder.append((char)charNum);
        stringBuilder.append("@email.com");
        this.email = stringBuilder.toString();
    }

    public void addAttendee(Attendee attendee) {
        this.attendees.add(attendee);
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT Customers (AddressID, Phone, Email) VALUES (" + this.addressID + ",'" + this.number + "','" + email + "');");
    }
}
