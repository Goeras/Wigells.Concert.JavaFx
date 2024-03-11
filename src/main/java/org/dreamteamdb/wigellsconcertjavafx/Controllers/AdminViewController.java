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
    private List<Concert> upcoming;

    public void initialize() {
        ViewManager viewManager = new ViewManager();
        //Ändra till inläsning från databas
        upcoming = new ArrayList<>();

        upcomingConcertList = FXCollections.observableList(upcoming);
        upcomingArtist.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        upcomingArena.setCellValueFactory(cellData -> viewManager.arenaProperty(cellData.getValue().getArena()));
        upcomingDate.setCellValueFactory(cellData -> viewManager.dateProperty(cellData.getValue().getDate()));
        upcomingIndoor.setCellValueFactory(cellData -> viewManager.indoorProperty(cellData.getValue().getArena()));
        upcomingPrice.setCellValueFactory(cellData -> viewManager.priceProperty(cellData.getValue().getTicketPrice()).asObject());
        upcomingMinAge.setCellValueFactory(cellData -> viewManager.ageProperty(cellData.getValue().getAgeLimit()).asObject());
        upComingConcerts.setItems(upcomingConcertList);


        List<Customer> customers = new ArrayList<>();
        ObservableList<Customer> customerList = FXCollections.observableList(customers);
        concertCustomers.setItems(customerList);
    }

    @FXML
    public void onSeeCustButton() {
        Concert concert = upComingConcerts.getSelectionModel().getSelectedItem();
        ObservableList customers = FXCollections.observableList(concert.getCustomerList());
        //Initiera alla customer-properties
        concertCustomers.setItems(customers);
        if (!concertCustomers.isVisible()) {
            concertCustomers.setVisible(true);
        }
    }
}
