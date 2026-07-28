public class Book implements Comparable<Book> {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private int year;
    private boolean isBorrowed;
    private BookStatus status;
    private String category; // اضافه شده بر اساس رقم آخر شماره دانشجویی (۶: دسته‌بندی کتاب‌ها)

    public Book(String id, String title, String author, String isbn, int year, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.category = category;
        this.isBorrowed = false;
        this.status = BookStatus.AVAILABLE;
    }

    public boolean borrowBook() {
        if (!isBorrowed && status == BookStatus.AVAILABLE) {
            this.isBorrowed = true;
            this.status = BookStatus.BORROWED;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (isBorrowed) {
            this.isBorrowed = false;
            this.status = BookStatus.AVAILABLE;
            return true;
        }
        return false;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean borrowed) { this.isBorrowed = borrowed; }

    public BookStatus getStatus() { return status; }
    public void setStatus(BookStatus status) { this.status = status; }

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return String.format("کتاب [شناسه: %s | عنوان: %s | نویسنده: %s | دسته: %s | شابک: %s | سال: %d | وضعیت: %s]",
                id, title, author, category, isbn, year, (isBorrowed ? "امانت داده شده" : "موجود"));
    }
}
