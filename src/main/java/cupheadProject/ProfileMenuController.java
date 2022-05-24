package cupheadProject;

import cupheadProject.Controller.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProfileMenuController extends Menu {

    @FXML
    private TextField newPassword;
    @FXML
    private TextField newUsername;

    public void changeUser(MouseEvent mouseEvent) {
        switchToPage("UserSettings");
    }

    public void logout() {
        UserController.getInstance().logout();
        switchToPage("FirstPage");
    }

    public void deleteAccount() {
        UserController.getInstance().deleteAccount();
        switchToPage("FirstPage");
    }

    public void updateInfo() {
        if (!UserController.getInstance().isUsernameNew(newUsername.getText())) {
            showErrorMessage("write down a new username");
        } else {
            UserController.getInstance().updateInfo(newUsername.getText(), newPassword.getText());
            switchToPage("ProfileMenu");
        }
    }

    public void chooseAvatar(MouseEvent mouseEvent) {
        switchToPage("ChooseAvatar");
    }
}
