package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;

import java.util.ArrayList;
import java.util.List;

public class AdminViewController {
    @FXML
    private TableView<Customer> concertCustomers;

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

    public void initialize() {
        ViewManager viewManager = new ViewManager();

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




        List<Customer> customers = viewManager.customerDAO.getAllCustomers();
        ObservableList<Customer> customerList = FXCollections.observableList(customers);
        concertCustomers.setItems(customerList);
    }

    @FXML
    public void onSeeCustButton() {
        Concert concert = upComingConcerts.getSelectionModel().getSelectedItem();
        ObservableList customers = FXCollections.observableList(concert.getCustomerList());

        concertCustomers.setItems(customers);
        if (!concertCustomers.isVisible()) {
            concertCustomers.setVisible(true);
        }
    }
}
