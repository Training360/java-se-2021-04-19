package functional;

import java.util.List;
import java.util.Optional;

public class BookService {


    public Optional<Book> findById(List<Book> books, long id) {
        for (var book: books) {
            if (book.getId() == id) {
                return Optional.of(book); //  Simple factory method
            }
        }
        // Mi van, ha nem találom meg?
        return Optional.empty();
    }

    public static void main(String[] args) {
        var books = List.of(
                new Book(1L,"Java"),
                new Book(2L, "Python"),
                new Book(3L, "JavaScript")
        );

        Optional<Book> book = new BookService().findById(books, 2L);
//        if (book.isEmpty()) {
//            System.out.println("Nem talált");
//        }
//        else {
//            System.out.println(book.get().getTitle());
//        }
//        if (book.isPresent()) {
//            System.out.println(book.get().getTitle());
//        }
//        Book result = book
//                .orElseThrow(() -> new IllegalArgumentException("Not found"));

        String title = book
                .orElseGet(() -> new Book(0L, "<unknown>")).getTitle();
        System.out.println(title);
    }

}
