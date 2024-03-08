package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.WC;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class WcDAO {

    private SessionFactory sessionFactory;

    public WcDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createWC(WC wc) {
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(wc);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        finally{
            session.close();
        }
    }

    public WC readWC(int id){
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        WC wc = new WC();
        try{
            session.beginTransaction();
            wc = session.get(WC.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
        }
        session.close();
        return wc;
    }
    public List<WC> readAllWC(){
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<WC> wc = new ArrayList<>();
        try{
            session.beginTransaction();
            Query<WC> query = session.createQuery("FROM WC ", WC.class);
            wc = query.list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return wc;
    }

    public void updateWC(WC wc){
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.merge(wc);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deleteWC(WC wc){
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.remove(wc);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }
}
