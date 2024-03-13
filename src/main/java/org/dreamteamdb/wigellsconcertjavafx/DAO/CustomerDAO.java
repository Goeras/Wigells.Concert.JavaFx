package org.dreamteamdb.wigellsconcertjavafx.DAO;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CustomerDAO {

    private SessionFactory sessionFactory;

    public CustomerDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createCustomer(Customer customer){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        try {
            s.persist(customer);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public Customer getCustomerById(int id){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        Customer customer = s.get(Customer.class, id);
        s.close();

        return customer;
    }

    public List<Customer> getAllCustomers(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Customer> customers = s.createQuery("FROM Customer", Customer.class).list();
        s.close();
        return customers;
    }

    public void updateCustomer(Customer customer){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.saveOrUpdate(customer);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public void deleteCustomer(Customer customer){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.remove(customer);
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

}
