package JavaAssignment_Sean;

public class Student {
    // instance variables
    private String name;
    private String email;
    private String phoneNumber;
    private int admissionYear;
    private String program;
    private String level;
    private String nationality;
    private String roomNumber;

    // constructor(s)
    public Student(String name, String email, String phoneNumber, int admissionYear, String program, String level, String nationality) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.admissionYear = admissionYear;
        this.program = program;
        this.level = level;
        this.nationality = nationality;
        this.roomNumber = null;
    }

    // getters and setters
    // ...
}