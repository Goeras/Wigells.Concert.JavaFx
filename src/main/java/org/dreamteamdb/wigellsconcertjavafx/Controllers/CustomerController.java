package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;

import java.io.IOException;

public class CustomerController {
    @FXML
    private TableView<Concert> yourTickets;
    @FXML
    private TableColumn<Concert, String> yourDate;
    @FXML
    private TableColumn<Concert, String> yourArtist;
    @FXML
    private TableColumn<Concert, String> yourArena;
    @FXML
    private TableColumn<Concert, String> yourIndoor;
    @FXML
    private TableColumn<Concert, Double> yourPrice;
    @FXML
    private TableColumn<Concert, Integer> yourMinAge;
    @FXML
    private TableView<Concert> upcomingTickets;
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
    @FXML
    private Label name;
    @FXML
    private Label address;
    @FXML
    private Label postAddress;
    @FXML
    private Label birthdate;

    private ObservableList<Concert> yourConcertList;
    private ObservableList<Concert> upcomingConcertList;
    private int loggedIn;
    private ViewManager viewManager;



    public void initialize(){
        loggedIn = 0;
        viewManager = new ViewManager();
        yourConcertList = viewManager.customerTickets(loggedIn);
        upcomingConcertList = viewManager.upcomingConcerts();

        name.setText(viewManager.getCustomerFirstName(loggedIn) + " " + viewManager.getCustomerLastName(loggedIn));
        address.setText(viewManager.getStreetAdress(loggedIn) + " " + viewManager.getHouseNumber(loggedIn));
        postAddress.setText(viewManager.getPostalCode(loggedIn) + " " + viewManager.getCity(loggedIn));
        birthdate.setText(viewManager.getCustBirthDay(loggedIn));

        yourArtist.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        yourArena.setCellValueFactory(cellData -> viewManager.arenaProperty(cellData.getValue().getArena()));
        yourDate.setCellValueFactory(cellData -> viewManager.dateProperty(cellData.getValue().getDate()));
        yourIndoor.setCellValueFactory(cellData -> viewManager.indoorProperty(cellData.getValue().getArena()));
        yourPrice.setCellValueFactory(cellData -> viewManager.priceProperty(cellData.getValue().getTicketPrice()).asObject());
        yourMinAge.setCellValueFactory(cellData -> viewManager.ageProperty(cellData.getValue().getAgeLimit()).asObject());
        yourTickets.setItems(yourConcertList);

        upcomingArtist.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        upcomingArena.setCellValueFactory(cellData -> viewManager.arenaProperty(cellData.getValue().getArena()));
        upcomingDate.setCellValueFactory(cellData -> viewManager.dateProperty(cellData.getValue().getDate()));
        upcomingIndoor.setCellValueFactory(cellData -> viewManager.indoorProperty(cellData.getValue().getArena()));
        upcomingPrice.setCellValueFactory(cellData -> viewManager.priceProperty(cellData.getValue().getTicketPrice()).asObject());
        upcomingMinAge.setCellValueFactory(cellData -> viewManager.ageProperty(cellData.getValue().getAgeLimit()).asObject());
        upcomingTickets.setItems(upcomingConcertList);


    }
    @FXML
    public void onBuyTicketsClick(){
        Concert concert = upcomingTickets.getSelectionModel().getSelectedItem();
        viewManager.addNewConcert(concert, loggedIn);
    }
    @FXML
    public void onLogoutButtonClick() throws IOException {
        Stage stage = (Stage) name.getScene().getWindow();
        viewManager.loadLoginPage(stage);
    }
    @FXML
    public void onChangeInfoClick() throws IOException {
        Stage stage = (Stage) name.getScene().getWindow();
        viewManager.loadChangeInfoPage(stage);
    }
    public void onDeleteButton(){
        //Skapa deletemetod i viewManager
    }
}
