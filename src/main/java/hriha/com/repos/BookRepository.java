package hriha.com.repos;

import hriha.com.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthor(String author);

    void deleteById(Integer id);

    Book findBookById(Integer id);
}
