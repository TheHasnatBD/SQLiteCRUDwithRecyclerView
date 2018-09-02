package bd.com.infobox.sqlitelibrary.DatabaseModule;

public class Student {

    private int id;
    private String name, dept;

    public Student(int id, String name, String dept) {
        this(name, dept);
        this.id = id;
    }

    public Student(String name, String dept) {
        this.name = name;
        this.dept = dept;
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
}
