package cupheadProject.View;

import cupheadProject.Enums.AvatarAddress;
import cupheadProject.Enums.Images;
import cupheadProject.Enums.Sounds;
import cupheadProject.Transition.*;
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
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

public class Game {
    private static Game instance;

    private static AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage mainStage;
    private AnimationTimer gameTimer;

    private Avatar avatar;

    private Random randomPositionGenerator;

    private PeriodicTask shoot;
    private MediaPlayer backgroundSoundPlayer;

    private Text bossLife;
    private Text avatarLife;
    private Text score;
    private Text timer;

    private boolean isUpKeyPressed;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isDownkeyPressed;
    private boolean isSpaseKeyPressed;

    private boolean isMute = false;

    private static final int GAME_WIDTH = 900;
    private static final int GAME_HEIGHT = 800;

    private Game() {
        initializeStage();
        createKeyListeners();
        createMedia();
        randomPositionGenerator = new Random();
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static AnchorPane getGamePane() {
        return gamePane;
    }

    public Text getBossLife() {
        return bossLife;
    }

    public Text getAvatarLife() {
        return avatarLife;
    }

    public Text getScore() {
        return score;
    }

    public void setIsMute(boolean isMute) {
        this.isMute = isMute;
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
                if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                    isUpKeyPressed = true;
                } else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;

                } else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                } else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                    isDownkeyPressed = true;
                } else if (event.getCode() == KeyCode.SPACE) {
                    shoot.start();
                    isSpaseKeyPressed = true;
                } else if (event.getCode() == KeyCode.TAB) {
                    BulletIcon.getInstance().switchIcon();
                } else if (event.getCode() == KeyCode.R) {
                    if (RocketTimer.getInstance().getWidth() == 100 && !Avatar.getInstance().isRocket()) {
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
                if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                    isUpKeyPressed = false;
                } else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;

                } else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;
                } else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                    isDownkeyPressed = false;
                } else if (event.getCode() == KeyCode.SPACE) {
                    isSpaseKeyPressed = false;
                }
            }
        });
    }

    private void createMedia() {
        Media backgroundSound = new Media(getClass().getResource(Sounds.GAME.getUrl()).toExternalForm());
        backgroundSoundPlayer = new MediaPlayer(backgroundSound);
        backgroundSoundPlayer.setCycleCount(INDEFINITE);
    }

    public void createGame(Stage mainStage) {
        this.mainStage = mainStage;
        this.mainStage.hide();
        createBackground();
        createAvatar();
        createGameElements();
        createGameLoop();
        gameStage.show();
        if (!isMute)
            backgroundSoundPlayer.play();
    }

    private void createBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource
                (Images.BACKGROUND.getUrl()).toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        gamePane.setBackground(new Background(backgroundImage));
    }

    private void createGameElements() {
        gamePane.getChildren().add(BulletIcon.getInstance());

        Rectangle timerFrame = new Rectangle(397, 7, 106, 26);
        gamePane.getChildren().add(timerFrame);
        gamePane.getChildren().add(RocketTimer.getInstance());
        Text rocketText = new Text(420, 22, "rocket time!");
        gamePane.getChildren().add(rocketText);

        gamePane.getChildren().add(BossEgg.getInstance());
        gamePane.getChildren().add(Boss.getInstance());
        BossAnimation bossFly = new BossAnimation();
        bossFly.play();

        Rectangle bossLifeFrame = new Rectangle(597, 7, 106, 26);
        gamePane.getChildren().add(bossLifeFrame);
        gamePane.getChildren().add(BossLife.getInstance());
        bossLife = new Text(605, 22, String.valueOf(BossLife.getInstance().getNumber()));
        gamePane.getChildren().add(bossLife);

        avatarLife = new Text(60, 22, "life: " + String.valueOf(Avatar.getInstance().getLife()));
        gamePane.getChildren().add(avatarLife);

        score = new Text(120, 22, "score: " + String.valueOf(Avatar.getInstance().getScore()));
        gamePane.getChildren().add(score);

        timer = new Text(200, 22, " ");
        gamePane.getChildren().add(timer);

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

        PeriodicTask rocketTime = new PeriodicTask(3) {
            @Override
            public void run() {
                if (RocketTimer.getInstance().getWidth() < 100)
                    RocketTimer.getInstance().setWidth(RocketTimer.getInstance().getWidth() + 20);
            }
        };
        rocketTime.start();

        shoot = new PeriodicTask(0.5) {
            @Override
            public void run() {
                if (isSpaseKeyPressed) {
                    Bullet bullet;
                    if (BulletIcon.isBullet()) {
                        if (!isMute) {
                            Media bulletSound = new Media(getClass().getResource(Sounds.SHOOT.getUrl()).toExternalForm());
                            MediaPlayer bulletSoundPlayer = new MediaPlayer(bulletSound);
                            bulletSoundPlayer.play();
                        }

                        bullet = new Bullet(avatar.getX() + avatar.getWidth() / 2,
                                avatar.getY() + avatar.getHeight() / 2, 72, 15);
                    } else {
                        bullet = new Bullet(avatar.getX() + avatar.getWidth() / 2,
                                avatar.getY() + avatar.getHeight() / 2, 36, 55);
                    }
                    gamePane.getChildren().add(bullet);
                    BulletAnimation animation = new BulletAnimation(Bullet.getBullets(), bullet, gamePane);
                    animation.play();
                }
            }
        };
    }

    private void createAvatar() {
        avatar = Avatar.getInstance();
        gamePane.getChildren().add(Avatar.getInstance());
    }

    private void createGameLoop() {
        LocalDateTime start = LocalDateTime.now();
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timer(start);

                if (!Avatar.getInstance().isRocket() && Avatar.getInstance().hasCollision(Boss.getInstance())) {
                    Avatar.getInstance().setLife(Avatar.getInstance().getLife()
                            - 1 * Avatar.getInstance().getVulnerability());
                }

                if (Avatar.getInstance().getLife() <= 0 ||
                        (Boss.getInstance().isPhase3() &&
                                Boss.getInstance().getLife() <= 0)) {
                    stopGame();
                }

                moveAvatar();
            }
        };
        gameTimer.start();
    }

    private void timer(LocalDateTime start) {
        LocalDateTime then = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(start);
        long minutes = tempDateTime.until(then, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);
        long seconds = tempDateTime.until(then, ChronoUnit.SECONDS);
        timer.setText(minutes + ":" + seconds);
    }

    private void moveAvatar() {

        if (isDownkeyPressed) {
            Avatar.getInstance().moveDown();
        }

        if (isUpKeyPressed) {
            Avatar.getInstance().moveUp();
        }

        if (isRightKeyPressed) {
            Avatar.getInstance().moveRight();
        }

        if (isLeftKeyPressed) {
            Avatar.getInstance().moveLeft();
        }

        if (!isSpaseKeyPressed) {
            shoot.stop();
        }

    }

    private void stopGame() {
        gameStage.close();
    }

}
