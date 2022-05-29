package cupheadProject.View;

import cupheadProject.App;
import cupheadProject.Enums.AvatarAddress;
import cupheadProject.View.Components.Avatar;
import cupheadProject.View.Components.AvatarTypeSetter;
import cupheadProject.View.Components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ChooseAvatar {
    private static Scene avatarScene;
    private BorderPane avatarPane;
    private AvatarAddress choosenAvatar;

    public ChooseAvatar() {
        avatarPane = new BorderPane();
        avatarScene = new Scene(avatarPane, 600, 400);
        addElements();
    }

    public void addElements() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        Text title = new Text("choose your avatar");
        title.setStyle("-fx-font-size: 50; -fx-font-weight: bold;");

        vBox.getChildren().add(title);

        vBox.getChildren().add(createAvatarsToChoose());
        vBox.getChildren().add(createRandomButton());
        vBox.getChildren().add(createStartButton());
        vBox.getChildren().add(createUploadButton());

        avatarPane.setCenter(vBox);
    }


    public Scene getAvatarScene() {
        return avatarScene;
    }

    private HBox createAvatarsToChoose() {
        HBox hBox = new HBox();
        hBox.setSpacing(60);
        hBox.setAlignment(Pos.CENTER);
        List<AvatarTypeSetter> avatarList = new ArrayList<>();
        for (AvatarAddress avatar : AvatarAddress.values()) {
            AvatarTypeSetter avatarToPick = new AvatarTypeSetter(avatar);
            avatarList.add(avatarToPick);
            hBox.getChildren().add(avatarToPick);
            avatarToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    for (AvatarTypeSetter avatar : avatarList) {
                        avatar.setIsChoosen(false);
                    }
                    avatarToPick.setIsChoosen(true);
                    choosenAvatar = avatarToPick.getAvatar();

                }
            });
        }
        return hBox;
    }

    private GameButton createStartButton() {
        GameButton startButton = new GameButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (choosenAvatar != null) {
                    Avatar.getInstance().setBackground(choosenAvatar.getUrl());
                    startGame();
                }

            }
        });

        return startButton;
    }

    private GameButton createRandomButton() {
        GameButton randomButton = new GameButton("random");
        randomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Random random = new Random();
                int i = random.nextInt(AvatarAddress.values().length);
                choosenAvatar = AvatarAddress.values()[i];
                Avatar.getInstance().setBackground(choosenAvatar.getUrl());
                startGame();
            }
        });
        return randomButton;
    }

    private GameButton createUploadButton() {
        GameButton uploadButton = new GameButton("upload");
        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("png Files", "*.png"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if(selectedFile != null){
                    Image image = new Image(selectedFile.toURI().toString());
//                    ImageView view = new ImageView(image);
                    Avatar.getInstance().setBackground(image);
                    startGame();
//                    avatarPane.getChildren().add(view);
                }
            }
        });
        return uploadButton;
    }

    private void startGame() {
        System.out.println("started");
        Game gameManager = new Game();
        Stage stage = new Stage();
        App.setMainStage(stage);
    }


//    private void addImage(String url) {
//        Image image = new Image(getClass().getResource(url).toExternalForm());
//        ImageView view = new ImageView(image);
//        view.setFitWidth(140);
//        view.setFitHeight(120);
//        VBox vBox = new VBox();
//        vBox.getChildren().add(view);
//        Button button = new Button();
//        vBox.getChildren().add(button);
//        hBox.setAlignment(Pos.CENTER);
//        hBox.getChildren().add(vBox);
//    }
}
