package cupheadProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

//    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        //URL address_login_page = new URL(App.class.getResource("fxml/loginPage.fxml").toString());
        Pane pane = FXMLLoader.load(App.class.getResource("fxml/loginPage.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }

//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }

    public static void main(String[] args) {
        launch(args);
    }

}