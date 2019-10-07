package booktrack.controller;

import booktrack.model.Book;
import booktrack.service.LibgenDBService;
import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;
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
    @Throttling(type = ThrottlingType.RemoteAddr)
    public String bookPage(Model model, @PathVariable(value = "id") String id) {
        Book book = libgenDBService.get(Long.valueOf(id));
        if (book == null) {
            return "no-book";
        }
        model.addAttribute("title", book.getTitle());
        model.addAttribute("pages", book.getPages());
        model.addAttribute("author", book.getAuthorfamily1() + " " + book.getAuthorname1());

        StringBuilder stringBuilder = new StringBuilder();
        int minsRead = (int) (Integer.valueOf(book.getPages()) * 0.8);
        Integer hoursRead = Integer.divideUnsigned(minsRead, 60);
        minsRead = minsRead % 60;
        if (hoursRead > 0) {
            stringBuilder.append(hoursRead).append(" Hour");
            if (hoursRead > 1) {
                stringBuilder.append("s");
            }
            stringBuilder.append(" ");
        }
        stringBuilder.append(minsRead).append(" minutes.");
        model.addAttribute("avgReadTime", stringBuilder.toString());

        return "book";
    }

}
