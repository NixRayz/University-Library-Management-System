import java.util.ArrayList;
import java.util.List;

public class Professor extends Person {
    private String employeeId;
    private String department;
    private List<Book> borrowedBooks;

    public Professor(String id, String name, String email, String employeeId, String department) {
        super(id, name, email);
        this.employeeId = employeeId;
        this.department = department;
        this.borrowedBooks = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "استاد";
    }

    @Override
    public int borrowLimit() {
        return 5;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }
}
