package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ArenaDAO {

    private SessionFactory sessionFactory;

    public ArenaDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createArena(Arena arena)
    {
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
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            return session.get(Arena.class, arenaId);
        }
        catch(Exception E)
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
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Arena> arenas = s.createQuery("FROM Arena", Arena.class).list();
        s.close();
        return arenas;
    }

    public void updateArena(Arena arena)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.saveOrUpdate(arena);
            session.getTransaction().commit();
        }
        catch (Exception E)
        {
            E.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deleteArena(Arena arena)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
           session.remove(arena);
           session.getTransaction().commit();
        }
        catch(HibernateException E)
        {
            E.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
