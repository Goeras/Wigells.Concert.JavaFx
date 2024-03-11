package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        ViewManager viewManager = new ViewManager();
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        viewManager.loadCustomerPage(stage);

    }
    @FXML
    public void onNewCustButtonClick() throws IOException{
        ViewManager viewManager = new ViewManager();
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        viewManager.loadNewCustPage(stage);
    }
}