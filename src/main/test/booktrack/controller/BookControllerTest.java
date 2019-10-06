package booktrack.controller;

import booktrack.model.Book;
import booktrack.service.LibgenDBService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibgenDBService libgenDBService;

    // write test cases here

    @Before
    public void setUp() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setAuthorfamily1("Pushkin");
        book1.setAuthorname1("Alex");
        book1.setTitle("How to kill");
        book1.setPages("9002");

        given(libgenDBService.get(1L)).willReturn(book1);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setAuthorfamily1("Korobkov");
        book2.setAuthorname1("Nikita");
        book2.setTitle("How to juggle");
        book2.setPages("32");

        given(libgenDBService.get(2L)).willReturn(book2);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        given(libgenDBService.searchByName("how to")).willReturn(books);

    }


    @Test
    public void searchTest() throws Exception {

        mvc.perform(get("/searchByName")
                .param("name", "how to")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("How to kill")));
    }




}
