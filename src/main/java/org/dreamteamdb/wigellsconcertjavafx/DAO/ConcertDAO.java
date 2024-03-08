package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ConcertDAO {

    private SessionFactory sessionFactory;

    public ConcertDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createConcert(Concert concert){
        //SessionFactory sf = new Configuration().configure().buildSessionFactory();
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
        //SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        Concert concert = s.get(Concert.class, id);
        s.close();

        return concert;
    }

    public List<Concert> readAllConcerts(){
        //SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Concert> concerts = s.createQuery("FROM Concert", Concert.class).list();
        return concerts;
    }

    public void updateConcert(Concert concert){
        //SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.merge(concert);
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
        //SessionFactory sf = new Configuration().configure().buildSessionFactory();
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

}
