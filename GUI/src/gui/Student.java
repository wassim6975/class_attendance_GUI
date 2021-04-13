package gui;

public class Student {
    private String ID;
    private String lastName;
    private String firstName;
    private String Date;
    private String Hours;


    public Student(String ID, String lastName, String firstName, String Date, String Hours) {
        this.ID = ID;
        this.lastName =lastName;
        this.firstName =firstName;
        this.Date =Date;
        this.Hours =Hours;
    }

    public String getID() {
        return ID;
    }

    public void seID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String fDate) {
        this.Date = fDate;
    }

    public String getHours() {
        return Hours;
    }

    public void setHours(String fHours) {
        this.Hours = fHours;
    }

}
