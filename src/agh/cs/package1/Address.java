package agh.cs.package1;

import java.io.PrintWriter;

public class Address {
    private String street;
    private String city;
    private String ZIP;
    private String country;
    private boolean isTaken;

    public Address(int ID, Filler nameSetter) {
        this.street = nameSetter.getStreet();
        this.city = nameSetter.getCity();
        this.country = nameSetter.getCountry();
        this.ZIP = nameSetter.getZIP();
        isTaken = false;
    }

    public void take() {
        this.isTaken = true;
    }

    boolean ifTaken() {
        return isTaken;
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT Address (Street, City, ZIP, Country) VALUES ('" + this.street + "','" + this.city + "'," + this.ZIP + ",'" + this.country + "');");
    }

}
