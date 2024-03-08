package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ArenaDAO {

    private SessionFactory sessionFactory;

    public ArenaDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createArena(Arena arena)
    {
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
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
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
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

    public List<Arena> readAllArenas(){
        //SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Arena> arenas = s.createQuery("FROM Arena", Arena.class).list();
        return arenas;
    }

    public void updateArena(Arena arena)
    {
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.merge(arena);
            session.getTransaction().commit();
        }
        catch (HibernateException E)
        {
            E.printStackTrace();
        }
    }

    public void deleteArena(int arenaId)
    {
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Arena arenaToDelete = session.get(Arena.class, arenaId);

        try
        {
            if(arenaToDelete != null)
            {
                session.remove(arenaToDelete);
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
