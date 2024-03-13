package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dreamteamdb.wigellsconcertjavafx.CurrentUser;
import org.dreamteamdb.wigellsconcertjavafx.DAO.*;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.*;
import org.dreamteamdb.wigellsconcertjavafx.HelloApplication;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    CustomerDAO customerDAO = new CustomerDAO();
    ConcertDAO concertDAO = new ConcertDAO();
    ArenaDAO arenaDAO = new ArenaDAO();
    AddressDAO addressDAO = new AddressDAO();
    AdminDAO adminDAO = new AdminDAO();


    //CONCERTPROPERTIES
    public SimpleStringProperty dateProperty(LocalDate localDate){
        return new SimpleStringProperty(localDate.toString());
    }
    public SimpleStringProperty arenaProperty(Arena arena){
        return new SimpleStringProperty(arena.getName());
    }
    public SimpleStringProperty indoorProperty(Arena arena){
        String inside = "";
        if(arena.isInside()){
            inside = "Inomhus";
        } else {
            inside = "Utomhus";
        }
        return new SimpleStringProperty(inside);
    }
    public SimpleDoubleProperty priceProperty(double price){
        return new SimpleDoubleProperty(price);
    }
    public SimpleIntegerProperty ageProperty(int age){
        return new SimpleIntegerProperty(age);
    }

    public ObservableList<Concert> customerTickets(Customer customer/*int customerId*/){
        //OBS TEST BYT TILL INLÄSNING FRÅN DATABAS
        //Customer customer = getCustomerById(customerId); // = customerDAO.getCustomerById(customerId);
        ObservableList<Concert> observableList = FXCollections.observableList(customer.getConcertList());
        return observableList;
    }
    public ObservableList<Concert> addNewConcert(Concert concert, Customer customer){
        customer.addConcertToList(concert);
        customerDAO.updateCustomer(customer);
        ObservableList<Concert> observableList = FXCollections.observableList(customer.getConcertList());
        return observableList;

    }

    public ObservableList<Concert> removeConcert(Concert concert, Customer customer){
        customer.removeConcertFromList(concert);
        customerDAO.updateCustomer(customer);
        ObservableList<Concert> observableList = FXCollections.observableList(customer.getConcertList());
        return observableList;

    }

    public ObservableList<Concert> upcomingConcerts(){
        List<Concert> upcomingConcerts = concertDAO.readAllConcerts();
        ObservableList<Concert> observableList = FXCollections.observableList(upcomingConcerts);
        return observableList;
    }
    //CUSTOMERPROPERTIES
    public String getCustomerFirstName(int id){
        Customer customer = getCustomerById(id);
        return customer.getFirstName();
    }
    public String getCustomerLastName(int id){
        Customer customer = getCustomerById(id);
        return customer.getLastName();
    }

    public String getStreetAdress(int id){
        Customer customer = getCustomerById(id);
        String streetAdress = customer.getAddress().getStreet() + " " + customer.getAddress().getHouseNo();
        return streetAdress;
    }
    public String getHouseNumber(int id){
        Customer customer = getCustomerById(id);
        return Integer.toString(customer.getAddress().getHouseNo());
    }
    public String getPostalCode(int id){
        Customer customer = getCustomerById(id);
        return Integer.toString(customer.getAddress().getPostalCode());
    }
    public String getPhoneNumber(int id){
        Customer customer= getCustomerById(id);
        return Integer.toString(customer.getPhoneNumber());
    }
    public String getCity(int id){
        Customer customer = getCustomerById(id);
        return customer.getAddress().getCity();
    }
    public String getCustBirthDay(int id){
        Customer customer = getCustomerById(id);
        return customer.getBirthDate().toString();
    }
    public SimpleStringProperty getCustomerFullName(int id){
        Customer customer = getCustomerById(id);
        return new SimpleStringProperty(customer.getFirstName() + " " + customer.getLastName());
    }
    public SimpleStringProperty getCustomerBirth(int id){
        Customer customer = getCustomerById(id);
        return new SimpleStringProperty(customer.getBirthDate().toString());
    }
    public SimpleStringProperty getCustomerPhone(int id){
        Customer customer = getCustomerById(id);
        return new SimpleStringProperty(Integer.toString(customer.getPhoneNumber()));
    }
    public SimpleStringProperty toStringProperty(String string){
        return new SimpleStringProperty(string);
    }
    //OBS BYT TILL INLÄSNING DETTA ÄR BARA TEST
    public Customer getCustomerById(int id){
        Customer customer = customerDAO.getCustomerById(id);

        /*Customer customer = new Customer();
        customer.setFirstName("förnamn");
        customer.setLastName("efternamn");
        customer.setBirthDate(LocalDate.now());

        Address address = new Address();
        address.setStreet("Gata");
        address.setHouseNo(1);
        address.setPostalCode(12345);
        address.setCity("Stad");

        customer.setAddress(address);*/
        return customer;
    }

    public Concert getConcertById(int id){
        return concertDAO.readConcert(id);
    }

    public Arena getArenaById(int id){
        return arenaDAO.readArena(id);
    }

    public Address getAddressById(int id){
        return addressDAO.readAddress(id);
    }


    public boolean customerLogIn(int phoneNumber, String password){
        List<Customer> customerList = customerDAO.getAllCustomers();
        for(Customer customer : customerList){
            if(customer.getPhoneNumber() == phoneNumber && customer.getPassword().equals(password)){
                CurrentUser.getInstance().setCurrentCustomer(customer);
                return true;
            }
        }
        return false;
    }

    public boolean adminLogIn(int id, String password){
        List<Admin> adminList = adminDAO.readAllAdmins();
        for(Admin admin : adminList){
            if(admin.getId() == id && admin.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public void deleteCustomer(Customer customer){
        customerDAO.deleteCustomer(customer);
    }

    public void deleteConcert(int id){
        Concert concert = concertDAO.readConcert(id);
        concertDAO.deleteConcert(concert);
    }

    public void deleteArena(int id){
        Arena arena = arenaDAO.readArena(id);
        arenaDAO.deleteArena(arena);
    }

    public void deleteAdmin(int id){
        Admin admin = adminDAO.readAdmin(id);
        adminDAO.deleteAdmin(admin);
    }

    public void newAdmin(String firstName, String lastName, String password){
        Admin admin = new Admin();
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setPassword(password);
        adminDAO.createAdmin(admin);
    }

    public void newConcert(String artistName, LocalDate date, double ticketPrice, Arena arena, int ageLimit){
        Concert concert =  new Concert();
        concert.setArtistName(artistName);
        concert.setDate(date);
        concert.setTicketPrice(ticketPrice);
        concert.setArena(arena);
        concert.setAgeLimit(ageLimit);

        concertDAO.createConcert(concert);
    }

    public void newArena(String name, Address address, boolean inside){
        Arena arena = new Arena();
        arena.setName(name);
        arena.setAddress(address);
        arena.setInside(inside);

        arenaDAO.createArena(arena);
    }

    public void updateCustomer(Customer customer, Address address){
        /*Customer customer = customerDAO.getCustomerById(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);*/

        addressDAO.updateAddress(address);
        customerDAO.updateCustomer(customer);
    }

    public void updateConcert(int id, String artistName, LocalDate date, Arena arena, int ageLimit){
        Concert concert = concertDAO.readConcert(id);
        concert.setArtistName(artistName);
        concert.setDate(date);
        concert.setArena(arena);
        concert.setAgeLimit(ageLimit);

        concertDAO.updateConcert(concert);
    }

    public void updateAddress(int id, String street, int houseNbr, int postalCode, String city){
        Address address = addressDAO.readAddress(id);
        address.setStreet(street);
        address.setHouseNo(houseNbr);
        address.setPostalCode(postalCode);
        address.setCity(city);

        addressDAO.updateAddress(address);
    }

    public void getNewInfo(String firstName, String lastName, String street, int housenumber, int postalCode, String city, int id){
        //BYT TILL UPDATE METOD
    }
    public void newCustomer(String firstName, String lastName, int phoneNumber, String street, int housenumber, int postalCode, String city, LocalDate localDate, String password){
        Address address = new Address();
        address.setStreet(street);
        address.setHouseNo(housenumber);
        address.setPostalCode(postalCode);
        address.setCity(city);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setBirthDate(localDate);
        customer.setPassword(password);
        customer.setPhoneNumber(phoneNumber);

        customerDAO.createCustomer(customer);
    }
    //LADDA NYA SIDOR
    public void loadCustomerPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Din Sida");
        stage.setScene(scene);
        stage.show();
    }
    public void loadChangeInfoPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("changeInfo-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Uppdatera dina personuppgifter");
        stage.setScene(scene);
        stage.show();
    }
    public void loadLoginPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Wigello Concerto");
        stage.setScene(scene);
        stage.show();
    }
    public void loadNewCustPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newCust-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Ny kund");
        stage.setScene(scene);
        stage.show();
    }
    public void loadAdminPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }
}
