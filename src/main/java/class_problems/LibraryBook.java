public class LibraryBook {
    private String title;
    private String isbn;
    private boolean isCatalogued;

    // Primary constructor handling all field initializations
    public LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = (isbn == null || isbn.trim().isEmpty()) ? "PENDING" : isbn;
        this.isCatalogued = true;
    }

    // Chained constructor using this(...) to avoid duplicated initialization
    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    public void display() {
        System.out.println(this.title + " | " + this.isbn + " | Catalogued: " + this.isCatalogued);
    }

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        for (int i = 0; i < titles.length; i++) {
            LibraryBook book;
            if (isbns[i].trim().isEmpty()) {
                book = new LibraryBook(titles[i]);
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }
            book.display();
        }
    }
}
