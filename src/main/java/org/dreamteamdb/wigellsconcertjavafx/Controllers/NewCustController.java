package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Address;
import org.dreamteamdb.wigellsconcertjavafx.Entitys.Customer;

import java.io.IOException;
import java.time.LocalDate;

public class NewCustController {
    @FXML
    private Label newCustomerException;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField street;
    @FXML
    private TextField housenumber;
    @FXML
    private TextField postalcode;
    @FXML
    private TextField city;
    @FXML
    private TextField birthday;
    @FXML
    private PasswordField passWord;
    @FXML
    private TextField phoneNumber;

    public void initialize(){}
    @FXML
    public void onBackButton() throws IOException {
        Stage stage = (Stage) firstName.getScene().getWindow();
        ViewManager viewManager = new ViewManager();
        viewManager.loadLoginPage(stage);
    }
    @FXML
    public void onSaveButtonClick() throws IOException {
        ViewManager viewManager = new ViewManager();
        LocalDate localDate = LocalDate.parse(birthday.getText());
        int housenr = Integer.parseInt(housenumber.getText());
        int phonenr = Integer.parseInt(phoneNumber.getText());
        if(!viewManager.validatePhoneNumber(phonenr)){
            newCustomerException.setManaged(true);
            newCustomerException.setVisible(true);

            return;
        }
        int postcode = Integer.parseInt(postalcode.getText());
        viewManager.newCustomer(firstName.getText(), lastName.getText(), phonenr, street.getText(), housenr, postcode, city.getText(), localDate, passWord.getText());
        onBackButton();

    }
}
