package hriha.com.service;

import hriha.com.domain.Book;
import hriha.com.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public void saveBook(String author, String name, int price) {
        Book book = new Book(author,name,price);
        bookRepository.save(book);
    }

    public Iterable<Book> filter(String filter) {
        Iterable<Book> books;
        if (filter != null && !filter.isEmpty()) {
            books = bookRepository.findByAuthor(filter);
        } else {
            books = bookRepository.findAll();
        }
        return books;
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
