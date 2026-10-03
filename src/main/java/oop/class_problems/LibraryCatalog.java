import java.util.List;

public class LibraryCatalog {
    public static class Book {
        public String isbn;
        public String title;

        public Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    public static String findBook(List<Book> catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = catalog.get(mid).isbn.compareTo(targetIsbn);

            if (comparison == 0) {
                return catalog.get(mid).title;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }
}
