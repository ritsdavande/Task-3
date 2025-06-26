package com.LibrarySystem.java;

import java.util.*;

// Book class
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String toString() {
        return "[ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued + "]";
    }
}

// User class
class User {
    int userId;
    String name;

    User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String toString() {
        return "[User ID: " + userId + ", Name: " + name + "]";
    }
}

// Library class
class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    Map<Integer, Integer> issuedBooks = new HashMap<>(); // bookId -> userId

    void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    void registerUser(User user) {
        users.add(user);
        System.out.println("User registered successfully.");
    }

    void issueBook(int bookId, int userId) {
        for (Book book : books) {
            if (book.id == bookId && !book.isIssued) {
                book.isIssued = true;
                issuedBooks.put(bookId, userId);
                System.out.println("Book issued successfully.");
                return;
            }
        }
        System.out.println("Book not available or already issued.");
    }

    void returnBook(int bookId) {
        for (Book book : books) {
            if (book.id == bookId && book.isIssued) {
                book.isIssued = false;
                issuedBooks.remove(bookId);
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Invalid book ID or book not issued.");
    }

    void viewBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    void viewUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}

// Main class with menu
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View All Users");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    lib.addBook(new Book(bid, title, author));
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String uname = sc.nextLine();
                    lib.registerUser(new User(uid, uname));
                    break;

                case 3:
                    System.out.print("Enter Book ID to Issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueUserId = sc.nextInt();
                    lib.issueBook(issueBookId, issueUserId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to Return: ");
                    int returnBookId = sc.nextInt();
                    lib.returnBook(returnBookId);
                    break;

                case 5:
                    System.out.println("All Books:");
                    lib.viewBooks();
                    break;

                case 6:
                    System.out.println("All Users:");
                    lib.viewUsers();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 7);

        sc.close();
    }
}
