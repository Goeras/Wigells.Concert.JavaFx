package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dreamteamdb.wigellsconcertjavafx.CurrentUser;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Address;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;

import java.io.IOException;
import java.time.LocalDate;

public class ChangeInfoController {
    @FXML
    private Label newCustomerException;

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

        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        phoneNumber.setText(String.valueOf(customer.getPhoneNumber()));
        street.setText(address.getStreet());
        houseNr.setText(String.valueOf(address.getHouseNo()));
        postalCode.setText(String.valueOf(address.getPostalCode()));
        city.setText(address.getCity());
    }
    @FXML
    public void onSaveButtonClick() {
        ViewManager viewManager = new ViewManager();
        boolean validPhoneNumber = false;
        try {
            validPhoneNumber = viewManager.validatePhoneNumber(Integer.parseInt(phoneNumber.getText()));
            int phoneInt = Integer.parseInt(phoneNumber.getText());
            int hounseInt = Integer.parseInt(houseNr.getText());
            int postInt = Integer.parseInt(postalCode.getText());
            customer.setFirstName(firstName.getText());
            customer.setLastName(lastName.getText());
            customer.setPhoneNumber(phoneInt);
            address.setStreet(street.getText());
            address.setHouseNo(hounseInt);
            address.setPostalCode(postInt);
            address.setCity(city.getText());

            if(!validPhoneNumber){
                newCustomerException.setText("Telefonnummer används redan");
                newCustomerException.setManaged(true);
                newCustomerException.setVisible(true);
            }

            if (!firstName.getText().isBlank() && validPhoneNumber) {
                viewManager.updateCustomer(customer, address);
                newCustomerException.setText("Uppgifter sparade");
            }

        }
        catch(NumberFormatException nfe){
            nfe.printStackTrace();
            newCustomerException.setText("Kontrollera inmatningarna");
            newCustomerException.setManaged(true);
            newCustomerException.setVisible(true);
        }

    }
    @FXML
    private void onBackButtonClick() throws IOException {
        Stage stage = (Stage) firstName.getScene().getWindow();
        ViewManager viewManager = new ViewManager();
        viewManager.loadCustomerPage(stage);
    }

    @FXML
    private void onDeleteClick() throws IOException {
        ViewManager viewManager = new ViewManager();
        viewManager.deleteCustomer(customer);
        Stage stage = (Stage) firstName.getScene().getWindow();
        viewManager.loadLoginPage(stage);
    }
}
