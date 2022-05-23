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

    User loggedInUser = null;

    public Button loginButton;
    @FXML
    private TextField password;
    @FXML
    private TextField username;

    public void register(MouseEvent mouseEvent) {
        System.out.println("+++" + username.getText());
        if(!RegisterMenuController.getInstance().handleRegister(username.getText(), password.getText())){
            showErrorMessage("this username is not available");
        }
    }

    public void login(MouseEvent mouseEvent){
        if(!LoginMenuController.getInstance().handleLogin(username.getText(), password.getText())){
            showErrorMessage("username or password is wrong");
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
        } else {
            password.setStyle("-fx-border-width: 0");
            loginButton.setDisable(false);
        }

    }

    public void skip(MouseEvent mouseEvent) {
        System.out.println("kdjkwlejfl");
    }

}
