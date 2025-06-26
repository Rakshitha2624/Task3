package task3;

import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + author + (isIssued ? " [Currently Issued]" : " [Available]");
    }
}

// User class
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Library class
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        System.out.println("\n Library Collection Overview:");
        for (Book b : books) {
            System.out.println(" - " + b);
        }
    }

    public void issueBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                if (!b.isIssued()) {
                    b.issue();
                    System.out.println(" Book issued successfully: \"" + title + "\"");
                } else {
                    System.out.println(" Sorry,this book is currently issued to another user.");
                }
                return;
            }
        }
        System.out.println(" Book titled \"" + title + "\" is not available in the system.");
    }

    public void returnBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                if (b.isIssued()) {
                    b.returnBook();
                    System.out.println(" Book returned successfully: \"" + title + "\"");
                } else {
                    System.out.println(" This book was not marked as issued.");
                }
                return;
            }
        }
        System.out.println(" No record found for the book titled \"" + title + "\".");
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook(new Book("Clean Code", "Robert C. Martin"));
        lib.addBook(new Book("Effective Java", "Joshua Bloch"));
        lib.addBook(new Book("Head First Java", "Kathy Sierra & Bert Bates"));

        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("====================================");
        System.out.println("   Welcome to the Digital Library");
        System.out.println("====================================");

        do {
            System.out.println("\n Please select an option:");
            System.out.println("1. View Available Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit the System");
            System.out.print(" Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    lib.showBooks();
                    break;
                case 2:
                    System.out.print(" Enter the title of the book to issue: ");
                    String issueTitle = sc.nextLine();
                    lib.issueBook(issueTitle);
                    break;
                case 3:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = sc.nextLine();
                    lib.returnBook(returnTitle);
                    break;
                case 4:
                    System.out.println("Thank you for using the Library Management System. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid menu choice.");
            }
        } while (choice != 4);

        sc.close();
    }
}
