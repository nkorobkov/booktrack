package booktrack.controller;
import booktrack.model.Book;
import booktrack.service.LibgenDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    private LibgenDBService libgenDBService;


    @RequestMapping("/searchByName")
    public List<Book> nameSearch(@RequestParam(value="name", defaultValue="World") String name) {
        return libgenDBService.searchByName(name);
    }
}
