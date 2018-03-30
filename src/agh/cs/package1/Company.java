package agh.cs.package1;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Company {
    private String companyName;
    private String contactName;
    int customerID;
    String NIP;

    public Company(int customerID, Filler nameSetter, ArrayList<Customer> customers) {
        this.customerID = customerID;
        this.NIP = nameSetter.getNIP();
        this.companyName = nameSetter.getCompanyName();
        StringBuilder stringBuilder = new StringBuilder(nameSetter.getFirstName());
        stringBuilder.append(" " + nameSetter.getLastName());
        this.contactName = stringBuilder.toString();
        String nick = companyName.replaceAll(" ", "").substring(0, 5).toLowerCase();
        customers.get(customerID - 1).setEmail(nick);

    }

    public void generate (PrintWriter printWriter) {
        printWriter.println("INSERT Companies (CustomerID, CompanyName, ContactName, NIP) VALUES (" + this.customerID + ",'" + this.companyName + "','" + this.contactName + "','" + this.NIP +  "');");
    }
}
