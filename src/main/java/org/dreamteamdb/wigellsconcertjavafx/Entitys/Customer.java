package org.dreamteamdb.wigellsconcertjavafx.Entitys;

import jakarta.persistence.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone_nbr")
    private int phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "address_id")
    @JoinColumn(name = "address")
    private Address address;    // Kolla video hur denna ska vara.

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "wc",
    joinColumns = {@JoinColumn(name = "customer_id")},
    inverseJoinColumns = {@JoinColumn(name = "concert_id")})
    private List<Concert> concertList = new ArrayList<>();

    // Constructor
    public Customer() {
    }

    // CRUD-operations
    public void createConcert(Customer customer){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        Customer customer = s.get(Customer.class, id);
        s.close();

        return customer;
    }

    public List<Customer> getAllCustomer(){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        List<Customer> customers = s.createQuery("FROM Customer", Customer.class).list();
        return customers;
    }

    public void updateCustomer(Customer customer){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        try{
            s.merge(customer);
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
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
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


    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Concert> getConcertList() {
        return concertList;
    }

    public void setConcertList(List<Concert> concertList) {
        this.concertList = concertList;
    }

    public void addConcertToList(Concert concert){
        this.concertList.add(concert);
    }
}
