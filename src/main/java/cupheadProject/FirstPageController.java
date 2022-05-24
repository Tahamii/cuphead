package cupheadProject;

import cupheadProject.Controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FirstPageController extends Menu {


    public Button loginButton;
    public Button registerButton;
    @FXML
    private TextField password;
    @FXML
    private TextField username;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;


    public void register(MouseEvent mouseEvent) {
        System.out.println("+++" + username.getText());
        if(!UserController.getInstance().handleRegister(username.getText(), password.getText())){
            showErrorMessage("this username is not available");
        }
    }

    public void login(MouseEvent mouseEvent){
        if(!UserController.getInstance().handleLogin(username.getText(), password.getText())){
            showErrorMessage("username or password is wrong");
        }
        else{
            this.switchToPage("ProfileMenu");
        }
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
//        switchToGame();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, 100, 100 );
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        App.setMainStage(mainStage);
    }



//    private void createBackGround(){
//        Image backgroundImage = new Image("sf");
//        BackgroundImage background = new
//    }

}
