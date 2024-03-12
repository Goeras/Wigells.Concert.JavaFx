package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Admin;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AdminDAO {

    private SessionFactory sessionFactory;

    public AdminDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createAdmin(Admin admin){

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.persist(admin);
            session.getTransaction().commit();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }

    public Admin readAdmin(int id)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            return session.get(Admin.class, id);
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
    public List<Admin> readAllAdmins(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Admin> admins = session.createQuery("FROM Admin", Admin.class).list();
        session.close();
        return admins;
    }
    public void updateAdmin(Admin admin)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.merge(admin);
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
    public void deleteAdmin(Admin admin)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.remove(admin);
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
