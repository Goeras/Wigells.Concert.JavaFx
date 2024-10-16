package org.dreamteamdb.wigellsconcertjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private Label welcomeText;
    @FXML
    private PasswordField password;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Label loginexception;


    public void initialize(){
    }


    @FXML
    protected void onHelloButtonClick() throws IOException {
        int phonenr = Integer.parseInt(phoneNumber.getText());
        ViewManager viewManager = new ViewManager();
        boolean loginSuccesfull = viewManager.customerLogIn(phonenr, password.getText());
        if(loginSuccesfull){
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            viewManager.loadCustomerPage(stage);
        }
        else{
            password.setText("");
            phoneNumber.setText("");
            loginexception.setManaged(true);
            loginexception.setVisible(true);
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
        viewManager.loadAdminLogInPage(stage);
    }
    public Scene getScene(){
       Scene scene = welcomeText.getScene();
        return scene;
    }
}