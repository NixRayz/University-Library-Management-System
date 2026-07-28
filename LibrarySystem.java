import java.util.Scanner;
import java.util.List;

public class LibrarySystem {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        
        int initialCount = 36;
        generateInitialData(library, initialCount);

        boolean exit = false;
        System.out.println("==================================================");
        System.out.println("  سیستم مدیریت کتابخانه دانشگاهی");
        System.out.println("  دانشجو: سعید مرادی | شماره دانشجویی: 02111085302036");
        System.out.println("==================================================");

        while (!exit) {
            System.out.println("\n--- 📚 منوی اصلی ---");
            System.out.println("1. مشاهده کتاب‌های موجود");
            System.out.println("2. جستجوی کتاب با عنوان");
            System.out.println("3. جستجوی کتاب با نویسنده");
            System.out.println("4. جستجوی کتاب با دسته‌بندی (ویژه رقم ۶)");
            System.out.println("5. امانت گرفتن کتاب");
            System.out.println("6. بازگرداندن کتاب");
            System.out.println("7. افزودن کتاب جدید");
            System.out.println("8. افزودن کاربر جدید");
            System.out.println("9. لیست کامل کاربران");
            System.out.println("10. مرتب‌سازی الفبایی کتاب‌ها");
            System.out.println("0. خروج");
            System.out.print("لطفاً یک گزینه را انتخاب کنید: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("\n--- 📖 کتاب‌های موجود ---");
                        for (Book b : library.getAvailableBooks()) {
                            System.out.println(b);
                        }
                        break;
                    case 2:
                        System.out.print("عنوان مورد نظر: ");
                        String titleQuery = scanner.nextLine();
                        List<Book> titleResults = library.searchByTitle(titleQuery);
                        titleResults.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.print("نام نویسنده: ");
                        String authorQuery = scanner.nextLine();
                        List<Book> authorResults = library.searchByAuthor(authorQuery);
                        authorResults.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.print("دسته‌بندی مورد نظر (علمی، رمان، درسی و...): ");
                        String catQuery = scanner.nextLine();
                        List<Book> catResults = library.searchByCategory(catQuery);
                        catResults.forEach(System.out::println);
                        break;
                    case 5:
                        System.out.print("شناسه کاربر (مثلا U1): ");
                        String uId = scanner.nextLine();
                        System.out.print("شناسه کتاب (مثلا B1): ");
                        String bId = scanner.nextLine();
                        library.borrowBook(uId, bId);
                        break;
                    case 6:
                        System.out.print("شناسه کاربر: ");
                        String rUId = scanner.nextLine();
                        System.out.print("شناسه کتاب: ");
                        String rBId = scanner.nextLine();
                        library.returnBook(rUId, rBId);
                        break;
                    case 7:
                        System.out.print("شناسه کتاب: ");
                        String newId = scanner.nextLine();
                        System.out.print("عنوان: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("نویسنده: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("دسته‌بندی (علمی/رمان/درسی...): ");
                        String newCat = scanner.nextLine();
                        System.out.print("شابک: ");
                        String newIsbn = scanner.nextLine();
                        System.out.print("سال انتشار: ");
                        int newYear = Integer.parseInt(scanner.nextLine());
                        library.addBook(new Book(newId, newTitle, newAuthor, newIsbn, newYear, newCat));
                        System.out.println("✅ کتاب با موفقیت ثبت شد.");
                        break;
                    case 8:
                        System.out.print("نوع کاربر (1. دانشجو / 2. استاد): ");
                        int userType = Integer.parseInt(scanner.nextLine());
                        System.out.print("شناسه: ");
                        String id = scanner.nextLine();
                        System.out.print("نام: ");
                        String name = scanner.nextLine();
                        System.out.print("ایمیل: ");
                        String email = scanner.nextLine();

                        if (userType == 1) {
                            System.out.print("شماره دانشجویی: ");
                            String sId = scanner.nextLine();
                            System.out.print("رشته تحصیلی: ");
                            String major = scanner.nextLine();
                            library.addUser(new Student(id, name, email, sId, major));
                        } else {
                            System.out.print("کد پرسنلی: ");
                            String eId = scanner.nextLine();
                            System.out.print("دانشکده: ");
                            String dept = scanner.nextLine();
                            library.addUser(new Professor(id, name, email, eId, dept));
                        }
                        System.out.println("✅ کاربر با موفقیت ثبت شد.");
                        break;
                    case 9:
                        System.out.println("\n--- 👥 لیست کاربران ---");
                        for (Person p : library.getUsers()) {
                            System.out.println(p);
                        }
                        break;
                    case 10:
                        library.sortBooksByTitle();
                        System.out.println("✅ کتاب‌ها بر اساس عنوان مرتب‌سازی شدند.");
                        break;
                    case 0:
                        exit = true;
                        System.out.println("با تشکر، برنامه بسته شد.");
                        break;
                    default:
                        System.out.println("⚠️ گزینه نامعتبر است!");
                }
            } catch (Exception e) {
                System.out.println("⚠️ خطایی رخ داد: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void generateInitialData(Library library, int count) {
        String[] categories = {"علمی", "رمان", "درسی", "تاریخی", "مهندسی"};
        for (int i = 1; i <= count; i++) {
            String cat = categories[i % categories.length];
            library.addBook(new Book("B" + i, "کتاب نمونه " + i, "نویسنده " + i, "ISBN-" + (1000 + i), 2020 + (i % 5), cat));
            
            if (i % 2 == 0) {
                library.addUser(new Student("U" + i, "دانشجو " + i, "student" + i + "@univ.ac.ir", "ST" + (1000 + i), "مهندسی نرم‌افزار"));
            } else {
                library.addUser(new Professor("U" + i, "استاد " + i, "prof" + i + "@univ.ac.ir", "PR" + (1000 + i), "دانشکده کامپیوتر"));
            }
        }
    }
}
