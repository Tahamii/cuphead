package cupheadProject;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class firstPageController {

    public Button registerButton;
    @FXML
    private TextField password;
    @FXML
    private TextField username;

    public void register(MouseEvent mouseEvent) {
        System.out.println(username.getText());

    }


    public void type(KeyEvent keyEvent) {

        int strength = password.getText().length();
        if (strength < 10){
            password.setStyle("-fx-border-color: #ff0066;");
            registerButton.setDisable(true);
        }else{
            password.setStyle("-fx-border-width: 0");
            registerButton.setDisable(false);

        }

    }

}
