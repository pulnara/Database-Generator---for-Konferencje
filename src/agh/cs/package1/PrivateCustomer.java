package agh.cs.package1;

import java.io.PrintWriter;
import java.util.ArrayList;

public class PrivateCustomer {
    private String name;
    private String surname;
    int customerID;

    public PrivateCustomer(Filler nameSetter, int customerID, ArrayList<Customer> customers) {
        this.customerID = customerID;
        this.name = nameSetter.getFirstName();
        this.surname = nameSetter.getLastName();
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 3;
        if (name.length() < 3) counter = name.length();
        stringBuilder.append(name.substring(0, counter).toLowerCase());
        if (surname.length() < 3) counter = surname.length();
        else counter = 3;
        stringBuilder.append(surname.substring(0, counter).toLowerCase());
        customers.get(customerID - 1).setEmail(stringBuilder.toString());
    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT PrivateCustomers (CustomerID, Firstname, Lastname) VALUES (" + this.customerID + ",'" + this.name + "','" + this.surname + "');");
    }
}
