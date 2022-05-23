package cupheadProject;

import cupheadProject.Controller.LoginMenuController;
import cupheadProject.Controller.RegisterMenuController;
import cupheadProject.Model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class FirstPageController {

    String loggedInUsername = null;

    public Button loginButton;
    public Button registerButton;
    @FXML
    private TextField password;
    @FXML
    private TextField username;


    public void register(MouseEvent mouseEvent) {
        System.out.println("+++" + username.getText());
        if(!RegisterMenuController.getInstance().handleRegister(username.getText(), password.getText())){
            showErrorMessage("this username is not available");
        }
        else{
            switchToGame();
        }
    }

    public void login(MouseEvent mouseEvent){
        if(!LoginMenuController.getInstance().handleLogin(username.getText(), password.getText())){
            showErrorMessage("username or password is wrong");
        }
        else{
            loggedInUsername = username.getText();
            switchToGame();
        }
    }

    public static void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("DevLaunch Dialog");
        alert.setHeaderText("An error has been encountered");
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void typePassword(KeyEvent keyEvent) {

        int strength = password.getText().length();
        if (strength < 9) {
            password.setStyle("-fx-border-color: #ff0066;");
            loginButton.setDisable(true);
            registerButton.setDisable(true);
        } else {
            password.setStyle("-fx-border-width: 0");
            loginButton.setDisable(false);
            registerButton.setDisable(false);
        }

    }

    public void skip(MouseEvent mouseEvent) {
        switchToGame();
    }

    private void switchToGame() {
        try {
            App.setRoot("GamePage");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
