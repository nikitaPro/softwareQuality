package lab.domain;

import java.util.List;

public interface BookRepository{
    Book getBook(Long id);
    void createBook(Book createdEntity);
    boolean removeBook(Book updatedEntity);
    List<Book> getBooks();
}
