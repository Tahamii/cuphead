package cupheadProject.View;

import cupheadProject.Enums.AvatarAddress;
import cupheadProject.Enums.Images;
import cupheadProject.Transition.BulletAnimation;
import cupheadProject.Transition.MiniBossAnimation;
import cupheadProject.View.Components.Bullet;
import cupheadProject.View.Components.MiniBosses;
import cupheadProject.View.Components.PeriodicTask;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Random;

public class Game {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage mainStage;
    private AnimationTimer gameTimer;

    private ImageView avatar;

    Random randomPositionGenerator;

    PeriodicTask shoot;

    private boolean isUpKeyPressed;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isDownkeyPressed;
    private boolean isSpaseKeyPressed;


    private static final int GAME_WIDTH = 900;
    private static final int GAME_HEIGHT = 500;

    public Game() {
        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP){
                    isUpKeyPressed = true;
                }
                else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;

                }
                else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                }
                else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN){
                    isDownkeyPressed = true;
                }
                else if (event.getCode() == KeyCode.SPACE){
                    shoot.start();
                    isSpaseKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP){
                    isUpKeyPressed = false;
                }
                else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;

                }
                else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;
                }
                else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN){
                    isDownkeyPressed = false;
                }
                else if (event.getCode() == KeyCode.SPACE){
                    isSpaseKeyPressed = false;
                }
            }
        });
    }


    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createGame(Stage mainStage, AvatarAddress avatar) {
        this.mainStage = mainStage;
        this.mainStage.hide();
//        createBackground();
        createAvatar(avatar);
        createGameElements();
        createGameLoop();
        gameStage.show();
    }

    private void createGameElements(){
//        miniBosses = new ImageView[3];
//        for (int i = 0; i < miniBosses.length; i++) {
//            miniBosses[i] = new ImageView(new Image(getClass().getResource(Images.MINIBOSS.getUrl()).toExternalForm()));
//            miniBosses[i].setLayoutY(100);
//            gamePane.getChildren().add(miniBosses[i]);
//        }

        PeriodicTask miniBossLoop = new PeriodicTask(15) {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    MiniBosses miniBosses = new MiniBosses(900 - 200 * i, 50);
                    gamePane.getChildren().add(miniBosses);
                }
                MiniBossAnimation miniBossAnimation = new MiniBossAnimation(MiniBosses.getMiniBosses(), gamePane);
                miniBossAnimation.play();
            }
        };
        miniBossLoop.start();

        shoot = new PeriodicTask(1) {
            @Override
            public void run() {
                if(isSpaseKeyPressed){
                    Bullet bullet = new Bullet(avatar.getLayoutX(), avatar.getLayoutY());
                    gamePane.getChildren().add(bullet);
                    BulletAnimation animation = new BulletAnimation(Bullet.getBullets(), bullet, gamePane);
                    animation.play();
//                    System.out.println("********************************************************");
//                    for (Bullet b:Bullet.getBullets()) {
//                        System.out.println(b);
//                    }
                }
            }
        };
    }

    private void createAvatar(AvatarAddress choosenAvatar){
        this.avatar = new ImageView(new Image(getClass().getResource(choosenAvatar.getUrl()).toExternalForm()));
        this.avatar.setLayoutX(10);
        this.avatar.setLayoutY(GAME_HEIGHT/2 - 50);
        avatar.setFitWidth(109);
        avatar.setFitHeight(95);
        gamePane.getChildren().add(avatar);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
//                moveBackground();
//                checkIfElementAreBehindTheShipAndRelocated();
//                checkIfElementsCollide();

                moveAvatar();

            }
        };
        gameTimer.start();
    }

    private void moveAvatar() {

        if(isDownkeyPressed) {
            if(avatar.getLayoutY() < GAME_HEIGHT - avatar.getFitHeight())
                avatar.setLayoutY(avatar.getLayoutY() + 10);
        }

        if(isUpKeyPressed){
            if(avatar.getLayoutY() > 0){
                avatar.setLayoutY(avatar.getLayoutY() - 10);
            }
        }

        if(isRightKeyPressed){
            if(avatar.getLayoutX() < GAME_WIDTH - avatar.getFitWidth()) {
                avatar.setLayoutX(avatar.getLayoutX() + 10);
            }
        }

        if(isLeftKeyPressed){
            if(avatar.getLayoutX() > 0){
                avatar.setLayoutX(avatar.getLayoutX() - 10);
            }
        }

        if(!isSpaseKeyPressed){
            shoot.stop();
        }

    }
}
