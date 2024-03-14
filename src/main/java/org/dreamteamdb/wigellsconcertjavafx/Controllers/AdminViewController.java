package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Address;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AdminViewController {
    @FXML
    TableView<Concert> upComingConcerts;
    @FXML
    private TableColumn<Concert, String> upcomingDate;
    @FXML
    private TableColumn<Concert, String> upcomingArtist;
    @FXML
    private TableColumn<Concert, String> upcomingArena;
    @FXML
    private TableColumn<Concert, String> upcomingIndoor;
    @FXML
    private TableColumn<Concert, Double> upcomingPrice;
    @FXML
    private TableColumn<Concert, Integer> upcomingMinAge;
    private ObservableList<Concert> upcomingConcertList;
    @FXML
    private TableView<Customer> concertCustomers;
    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> birthdate;
    @FXML
    private TableColumn<Customer, String> streetAddress;
    @FXML
    private TableColumn<Customer, String> postAddress;
    @FXML
    private TableColumn<Customer, String> phoneNr;
    @FXML
    private TableView<Arena> arenas;
    @FXML
    private TableColumn<Arena, String> arenaName;
    @FXML
    private TableColumn<Arena, String> arenaStreet;
    @FXML
    private TableColumn<Arena, String> arenaPost;
    @FXML
    private TableColumn<Arena, String> arenaIndoor;
    @FXML
    private VBox newConcert;
    @FXML
    private VBox updateConcert;
    @FXML
    private VBox newArena;
    @FXML
    private VBox updateArena;
    @FXML
    private TextField updateName;
    @FXML
    private TextField updateStreet;
    @FXML
    private TextField updateHouse;
    @FXML
    private TextField updatePostal;
    @FXML
    private TextField updateCity;
    @FXML
    private CheckBox indoorcheck;
    @FXML
    private TextField newArenaName;
    @FXML
    private TextField newArenaStreet;
    @FXML
    private TextField newArenaHouse;
    @FXML
    private TextField newArenaPostal;
    @FXML
    private TextField newArenaCity;
    @FXML
    private CheckBox indoorCheckBox;
    @FXML
    private TextField newConcertArtist;
    @FXML
    private TextField newConcertArenaId;
    @FXML
    private TextField newConcertDate;
    @FXML
    private TextField newConcertPrice;
    @FXML
    private TextField newConcertAge;
    @FXML
    ViewManager viewManager = new ViewManager();
    @FXML
    ChoiceBox<Arena> arenaChoiceBox;

    @FXML
    private TextField updateConcertArtist;
    @FXML
    private TextField updateConcertPrice;
    @FXML
    private TextField updateConcertAge;
    @FXML
    private TextField updateConcertDate;
    @FXML
    private ChoiceBox<Arena> updateArenaChoice;
    @FXML
    private TextField updateConcertArena;

    ObservableList<Arena> observableList;

    public void initialize() {
        viewManager = new ViewManager();
        upcomingConcertList = viewManager.upcomingConcerts();

        upcomingArtist.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        upcomingArena.setCellValueFactory(cellData -> viewManager.arenaProperty(cellData.getValue().getArena()));
        upcomingDate.setCellValueFactory(cellData -> viewManager.dateProperty(cellData.getValue().getDate()));
        upcomingIndoor.setCellValueFactory(cellData -> viewManager.indoorProperty(cellData.getValue().getArena()));
        upcomingPrice.setCellValueFactory(cellData -> viewManager.priceProperty(cellData.getValue().getTicketPrice()).asObject());
        upcomingMinAge.setCellValueFactory(cellData -> viewManager.ageProperty(cellData.getValue().getAgeLimit()).asObject());
        upComingConcerts.setItems(upcomingConcertList);

        name.setCellValueFactory(cellData -> viewManager.getCustomerFullName(cellData.getValue().getId()));
        birthdate.setCellValueFactory(cellData -> viewManager.getCustomerBirth(cellData.getValue().getId()));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        streetAddress.setCellValueFactory(cellData -> viewManager.toStringProperty(viewManager.getStreetAdress(cellData.getValue().getId())));
        postAddress.setCellValueFactory(cellData -> viewManager.toStringProperty(viewManager.getPostalCode(cellData.getValue().getId()) + " " + viewManager.getCity(cellData.getValue().getId())));
        phoneNr.setCellValueFactory(cellData -> viewManager.toStringProperty(viewManager.getPhoneNumber(cellData.getValue().getId())));

       arenaName.setCellValueFactory(cellData -> viewManager.toStringProperty(cellData.getValue().getName()));
        arenaStreet.setCellValueFactory(cellData -> viewManager.toStringProperty(cellData.getValue().getAddress().getStreet() + " " + cellData.getValue().getAddress().getHouseNo()));
        arenaPost.setCellValueFactory(cellData -> viewManager.toStringProperty(cellData.getValue().getAddress().getPostalCode() + " " + cellData.getValue().getAddress().getCity()));
        arenaIndoor.setCellValueFactory(cellData -> viewManager.indoorProperty(cellData.getValue()));

        List<Arena> arenasList = viewManager.arenaDAO.readAllArenas();
        observableList = FXCollections.observableList(arenasList);
        arenas.setItems(observableList);
    }

    @FXML
    public void onSeeCustButton() {
      Concert concert = upComingConcerts.getSelectionModel().getSelectedItem();
      List<Customer> customerList = viewManager.concertDAO.readConcert(concert.getId()).getCustomerList();
        ObservableList customers = FXCollections.observableList(customerList);
        concertCustomers.setItems(customers);
        if (!concertCustomers.isVisible()) {
            concertCustomers.setVisible(true);
            concertCustomers.setManaged(true);
        }
    }
    @FXML
    public void onNewConcertButton(){
        if(!newConcert.isManaged()){
            newConcert.setManaged(true);
            newConcert.setVisible(true);
        }
        List<Arena> arenasList = viewManager.arenaDAO.readAllArenas();
        observableList = FXCollections.observableList(arenasList);
        arenaChoiceBox.setItems(observableList);
    }
    @FXML
    public  void onSaveConcertButton(){
        Concert concert = new Concert();
        Arena arena = arenaChoiceBox.getValue();
        concert.setArtistName(newConcertArtist.getText());
        concert.setDate(LocalDate.parse(newConcertDate.getText()));
        concert.setAgeLimit(Integer.parseInt(newConcertAge.getText()));
        concert.setTicketPrice(Double.parseDouble(newConcertPrice.getText()));

        viewManager.concertDAO.createConcert2(concert, arena.getId());
        ObservableList concertObservableList = FXCollections.observableList(viewManager.concertDAO.readAllConcerts());
        upComingConcerts.setItems(concertObservableList);

        if(newConcert.isManaged()){
            newConcert.setManaged(false);
            newConcert.setVisible(false);
        }
    }
    @FXML
    public void onUpdateConcertButton(){
            Concert concert = upComingConcerts.getSelectionModel().getSelectedItem();
            updateConcertArtist.setText(concert.getArtistName());
            updateConcertDate.setText(concert.getDate().toString());
            updateConcertAge.setText(Integer.toString(concert.getAgeLimit()));
            updateConcertPrice.setText(Double.toString(concert.getTicketPrice()));
            updateConcertArena.setText(concert.getArena().getName());
            updateArenaChoice.setItems(observableList);
        if(!updateConcert.isManaged()){
            updateConcert.setManaged(true);
            updateConcert.setVisible(true);
        }
    }
    @FXML
    public void onSaveUpdateConcert(){
        Concert concert = upComingConcerts.getSelectionModel().getSelectedItem();
        if(updateArenaChoice.getValue() != null){
        concert.setArena((Arena) updateArenaChoice.getValue());}
        concert.setArtistName(updateConcertArtist.getText());
        concert.setTicketPrice(Double.parseDouble(updateConcertPrice.getText()));
        concert.setDate(LocalDate.parse(updateConcertDate.getText()));
        concert.setAgeLimit(Integer.parseInt(updateConcertAge.getText()));
        viewManager.concertDAO.updateConcert(concert);
        if(updateConcert.isManaged()){
            updateConcert.setVisible(false);
            updateConcert.setManaged(false);
        }
    }
    @FXML
    public void onDeleteConcert(){
        Concert concert = upComingConcerts.getSelectionModel().getSelectedItem();
        viewManager.deleteConcert(concert);
        upcomingConcertList.remove(concert);
        upComingConcerts.refresh();
    }
    @FXML
    public void onNewArenaButton(){
        if(!newArena.isManaged()){
            newArena.setManaged(true);
            newArena.setVisible(true);
        }
    }
    @FXML
    public void onSaveNewArena(){
        Arena arena = new Arena();
        Address address = new Address();
        address.setStreet(newArenaStreet.getText());
        address.setHouseNo(Integer.parseInt(newArenaHouse.getText()));
        address.setPostalCode(Integer.parseInt(newArenaPostal.getText()));
        address.setCity(newArenaCity.getText());

        arena.setName(newArenaName.getText());
        arena.setAddress(address);
        arena.setInside(indoorCheckBox.isSelected());
        viewManager.arenaDAO.createArena(arena);
        observableList = FXCollections.observableList(viewManager.arenaDAO.readAllArenas());
        arenas.setItems(observableList);

        if(newArena.isManaged()){
            newArena.setManaged(false);
            newArena.setVisible(false);
        }

    }
    @FXML
    public void onUpdateArenaButton(){
        Arena arena = arenas.getSelectionModel().getSelectedItem();
        if(arena != null){
            updateName.setText(arena.getName());
            updateStreet.setText(arena.getAddress().getStreet());
            updateHouse.setText(Integer.toString(arena.getAddress().getHouseNo()));
            updatePostal.setText(Integer.toString(arena.getAddress().getPostalCode()));
            updateCity.setText(arena.getAddress().getCity());
            if(arena.isInside()){
                indoorcheck.setSelected(true);
            }
            else {
                indoorcheck.setSelected(false);
            }
        }
        if(!updateArena.isManaged()){
            updateArena.setManaged(true);
            updateArena.setVisible(true);
        }

    }
    @FXML
    public void onSaveUpdateArenaButton(){
        Arena arena = arenas.getSelectionModel().getSelectedItem();
        arena.setName(updateName.getText());
        arena.getAddress().setStreet(updateStreet.getText());
        arena.getAddress().setHouseNo(Integer.parseInt(updateHouse.getText()));
        arena.getAddress().setPostalCode(Integer.parseInt(updatePostal.getText()));
        arena.getAddress().setCity(updateCity.getText());
        arena.setInside(indoorcheck.isSelected());
        viewManager.arenaDAO.updateArena(arena);

        if(updateArena.isManaged()){
            updateArena.setVisible(false);
            updateArena.setManaged(false);
        }
    }
    @FXML
    public void onLogoutButtonClick() throws IOException {
        Stage stage = (Stage) updateConcertArena.getScene().getWindow();
        viewManager.loadLoginPage(stage);
    }


    @FXML
    public void onDeleteArena(){
        Arena arena = arenas.getSelectionModel().getSelectedItem();
        viewManager.deleteArena(arena);
        observableList.remove(arena);
        arenas.refresh();
    }
}
