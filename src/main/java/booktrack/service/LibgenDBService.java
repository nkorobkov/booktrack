package booktrack.service;

import booktrack.model.Book;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibgenDBService {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public LibgenDBService() {
        sessionFactory = getSessionFactory();

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
                    "from Book book where pages is not null and book.title like :Que ", Book.class);

            books.setParameter("Que", "%"+query+"%");

            return books.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();
                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);
                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
