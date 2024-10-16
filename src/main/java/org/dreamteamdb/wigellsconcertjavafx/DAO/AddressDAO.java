package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AddressDAO {

    private SessionFactory sessionFactory;

    public AddressDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createAddress(Address address) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(address);
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

    public Address readAddress(int id){
        Session session = sessionFactory.openSession();

        Address address = new Address();
        try{
            session.beginTransaction();
            address = session.get(Address.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
        }
        session.close();
        return address;
    }
    public List<Address> readAllAddresses(){
        Session session = sessionFactory.openSession();
        List<Address> addresses = new ArrayList<>();
        try{
            session.beginTransaction();
            Query<Address> query = session.createQuery("FROM Address", Address.class);
            addresses = query.list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return addresses;
    }

    public void updateAddress(Address address){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.saveOrUpdate(address);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deleteAddress(Address address){
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.remove(address);
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
