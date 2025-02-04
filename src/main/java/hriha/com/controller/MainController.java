package hriha.com.controller;

import hriha.com.domain.Book;
import hriha.com.repos.BookRepository;
import hriha.com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public String main(Map<String, Object> model) {
        Iterable<Book> books = bookService.findAll();
        model.put("books", books);

        return "main";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String author,
                          @RequestParam String name,
                          @RequestParam int price) {


        bookService.saveBook(author, name, price);

        return "redirect:/all";
    }

    @GetMapping("/add")
    public String addBook() {
        return "add";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Map<String, Object> model) {
        Iterable<Book> books = bookService.filter(filter);
        model.put("books", books);

        return "main";
    }

    @Transactional
    @GetMapping("/delete")
    public String deleteBook(@RequestParam Integer id) {
        bookService.deleteBook(id);
        return "redirect:/all";
    }

    @GetMapping("/update")
    public String updateBook(@RequestParam Integer id,
                             Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "update";
    }

    @Transactional
    @PostMapping("/update")
    public String updateBook(@RequestParam Integer id,
                             @RequestParam String author,
                             @RequestParam String name,
                             @RequestParam int price){
        bookService.deleteBook(id);
        bookService.saveBook(author,name,price);

        return "redirect:/all";
    }
}
