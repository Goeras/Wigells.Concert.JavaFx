package org.dreamteamdb.wigellsconcertjavafx;

import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;

// Singleton klass för att hålla inloggad Customer.
public class CurrentUser {

    private static CurrentUser instance;
    private Customer currentCustomer;

    private CurrentUser(){}

    public static synchronized CurrentUser getInstance(){
        if(instance == null){
            instance = new CurrentUser();
        }
        return instance;
    }

    public Customer getCurrentUser(){
        return currentCustomer;
    }
    public void setCurrentCustomer(Customer customer){
        this.currentCustomer = customer;
    }
}
