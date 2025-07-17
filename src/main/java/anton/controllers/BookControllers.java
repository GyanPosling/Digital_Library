package anton.controllers;


import anton.dao.BookDAO;
import anton.dao.PersonDAO;
import anton.models.Book;
import anton.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookControllers {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public BookControllers(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute(bookDAO.findAll());
        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable int id, Model model) {
        model.addAttribute(bookDAO.findById(id));

        Optional<Person> owner = personDAO.findById(id);

        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        }
        else{
            model.addAttribute("person", personDAO.findAll());
        }
        return "books/showInfo";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute Book book) {
        return "books/new";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute @Valid Book book, Model model,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }


}
