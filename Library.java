import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Person> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(Person person) {
        users.add(person);
    }

    public Person findUserById(String userId) {
        for (Person u : users) {
            if (u.getId().equalsIgnoreCase(userId)) return u;
        }
        return null;
    }

    public Book findBookById(String bookId) {
        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(bookId)) return b;
        }
        return null;
    }

    public boolean borrowBook(String userId, String bookId) {
        Person user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user == null || book == null) {
            System.out.println("❌ کاربر یا کتاب یافت نشد!");
            return false;
        }

        List<Book> userBorrowedList = (user instanceof Student) ? 
                ((Student) user).getBorrowedBooks() : ((Professor) user).getBorrowedBooks();

        if (userBorrowedList.size() >= user.borrowLimit()) {
            System.out.println("❌ سقف امانت کتاب برای این کاربر (" + user.borrowLimit() + " جلد) به پایان رسیده است!");
            return false;
        }

        if (book.borrowBook()) {
            userBorrowedList.add(book);
            System.out.println("✅ کتاب [" + book.getTitle() + "] با موفقیت به " + user.getName() + " امانت داده شد.");
            return true;
        } else {
            System.out.println("❌ این کتاب در حال حاضر در دسترس نیست.");
            return false;
        }
    }

    public boolean returnBook(String userId, String bookId) {
        Person user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user == null || book == null) {
            System.out.println("❌ اطلاعات کاربر یا کتاب اشتباه است!");
            return false;
        }

        List<Book> userBorrowedList = (user instanceof Student) ? 
                ((Student) user).getBorrowedBooks() : ((Professor) user).getBorrowedBooks();

        if (userBorrowedList.contains(book)) {
            book.returnBook();
            userBorrowedList.remove(book);
            System.out.println("✅ کتاب با موفقیت بازگردانده شد.");
            return true;
        } else {
            System.out.println("❌ این کتاب در لیست امانتی‌های این کاربر وجود ندارد.");
            return false;
        }
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> searchByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getCategory().toLowerCase().contains(category.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book b : books) {
            if (!b.isBorrowed()) {
                available.add(b);
            }
        }
        return available;
    }

    public void sortBooksByTitle() {
        Collections.sort(books);
    }

    public List<Book> getBooks() { return books; }
    public List<Person> getUsers() { return users; }
}
