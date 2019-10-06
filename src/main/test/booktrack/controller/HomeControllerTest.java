package booktrack.controller;

import booktrack.controller.HomeController;
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


import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {


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

    }


    @Test
    public void longBookTest() throws Exception {

        mvc.perform(get("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pushkin Alex")))
                .andExpect(content().string(containsString("9002")))
                .andExpect(content().string(containsString("120 Hours 1 minutes.")))
                .andExpect(content().string(containsString("How to kill")));
    }

    @Test
    public void shortBookTest() throws Exception {

        mvc.perform(get("/book/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Korobkov Nikita")))
                .andExpect(content().string(containsString("32")))
                .andExpect(content().string(containsString("25 minutes.")))
                .andExpect(content().string(containsString("How to juggle")));
    }

    @Test

    public void homeTest() throws Exception {

        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("BookTrack")));
    }


}
