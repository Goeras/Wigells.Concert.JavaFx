package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ArenaDAO {

    public void createArena(Arena arena)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.persist(arena);
            session.getTransaction().commit();
        }
        catch(HibernateException E)
        {

        }
        finally
        {
            session.close();
        }
    }

    public Arena readArena(int arenaId)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            return session.get(Arena.class, arenaId);
        }
        catch(HibernateException E)
        {
            E.printStackTrace();
            return null;
        }
        finally
        {
            session.close();
        }
    }

    public List<Concert> readAllArenas(){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        List<Concert> concerts = s.createQuery("FROM Concert", Concert.class).list();
        return concerts;
    }

    public void updateArena(Arena arenaToUpdate)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.update(arenaToUpdate);
            session.getTransaction().commit();
        }
        catch (HibernateException E)
        {
            E.printStackTrace();
        }
    }

    public static void deleteArena(int arenaId)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Arena arenaToDelete = session.get(Arena.class, arenaId);

        try
        {
            if(arenaToDelete != null)
            {
                session.delete(arenaToDelete);
                session.getTransaction().commit();
                System.out.println("Arena med id: " + arenaId + " har tagits bort.");
            }
            else
            {
                System.out.println("Arenan med id: " + arenaId + " kunde inte hittas.");
            }
        }
        catch(HibernateException E)
        {
            E.printStackTrace();
        }
    }
}
