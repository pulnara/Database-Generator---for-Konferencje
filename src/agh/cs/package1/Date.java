package agh.cs.package1;

public class Date {
    int day;
    int month;
    int year;
    int hour;
    int minute;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.check(this);
    }

    public Date(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.check(this);
    }

    public String toString() {
        return (this.year + "-" + this.month + "-" + this.day);
    }

    public String toStringWithTime() {
        return ("'" + this.year + "-" + this.month + "-" + this.day + " " + this.hour + ":" + this.minute + ":00'");
    }

    public Date check(Date prevDate) {
        switch (prevDate.month) {
            case 2:
                if ((((prevDate.year % 4 == 0 && prevDate.year % 100 != 0) || prevDate.year % 400 == 0)) && prevDate.day > 29) {
                    prevDate.day = prevDate.day % 29;
                    prevDate.month++;
                }
                else if (prevDate.day > 28) {
                    prevDate.day = prevDate.day % 28;
                    prevDate.month++;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (prevDate.day > 30) {
                    prevDate.day = prevDate.day % 30;
                    prevDate.month++;
                }
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (prevDate.day > 31) {
                    prevDate.day = prevDate.day % 31;
                    if (prevDate.month == 12) {
                        prevDate.year++;
                        prevDate.month = 1;
                    }
                    else prevDate.month++;
                }
                break;
        }

        return prevDate;
    }

    public Date add(int number) {
        if (number == 0) return this;
        int day  = this.day + number - 1;
        Date newDay = new Date(day, this.month, this.year);
        return check(newDay);
    }

    public int getDifference(Date before) {
        int difference = 0;
        if (before.year + 2 == this.year) difference = 365;
        else if (this.year == before.year) {
            int month1 = before.month;
            int month2 = this.month;
            if (month1 == month2) return this.day - before.day + 1;
            else if (month1 < month2) {
                difference += before.getMonthDifference(this);
            }
            else return -1;
        }
        else if (this.year == before.year + 1) {
            Date endYear = new Date(31, 12, before.year);
            difference = before.getMonthDifference(endYear);
            Date beginYear = new Date(1, 1, this.year);
            difference += beginYear.getMonthDifference(this);
        }
        else return -1;
        return difference;
    }

    public int getMonthDifference(Date after) {
        if (this.month == after.month) return after.getDifference(this);
        int days = 0;
        int month1 = this.month;
        int month2 = after.month;
        switch (month1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31 - this.day + 1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30 - this.day + 1;
                break;
            case 2:
                if ((this.year % 4 == 0 && this.year % 100 != 0) || this.year % 400 == 0) days = 29 - this.day + 1;
                else days  = 28 - this.day + 1;
                break;
        }
        month1++;
        while (month1 != month2) {
            switch (month1) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days += 30;
                    break;
                case 2:
                    if ((this.year % 4 == 0 && this.year % 100 != 0) || this.year % 400 == 0) days += 29;
                    else days += 28;
                    break;
            }
            month1++;
        }
        days += after.day;
        return days;
    }

    public boolean beforeAndCanBe (Date date) {
        int hourDifference = date.hour - this.hour;
        if (hourDifference > 2) return true;
        if (hourDifference < 2) return false;

        // hourDifference == 2:
        int minuteDifference = date.minute - this.minute;
        if (minuteDifference < 0) return false;
        return true;
    }
}
