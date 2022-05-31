package cupheadProject.View;

import cupheadProject.App;
import cupheadProject.Controller.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MainMenuController extends Menu{
    @FXML
    public Button profile;

    public void chooseAvatar(MouseEvent mouseEvent) {
        ChooseAvatar chooseAvatar = new ChooseAvatar();
        App.setScene(chooseAvatar.getAvatarScene());
    }

    public void profileMenu() {
        if(UserController.getInstance().getLoggedInUser() == null){
            profile.setDisable(true);
            showErrorMessage("you should login first");
        }
        else {
            switchToPage("ProfileMenu");
        }
    }

    public void setting(){
        switchToPage("setting");
    }

    public void exit(){
        App.getMainStage().close();
    }
}
