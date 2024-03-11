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
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Address;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;
import org.dreamteamdb.wigellsconcertjavafx.HelloApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

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


    public void getNewInfo(String firstName, String lastName, String street, int housenumber, int postalCode, String city, int id){
        //BYT TILL UPDATE METOD
    }
    public void newCustomer(String firstName, String lastName, String street, int housenumber, int postalCode, String city, LocalDate localDate){
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
        //SPARA KUND TILL DATABAS
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
