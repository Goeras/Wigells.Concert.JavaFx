package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ConcertDAO {

    private SessionFactory sessionFactory;

    public ConcertDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createConcert(Concert concert){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        try {
            s.persist(concert);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public Concert readConcert(int id){
        Concert concert = new Concert();
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        concert = s.get(Concert.class, id);
        s.close();

        return concert;
    }

    public List<Concert> readAllConcerts(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Concert> concerts = s.createQuery("FROM Concert", Concert.class).list();
        s.close();
        return concerts;
    }

    public void updateConcert(Concert concert){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.saveOrUpdate(concert);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public void deleteConcert(Concert concert){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.remove(concert);
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }
    public void createConcert2(Concert concert, int id){
        ArenaDAO arenaDAO = new ArenaDAO();

        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try {
            Arena arena = s.get(Arena.class, id);
            concert.setArena(arena);
            s.persist(concert);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }
}
