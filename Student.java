import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String studentId;
    private String major;
    private List<Book> borrowedBooks;

    public Student(String id, String name, String email, String studentId, String major) {
        super(id, name, email);
        this.studentId = studentId;
        this.major = major;
        this.borrowedBooks = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "دانشجو";
    }

    @Override
    public int borrowLimit() {
        return 3;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }
}
