package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeInfoController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
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
    public void initialize(){
        loggedIn = 0;
        ViewManager viewManager = new ViewManager();
        firstName.setText(viewManager.getCustomerFirstName(loggedIn));
        lastName.setText(viewManager.getCustomerLastName(loggedIn));
        street.setText(viewManager.getStreetAdress(loggedIn));
        houseNr.setText(viewManager.getHouseNumber(loggedIn));
        postalCode.setText(viewManager.getPostalCode(loggedIn));
        city.setText(viewManager.getCity(loggedIn));
        birthDate.setText(viewManager.getCustBirthDay(loggedIn));
    }
    @FXML
    public void onSaveButtonClick() {
        ViewManager viewManager = new ViewManager();
        //Lägg till alla andra villkor
        if (!firstName.getText().isBlank()) {
            viewManager.getNewInfo(firstName.getText(), lastName.getText(), street.getText(), Integer.parseInt(houseNr.getText()), Integer.parseInt(postalCode.getText()), city.getText(), loggedIn);
        }
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        Stage stage = (Stage) firstName.getScene().getWindow();
        ViewManager viewManager = new ViewManager();
        viewManager.loadCustomerPage(stage);
    }
}
