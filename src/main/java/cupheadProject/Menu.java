package cupheadProject;

import cupheadProject.Model.User;
import javafx.scene.control.Alert;

public class Menu {

    public void switchToPage(String pageName) {
        try {
            App.setRoot(pageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("DevLaunch Dialog");
        alert.setHeaderText("An error has been encountered");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
