import java.util.List;
import java.util.stream.Collectors;

public class BookSearch {

    public List<Book> searchByTitleOrAuthor(List<Book> books, String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerKeyword) 
                             || book.getAuthor().toLowerCase().contains(lowerKeyword)
                             || book.getPublisher().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

}