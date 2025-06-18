import java.util.List;
import java.util.stream.Collectors;

public class BookSearch {
    public List<Book> searchByTitleOrAuthor(List<Book> books, String keyword) {
        ArrayList<Book> result = null;
    try{
        String lowerKeyword = keyword.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerKeyword) 
                             || book.getAuthor().toLowerCase().contains(lowerKeyword)
                             || book.getPublisher().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
    catch(Exception e){
        System.err.println("Lỗi khi tìm sách:" + e.getMessage());
        result = ArrayList.of();
    }
    finally{
        System.out.println("Kết thúc tìm sách.");
    }
    return result;
    }
}