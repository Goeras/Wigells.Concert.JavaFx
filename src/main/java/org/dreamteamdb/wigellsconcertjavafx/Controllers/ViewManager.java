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
import org.dreamteamdb.wigellsconcertjavafx.DAO.AddressDAO;
import org.dreamteamdb.wigellsconcertjavafx.DAO.ArenaDAO;
import org.dreamteamdb.wigellsconcertjavafx.DAO.ConcertDAO;
import org.dreamteamdb.wigellsconcertjavafx.DAO.CustomerDAO;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Address;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;
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

    /*AdminDAO adminDAO = new AdminDAO();*/


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

    public ObservableList<Concert> customerTickets(int customerId){
        //OBS TEST BYT TILL INLÄSNING FRÅN DATABAS
        Customer customer = getCustomerById(customerId);
        ObservableList<Concert> observableList = FXCollections.observableList(customer.getConcertList());
        return observableList;
    }
    public ObservableList<Concert> addNewConcert(Concert concert, int id){
        Customer customer = getCustomerById(id);
        customer.addConcertToList(concert);
        //Spara customer till databas
        ObservableList<Concert> observableList = FXCollections.observableList(customer.getConcertList());
        return observableList;

    }
    public ObservableList<Concert> upcomingConcerts(){
        List<Concert> upcomingConcerts = new ArrayList<>();
        //OBS TEST BYT TILL INLÄSNING FRÅN DATABAS
        upcomingConcerts.add(createTestConcert());
        ObservableList<Concert> observableList = FXCollections.observableList(upcomingConcerts);
        return observableList;
    }
    //ENBART TEST FÖR ATT SIMULERA DATA
    public Concert createTestConcert(){
        Arena arena = new Arena();
        arena.setName("Skolans gympahall");
        arena.setInside(true);
        arena.setAddress(new Address());

        Concert concert = new Concert();
        concert.setDate(LocalDate.now());
        concert.setArena(arena);
        concert.setArtistName("Carola");
        concert.setTicketPrice(10.1);
        concert.setAgeLimit(10);

        return concert;
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
    public String getCity(int id){
        Customer customer = getCustomerById(id);
        return customer.getAddress().getCity();
    }
    public String getCustBirthDay(int id){
        Customer customer = getCustomerById(id);
        return customer.getBirthDate().toString();
    }
    //OBS BYT TILL INLÄSNING DETTA ÄR BARA TEST
    public Customer getCustomerById(int id){
        Customer customer = new Customer();
        customer.setFirstName("förnamn");
        customer.setLastName("efternamn");
        customer.setBirthDate(LocalDate.now());

        Address address = new Address();
        address.setStreet("Gata");
        address.setHouseNo(1);
        address.setPostalCode(12345);
        address.setCity("Stad");

        customer.setAddress(address);
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


    /*public Customer customerLogIn(int id, String password){
        List<Customer> customerList = customerDAO.getAllCustomers();
        for(Customer customer : customerList){
            if(customer.getId() == id && customer.getPassword.equals(password)){
                return customer;
            }
        }
        return null;
    }*/

    /*public Admin adminLogIn(int id, String password){
        List<Admin> adminList = adminDAO.getAllAdmins();
        for(Admin admin : adminList){
            if(admin.getId() == id && admin.getPassword.equals(password)){
                return admin;
            }
        }
        return null;
    }*/

    public void deleteCustomer(int id){
        Customer customer = customerDAO.getCustomerById(id);
        customerDAO.deleteCustomer(customer);
    }

    public void deleteConcert(int id){
        Concert concert = concertDAO.readConcert(id);
        concertDAO.deleteConcert(concert);
    }

    public void deleteArena(int id){
        arenaDAO.deleteArena(id);
    }

    /*public void deleteAdmin(int id){
        Admin admin = adminDAO.readAdmin(id);
        adminDAO.deleteAdmin(admin);
    }*/

    /*public void newAdmin(String name, String password){
        Admin admin = new Admin();
        admin.setName(name);
        admin.setPassword(password);
        adminDAO.createAdmin(admin);

    }*/

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

    public void updateCustomer(int id, String firstName, String lastName, int phoneNumber, Address address){
        Customer customer = customerDAO.getCustomerById(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);

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
    public void newCustomer(String firstName, String lastName, String street, int housenumber, int postalCode, String city, LocalDate localDate/*, String password*/){
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
        /*customer.setPassword(password);*/

        /*customerDAO.createCustomer(customer);*/     //SPARA KUND TILL DATABAS
    }
    //LADDA NYA SIDOR
    public void loadCustomerPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void loadChangeInfoPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("changeInfo-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void loadLoginPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void loadNewCustPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newCust-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
