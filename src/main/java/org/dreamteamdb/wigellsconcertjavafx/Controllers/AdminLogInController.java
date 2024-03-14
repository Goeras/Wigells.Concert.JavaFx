package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLogInController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField adminId;
    @FXML
    private TextField password;

    @FXML
    protected void onAdminLogInButtonClick() throws IOException {
        int admId = Integer.parseInt(adminId.getText());
        ViewManager viewManager = new ViewManager();
        boolean loginSuccesfull = viewManager.adminLogIn(admId, password.getText());
        if(loginSuccesfull){
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            viewManager.loadAdminPage(stage);
        }

    }
    @FXML
    public void onLogoutButtonClick() throws IOException {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        ViewManager viewManager = new ViewManager();
        viewManager.loadLoginPage(stage);
    }
}

