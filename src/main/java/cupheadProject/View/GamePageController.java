package cupheadProject.View;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Random;

public class GamePageController {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage mainStage;

    Random randomPositionGenerator;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;

    public GamePageController() {
        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;

                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                }

            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;

                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;

                }

            }
        });
    }


    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, 600, 400);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createGame(Stage mainStage) {
        this.mainStage = mainStage;
        this.mainStage.hide();
//        createBackground();
//        createShip(choosenShip);
//        createGameElements(choosenShip);
//        createGameLoop();
        gameStage.show();
    }
}
