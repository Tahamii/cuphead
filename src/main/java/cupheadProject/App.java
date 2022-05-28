package cupheadProject;

import cupheadProject.Enums.AvatarAddress;
import cupheadProject.View.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(App.class.getResource("fxml/FirstPage.fxml"));
        scene = new Scene(pane);
        mainStage = new Stage();
        mainStage.setScene(scene);
        stage = mainStage;
        stage.show();
    }

    public static void setRoot(String fxml) throws Exception {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage, AvatarAddress avatar) {
//        App.mainStage.hide();
        Game gamePageController = new Game();
        gamePageController.createGame(App.mainStage, avatar);
    }

    public static void setScene(Scene scene) {
        App.scene = scene;
        mainStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }


}