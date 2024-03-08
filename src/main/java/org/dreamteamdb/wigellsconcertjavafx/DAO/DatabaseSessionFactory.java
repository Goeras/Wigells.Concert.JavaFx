package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Singletonklass för att skapa EN SessionsFactory som kan användas i alla DAO.
public class DatabaseSessionFactory {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private DatabaseSessionFactory() {}

    // Metod för att skapa SessionFactory
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metod för att hämta SessionFactory-instans
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Metod för att stänga SessionFactory när den inte längre behövs
    public static void shutdown() {
        getSessionFactory().close();
    }
}
