package streams;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Books {

    public static void main(String[] args) {
        List<Book> books = List.of(

                new Book(1L, "Java", "Gosling"),
                new Book(2L, "Java Collections", "Gosling"),
                new Book(3L, "Python", "Jane"),
                new Book(4L, "JavaScript", "Jack")
                );

//        Map<Long, Book> booksById =
//                books.stream()
//                .collect(Collectors.toMap(
//                        Book::getId, // KeyMapper
//                        //b -> b
//                        Function.identity()
//                ));
//
//        System.out.println(booksById);
//        System.out.println(booksById.get(3L).getTitle());

        Map<String, String> bookTitles =
                books.stream()
                    .collect(Collectors.toMap(
                            Book::getAuthor,
                            Book::getTitle,
                            (t1, t2) -> t1 + ", " + t2));
        System.out.println(bookTitles.get("Gosling"));
    }
}
