package cupheadProject.View;

import cupheadProject.App;
import cupheadProject.Enums.Sounds;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

public class Menu {
    private static MediaPlayer menuSound;

    public static void playMenuSound() {
        Media media = new Media(App.class.getResource(Sounds.Menu.getUrl()).toExternalForm());
        menuSound = new MediaPlayer(media);
        menuSound.setCycleCount(INDEFINITE);
        menuSound.play();
    }

    public static void stopMenuSound() { menuSound.stop(); }

    public static MediaPlayer getMenuSound() { return menuSound; }

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
