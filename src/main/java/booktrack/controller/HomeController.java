package booktrack.controller;

import booktrack.model.Book;
import booktrack.service.LibgenDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private LibgenDBService libgenDBService;

    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/book/{id}")
    public String bookPage(Model model, @PathVariable(value="id") String id) {
        Book book = libgenDBService.get(Long.valueOf(id));
        if (book == null){ return "no-book";}
        model.addAttribute("title", book.getTitle());
        model.addAttribute("pages", book.getPages());
        model.addAttribute("author", book.getAuthorfamily1() + " " + book.getAuthorname1());
        model.addAttribute("avgReadTime", Integer.valueOf(book.getPages())*0.8);

        return "book";
    }

}
