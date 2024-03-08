package org.dreamteamdb.wigellsconcertjavafx.Entitys;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "street", length = 45)
    private String street;

    @Column(name = "house_nbr")
    private int houseNo;

    @Column(name = "postal_code")
    private int postalCode;

    @Column(name = "city", length = 45)
    private String city;

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void create(Session session) {


        try {
            session.beginTransaction();
            session.persist(this);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    public static Address read(Session session, int id){
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
        return address;
    }
    public static List<Address> readList(Session session, List<Address> addresses, String queryString){
        try{
            session.beginTransaction();
            Query<Address> query = session.createQuery(queryString, Address.class);
            addresses = query.list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return addresses;
    }

    public void update(Session session){
        session.beginTransaction();
        session.merge(this);
        session.getTransaction().commit();
    }

    public void delete(Session session, Address address){
        try {
            session.beginTransaction();
            session.remove(address);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}

