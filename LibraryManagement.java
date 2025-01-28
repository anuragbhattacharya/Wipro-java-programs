import java.io.*;
import java.util.*;

// Class to represent a Book
class Book implements Serializable {
    private String id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isIssued=" + isIssued +
                '}';
    }
}

// Class to represent a Member
class Member implements Serializable {
    private String id;
    private String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

// Class to represent a Transaction
class Transaction implements Serializable {
    private String bookId;
    private String memberId;
    private Date issueDate;
    private Date returnDate;

    public Transaction(String bookId, String memberId, Date issueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
    }

    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "bookId='" + bookId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

public class LibraryManagement {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;

    public LibraryManagement() {
        books = loadFromFile("books.dat");
        members = loadFromFile("members.dat");
        transactions = loadFromFile("transactions.dat");
    }

    private <T> List<T> loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveToFile(String fileName, List<?> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + fileName);
        }
    }

    public void addBook(String id, String title, String author) {
        books.add(new Book(id, title, author));
        saveToFile("books.dat", books);
    }

    public void addMember(String id, String name) {
        members.add(new Member(id, name));
        saveToFile("members.dat", members);
    }

    public void issueBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        if (book != null && !book.isIssued()) {
            book.setIssued(true);
            transactions.add(new Transaction(bookId, memberId, new Date()));
            saveToFile("books.dat", books);
            saveToFile("transactions.dat", transactions);
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book is either not available or already issued.");
        }
    }

    public void returnBook(String bookId) {
        Book book = findBookById(bookId);
        if (book != null && book.isIssued()) {
            book.setIssued(false);
            for (Transaction transaction : transactions) {
                if (transaction.getBookId().equals(bookId) && transaction.getReturnDate() == null) {
                    transaction.setReturnDate(new Date());
                    break;
                }
            }
            saveToFile("books.dat", books);
            saveToFile("transactions.dat", transactions);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Invalid book return.");
        }
    }

    private Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public void viewBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void viewMembers() {
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public void viewTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        LibraryManagement lms = new LibraryManagement();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Books");
            System.out.println("6. View Members");
            System.out.println("7. View Transactions");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.next();
                    System.out.print("Enter Book Title: ");
                    String bookTitle = scanner.next();
                    System.out.print("Enter Book Author: ");
                    String bookAuthor = scanner.next();
                    lms.addBook(bookId, bookTitle, bookAuthor);
                    break;
                case 2:
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.next();
                    System.out.print("Enter Member Name: ");
                    String memberName = scanner.next();
                    lms.addMember(memberId, memberName);
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    bookId = scanner.next();
                    System.out.print("Enter Member ID: ");
                    memberId = scanner.next();
                    lms.issueBook(bookId, memberId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    bookId = scanner.next();
                    lms.returnBook(bookId);
                    break;
                case 5:
                    lms.viewBooks();
                    break;
                case 6:
                    lms.viewMembers();
                    break;
                case 7:
                    lms.viewTransactions();
                    break;
                case 8:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 8);
    }
}
