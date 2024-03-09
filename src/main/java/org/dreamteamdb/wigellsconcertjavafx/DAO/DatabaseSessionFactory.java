package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Lazy Singletonklass för att skapa EN SessionsFactory som kan användas i alla DAO.
public class DatabaseSessionFactory {

    private static volatile SessionFactory sessionFactory;
    private DatabaseSessionFactory() {}

    // Metod för att hämta Sessionfactory
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    // Metod för att kunna stänga SessionFactory när den inte längre behövs
    public void shutdown() {
        getSessionFactory().close();
    }
}
