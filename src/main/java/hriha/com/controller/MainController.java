package hriha.com.controller;

import hriha.com.domain.Book;
import hriha.com.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller

public class MainController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/all")
    public String main(Map<String, Object> model) {

        Iterable<Book> books = bookRepository.findAll();

        model.put("books", books);

        return "main";
    }

    @PostMapping("/all")
    public String add(@RequestParam String author,
                      @RequestParam String name,
                      @RequestParam int price,
                      Map<String, Object> model) {

        Book book = new Book(author, name, price);
        bookRepository.save(book);

        Iterable<Book> books = bookRepository.findAll();

        model.put("books", books);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Map<String, Object> model) {
        Iterable<Book> books;
        if (filter != null && !filter.isEmpty()) {
            books = bookRepository.findByAuthor(filter);
        } else {
            books = bookRepository.findAll();
        }

        model.put("books", books);

        return "main";
    }
}
