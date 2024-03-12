package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dreamteamdb.wigellsconcertjavafx.CurrentUser;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Address;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;

import java.io.IOException;
import java.time.LocalDate;

public class ChangeInfoController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField street;
    @FXML
    private TextField houseNr;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField city;
    @FXML
    private TextField birthDate;
    private int loggedIn;
    Customer customer = CurrentUser.getInstance().getCurrentUser();
    Address address = customer.getAddress();
    public void initialize(){
        // loggedIn = CurrentUser.getInstance().getCurrentUser().getId(); //0;

        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        phoneNumber.setText(String.valueOf(customer.getPhoneNumber()));
        street.setText(address.getStreet());
        houseNr.setText(String.valueOf(address.getHouseNo()));
        postalCode.setText(String.valueOf(address.getPostalCode()));
        city.setText(address.getCity());

        /*ViewManager viewManager = new ViewManager();
        firstName.setText(viewManager.getCustomerFirstName(loggedIn));
        lastName.setText(viewManager.getCustomerLastName(loggedIn));
        street.setText(viewManager.getStreetAdress(loggedIn));
        houseNr.setText(viewManager.getHouseNumber(loggedIn));
        postalCode.setText(viewManager.getPostalCode(loggedIn));
        city.setText(viewManager.getCity(loggedIn));
        birthDate.setText(viewManager.getCustBirthDay(loggedIn));*/
    }
    @FXML
    public void onSaveButtonClick() {

        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setPhoneNumber(Integer.parseInt(phoneNumber.getText()));
        address.setStreet(street.getText());
        address.setHouseNo(Integer.parseInt(houseNr.getText()));
        address.setPostalCode(Integer.parseInt(postalCode.getText()));
        address.setCity(city.getText());

        ViewManager viewManager = new ViewManager(); // Här borde man kunna uppdatera Customer och Address objekten där uppe och direkt anropa tex CustomerDAO för att uppdatera
        //Lägg till alla andra villkor
        if (!firstName.getText().isBlank()) {
            viewManager.updateCustomer(customer, address);
            //viewManager.getNewInfo(firstName.getText(), lastName.getText(), street.getText(), Integer.parseInt(houseNr.getText()), Integer.parseInt(postalCode.getText()), city.getText(), loggedIn);
        }
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        Stage stage = (Stage) firstName.getScene().getWindow();
        ViewManager viewManager = new ViewManager();
        viewManager.loadCustomerPage(stage);
    }
}
