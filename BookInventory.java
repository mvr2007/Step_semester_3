public class BookInventory {
    String title, author;
    int copiesAvailable;

    BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public void printEntry() {
        System.out.println("Title: " + title + ", Author: " + author + ", Copies Available: " + copiesAvailable);
    }

    public static void main(String[] args) {
        BookInventory book1 = new BookInventory("Clean Code", "Robert C. Martin", 3);
        BookInventory book2 = new BookInventory("Effective Java", "Joshua Bloch", 5);
        BookInventory book3 = new BookInventory("Refactoring", "Martin Fowler", 0);
        BookInventory book4 = new BookInventory("Design Patterns", "GoF", 2);

        book1.printEntry();
        book2.printEntry();
        book3.printEntry();
        book4.printEntry();
    }
}