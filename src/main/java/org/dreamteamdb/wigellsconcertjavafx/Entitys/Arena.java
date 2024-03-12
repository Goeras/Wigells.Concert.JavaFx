package org.dreamteamdb.wigellsconcertjavafx.Entitys;

import jakarta.persistence.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="arena")
public class Arena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arena_id")
    private int id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "inside")
    private boolean inside;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Arena()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void createArena(Arena arena)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
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
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            return session.get(Arena.class, arenaId);
        }
        catch(HibernateException E)
        {
            E.printStackTrace();
            return null;
        }
        finally
        {
            session.close();
        }
    }

    public void updateArena(Arena arenaToUpdate)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.update(arenaToUpdate);
            session.getTransaction().commit();
        }
        catch (HibernateException E)
        {
            E.printStackTrace();
        }
    }

    public static void deleteArena(int arenaId)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Arena arenaToDelete = session.get(Arena.class, arenaId);

        try
        {
            if(arenaToDelete != null)
            {
                session.delete(arenaToDelete);
                session.getTransaction().commit();
                System.out.println("Arena med id: " + arenaId + " har tagits bort.");
            }
            else
            {
                System.out.println("Arenan med id: " + arenaId + " kunde inte hittas.");
            }
        }
        catch(HibernateException E)
        {
            E.printStackTrace();
        }
    }
}

