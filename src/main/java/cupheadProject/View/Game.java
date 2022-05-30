package cupheadProject.View;

import cupheadProject.Enums.AvatarAddress;
import cupheadProject.Enums.Images;
import cupheadProject.Enums.Sounds;
import cupheadProject.Transition.BulletAnimation;
import cupheadProject.Transition.MiniBossAnimation;
import cupheadProject.Transition.RocketAnimation;
import cupheadProject.View.Components.*;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

public class Game {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage mainStage;
    private AnimationTimer gameTimer;

    private Avatar avatar;

    Random randomPositionGenerator;

    PeriodicTask shoot;
    MediaPlayer backgroundSoundPlayer;
    MediaPlayer bulletSoundPlayer;

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
        createMedia();
        randomPositionGenerator = new Random();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
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
                else if(event.getCode() == KeyCode.TAB){
                    BulletIcon.getInstance().switchIcon();
                }
                else if(event.getCode() == KeyCode.R){
                    if(RocketTimer.getInstance().getWidth() == 100 && !Avatar.getInstance().isRocket()) {
                        Avatar.getInstance().setRocket(true);
                        RocketAnimation animation = new RocketAnimation(gamePane);
                        animation.play();
                    }
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

    private void createMedia(){
        Media backgroundSound = new Media(getClass().getResource(Sounds.GAME.getUrl()).toExternalForm());
        backgroundSoundPlayer = new MediaPlayer(backgroundSound);
        backgroundSoundPlayer.setCycleCount(INDEFINITE);

//        Media bulletSound = new Media(getClass().getResource(Sounds.SHOOT.getUrl()).toExternalForm());
//        bulletSoundPlayer = new MediaPlayer(bulletSound);
    }

    public void createGame(Stage mainStage) {
        this.mainStage = mainStage;
        this.mainStage.hide();
//        createBackground();
        createAvatar();
        createGameElements();
        createGameLoop();
        gameStage.show();
        backgroundSoundPlayer.play();
    }

    private void createGameElements(){
        gamePane.getChildren().add(BulletIcon.getInstance());
        Rectangle timerFrame = new Rectangle(397, 7, 106, 26);
        gamePane.getChildren().add(timerFrame);
        gamePane.getChildren().add(RocketTimer.getInstance());
//        Text rocketText = new Text("rocket timer");
//        rocketText.setLayoutX(420);
//        rocketText.setY(12);
//        gamePane.getChildren().add(rocketText);


//        Button b = new GameButton("test");
//        b.setLayoutX(50);
//        b.setLayoutY(50);
//        gamePane.getChildren().add(b);
//        miniBosses = new ImageView[3];
//        for (int i = 0; i < miniBosses.length; i++) {
//            miniBosses[i] = new ImageView(new Image(getClass().getResource(Images.MINIBOSS.getUrl()).toExternalForm()));
//            miniBosses[i].setLayoutY(100);
//            gamePane.getChildren().add(miniBosses[i]);
//        }

        PeriodicTask miniBossLoop = new PeriodicTask(15) {
            @Override
            public void run() {
                int y = randomPositionGenerator.nextInt(GAME_HEIGHT - 70);
                for (int i = 0; i < 3; i++) {
                    MiniBosses miniBosses = new MiniBosses(900 - 100 * i, y);
                    gamePane.getChildren().add(miniBosses);
                }
                MiniBossAnimation miniBossAnimation = new MiniBossAnimation(MiniBosses.getMiniBosses(), gamePane);
                miniBossAnimation.play();
            }
        };
        miniBossLoop.start();

        PeriodicTask RocketTime = new PeriodicTask(3) {
            @Override
            public void run() {
                if(RocketTimer.getInstance().getWidth() < 100)
                    RocketTimer.getInstance().setWidth(RocketTimer.getInstance().getWidth() + 20);
            }
        };
        RocketTime.start();

        shoot = new PeriodicTask(0.5) {
            @Override
            public void run() {
                if(isSpaseKeyPressed){
                    Bullet bullet;
                    if(BulletIcon.isBullet()) {
                        Media bulletSound = new Media(getClass().getResource(Sounds.SHOOT.getUrl()).toExternalForm());
                        MediaPlayer bulletSoundPlayer = new MediaPlayer(bulletSound);
                        bulletSoundPlayer.play();

                        bullet = new Bullet(avatar.getX() + avatar.getWidth() / 2,
                                avatar.getY() + avatar.getHeight() / 2, 72, 15);
                    }
                    else {
                        bullet = new Bullet(avatar.getX() + avatar.getWidth() / 2,
                                avatar.getY() + avatar.getHeight() / 2, 36, 55);
                    }
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

    private void createAvatar(){
        avatar = Avatar.getInstance();
        gamePane.getChildren().add(Avatar.getInstance());
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
            if(avatar.getY() < GAME_HEIGHT - avatar.getHeight())
                avatar.setY(avatar.getY() + 10);
        }

        if(isUpKeyPressed){
            if(avatar.getY() > 0){
                avatar.setY(avatar.getY() - 10);
            }
        }

        if(isRightKeyPressed){
            if(avatar.getX() < GAME_WIDTH - avatar.getWidth()) {
                avatar.setX(avatar.getX() + 10);
            }
        }

        if(isLeftKeyPressed){
            if(avatar.getX() > 0){
                avatar.setX(avatar.getX() - 10);
            }
        }

        if(!isSpaseKeyPressed){
            shoot.stop();
        }

    }
    public static int getGameHeight(){
        return GAME_HEIGHT;
    }
    public static int getGameWidth(){
        return GAME_WIDTH;
    }

    public AnchorPane getGamePane() {
        return gamePane;
    }
}
