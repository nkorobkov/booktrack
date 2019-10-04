package booktrack.service;

import booktrack.model.Book;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibgenDBService {

    private SessionFactory sessionFactory;

    @Autowired
    public LibgenDBService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    public Book get(Long id) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        session.close();
        return book;

    }

    public List<Book> searchByName(String query) {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> books = session.createQuery(
                    "from Book book where pages is not null and book.lowertitle like :Que ", Book.class);
            query = query.toLowerCase();
            books.setParameter("Que", "% "+query+"%");

            return books.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
