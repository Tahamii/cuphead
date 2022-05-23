package cupheadProject;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import cupheadProject.Components.Avatar;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(App.class.getResource("fxml/FirstPage.fxml"));
        scene = new Scene(pane);
        ScreenController.setScene(scene);
        ScreenController screenController = new ScreenController();
        screenController.addScreen("FirstPage", FXMLLoader.load(App.class.getResource("fxml/FirstPage.fxml")));
        screenController.addScreen("GamePage", FXMLLoader.load(App.class.getResource("fxml/GamePage.fxml")));
        screenController.activate("FirstPage");
//        Pane pane = FXMLLoader.load(App.class.getResource("fxml/FirstPage.fxml"));
//        scene = new Scene(pane);
        //GamePageController gamePageController = new GamePageController();
        //Avatar cupHead = gamePageController.createAvatar();

//        Image carImage = new Image(getClass().getResource("/cupheadProject/png/red.png").toExternalForm());
//        ImageView cImage = new ImageView(carImage);
//        cImage.setFitWidth(50);
//        cImage.setFitHeight(60);
//        pane.getChildren().add(cImage);

//        Avatar cupHead = create();
//        pane.getChildren().add(cupHead);
        stage.setScene(scene);
        stage.setTitle("Brick Breaker");
//        pane.getChildren().get(0).requestFocus();
        stage.show();
    }

    static void setRoot(String fxml) throws Exception {
        if(fxml.equals("GamePage")){
            System.out.println("gamepage");
        }
//        Avatar cupHead = Avatar.getInstance();
        Parent parent = loadFXML(fxml);
//        parent.getChildrenUnmodifiable().add(cupHead);
        scene.setRoot(parent);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public Avatar create() {
        Avatar avatar = Avatar.getInstance();
//        avatar.setOnKeyPressed();
        avatar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName(); //Get Pressed Key Name!
                System.out.println(keyName);
                switch (keyName) {
                    case "Left":
                        avatar.moveLeft();
                        break;
                    case "Right":
                        avatar.moveRight();
                        break;
                }
            }
        });
        return avatar;
    }

    public static void main(String[] args) {
        launch(args);
    }


}