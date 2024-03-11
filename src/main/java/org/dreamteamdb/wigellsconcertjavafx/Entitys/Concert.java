package org.dreamteamdb.wigellsconcertjavafx.Entitys;
import jakarta.persistence.*;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "concert")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_id")
    private int id;

    @Column(name = "artist_name", length = 45)
    private String artistName;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "ticket_price")
    private double ticketPrice;

    @Column(name = "age_limit")
    private int ageLimit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arena_id")
    private Arena arena;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "concert_customer",
    joinColumns = {@JoinColumn(name = "concert_id")},
    inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customerList = new ArrayList<>();

    // Constructor
    public Concert() {
    }

    // CRUD-operations
    public void createConcert(Concert concert){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
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

    public Concert getConcertById(int id){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        Concert concert = s.get(Concert.class, id);
        s.close();

        return concert;
    }

    public List<Concert> getAllConcerts(){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        List<Concert> concerts = s.createQuery("FROM Concert", Concert.class).list();
        return concerts;
    }

    public void updateConcert(Concert concert){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
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

    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void addCustomerToList(Customer customer){
        this.customerList.add(customer);
    }
}
