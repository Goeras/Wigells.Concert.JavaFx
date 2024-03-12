package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField password;
    @FXML
    private TextField phoneNumber;


    @FXML
    protected void onHelloButtonClick() throws IOException {
        int phonenr = Integer.parseInt(phoneNumber.getText());
        ViewManager viewManager = new ViewManager();
        boolean loginSuccesfull = viewManager.customerLogIn(phonenr, password.getText());
        if(loginSuccesfull){
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            viewManager.loadCustomerPage(stage);
        }

    }
    @FXML
    public void onNewCustButtonClick() throws IOException{
        ViewManager viewManager = new ViewManager();
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        viewManager.loadNewCustPage(stage);
    }
    @FXML
    public void onAdminButtonClick() throws IOException{
        ViewManager viewManager = new ViewManager();
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        viewManager.loadAdminPage(stage);
    }
}