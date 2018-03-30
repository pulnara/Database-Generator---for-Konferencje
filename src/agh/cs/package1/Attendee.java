package agh.cs.package1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Attendee {
    int AttendeeID;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String studentCard;
    private Integer worksForID;
    private ArrayList<Integer> participatesDays = new ArrayList<>();
    private ArrayList<Date> takenFrom = new ArrayList<>();
    private ArrayList<Date> takenTo = new ArrayList<>();

    public Attendee(Filler nameSetter, int ID, ArrayList<Attendee> attendees) {
        this.firstname = nameSetter.getFirstName();
        this.lastname = nameSetter.getLastName();

        StringBuilder stringBuilder = new StringBuilder();
        int counter = 3;
        if (this.firstname.length() < 3) counter = this.firstname.length();
        stringBuilder.append(this.firstname.substring(0, counter).toLowerCase());
        if (this.lastname.length() < 3) counter = this.lastname.length();
        else counter = 3;
        stringBuilder.append(this.lastname.substring(0, counter).toLowerCase());
        String nick = stringBuilder.toString();
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).email.contains(nick)) {
                Random generator = new Random();
                int charNum = 97 + generator.nextInt(25);
                stringBuilder.append((char)charNum);
            }
        }
        stringBuilder.append("@email.com");
        this.email = stringBuilder.toString();

        this.phone = nameSetter.getNumber();
        this.AttendeeID = ID;
    }

    public void setWorksForID(int worksForID, ArrayList<Customer> customers) {
        this.worksForID = worksForID;
        customers.get(worksForID - 1).addAttendee(this);
    }

    public void resetTakings() {
        this.takenTo = new ArrayList<>();
        this.takenFrom = new ArrayList<>();
    }

    public void addParticipation (int conferenceDayID) {
        this.participatesDays.add(conferenceDayID);
    }

    public boolean checkParticipation (int conferenceDayID) {
        for (Integer element : participatesDays) {
            if (element == conferenceDayID) return true;
        }
        return false;
    }

    public void setSC() {
        StringBuilder stringBuilder = new StringBuilder();
        Random generator = new Random();
        stringBuilder.append(toString().valueOf(1 + generator.nextInt(9)));
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(toString().valueOf(generator.nextInt(10)));
        }
        this.studentCard = stringBuilder.toString();
    }

    public void generate (PrintWriter printWriter) {
        StringBuilder stringBuilder = new StringBuilder("INSERT Attendees (Firstname, Lastname, Email, Phone");
        StringBuilder arguments = new StringBuilder(this.firstname + "','" + this.lastname + "','" + this.email + "','" + this.phone + "'");

        if (this.worksForID != null) {
            stringBuilder.append(", WorksForID");
            arguments.append("," + this.worksForID);
        }

        if (this.studentCard != null){
            stringBuilder.append(", StudentCard");
            arguments.append(",'" + this.studentCard + "'");
        }
        stringBuilder.append(") VALUES ('");
        arguments.append(");");
        stringBuilder.append(arguments.toString());
        printWriter.println(stringBuilder.toString());
    }

    public boolean canParticipate (Date startTime, Date endTime) {
        if (this.takenFrom == null) {
            this.takenFrom.add(startTime);
            this.takenTo.add(endTime);
            return true;
        }

        for (int i = 0; i < takenFrom.size(); i++) {
            if (!startTime.beforeAndCanBe(takenFrom.get(i)) && !takenTo.get(i).beforeAndCanBe(startTime)) return false;
        }

        this.takenFrom.add(startTime);
        this.takenTo.add(endTime);
        return true;
    }

    public int getAttendeeID() {
        return this.AttendeeID;
    }
}
