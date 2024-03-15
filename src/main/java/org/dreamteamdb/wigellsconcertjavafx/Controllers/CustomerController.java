package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dreamteamdb.wigellsconcertjavafx.CurrentUser;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;

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
    private Customer customer;



    public void initialize(){
        loggedIn = CurrentUser.getInstance().getCurrentUser().getId(); //0;
        customer = CurrentUser.getInstance().getCurrentUser();
        viewManager = new ViewManager();
        yourConcertList = viewManager.customerTickets(customer/*loggedIn*/);
        upcomingConcertList = viewManager.upcomingConcerts();

        name.setText(customer.getFirstName() + " " + customer.getLastName());
        address.setText(customer.getAddress().getStreet() + " " + customer.getAddress().getHouseNo());
        postAddress.setText(customer.getAddress().getPostalCode() + " " + customer.getAddress().getCity());
        birthdate.setText(customer.getBirthDate().toString());

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
        System.out.println(concert.getArtistName());
        viewManager.addNewConcert(concert, customer);
        yourTickets.refresh();
    }

    @FXML
    public void onCancelTicketsClick(){
        Concert concert = yourTickets.getSelectionModel().getSelectedItem();
        System.out.println(concert.getArtistName());
        viewManager.removeConcert(concert, customer);
        yourTickets.refresh();
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

}
