package booktrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
// pg_ctl -D /usr/local/var/postgres -l /usr/local/var/postgres/server.log start

@SpringBootApplication
public class Application {
    public static void main(String[] args){

        SpringApplication.run(Application.class, args);

//        LibgenDBService libgenDBService = new LibgenDBService();
//
//
//        List<Book> searchResult = libgenDBService.searchByName("Anna");
//
//        searchResult.forEach(book -> {
//            if (Integer.valueOf(book.getPages()) > 0){
//                System.out.println(book.getPages()+" "+ book.getTitle());
//            }});
    }
}
