package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Arena;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Concert;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;
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

    public AdminViewController(){}

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

        arenaName.setCellValueFactory(cellData -> viewManager.toStringProperty(cellData.getValue().getName()));
        arenaStreet.setCellValueFactory(cellData -> viewManager.toStringProperty(cellData.getValue().getAddress().getStreet() + " " + cellData.getValue().getAddress().getHouseNo()));
        arenaPost.setCellValueFactory(cellData -> viewManager.toStringProperty(cellData.getValue().getAddress().getPostalCode() + " " + cellData.getValue().getAddress().getCity()));
        arenaIndoor.setCellValueFactory(cellData -> viewManager.indoorProperty(cellData.getValue()));


        List<Arena> arenasList = viewManager.arenaDAO.readAllArenas();
        ObservableList observableList = FXCollections.observableList(arenasList);
        arenas.setItems(observableList);

        List<Customer> customers = viewManager.customerDAO.getAllCustomers();
        ObservableList<Customer> customerList = FXCollections.observableList(customers);
        concertCustomers.setItems(customerList);
    }

    @FXML
    public void onSeeCustButton() {
      Concert concert = upComingConcerts.getSelectionModel().getSelectedItem();
      ViewManager viewManager = new ViewManager();
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
    }
}
