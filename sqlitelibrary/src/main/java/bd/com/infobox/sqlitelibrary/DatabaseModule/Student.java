package bd.com.infobox.sqlitelibrary.DatabaseModule;

public class Student {

    private int id;
    private String name, dept, gender, year;

    public Student(int id, String name, String dept) {
        this(name, dept);
        this.id = id;
    }

    public Student(int id, String name, String dept, String gender) {
        this(name, dept);
        this.gender = gender;
        this.id = id;
    }

    public Student(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }

    public Student(int id, String name, String dept, String gender, String year) {
        this(id, name, dept);
        this.gender = gender;
        this.year = year;
    }

    public Student(String name, String dept, String gender, String year) {
        this(name, dept);
        this.gender = gender;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getGender() {
        return gender;
    }

    public String getYear() {
        return year;
    }
}
