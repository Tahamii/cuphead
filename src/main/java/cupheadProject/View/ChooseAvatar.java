package cupheadProject.View;

import cupheadProject.App;
import cupheadProject.Enums.AvatarAddress;
import cupheadProject.View.Components.AvatarTypeSetter;
import cupheadProject.View.Components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ChooseAvatar {
    private BorderPane avatarPane;
    private static Scene avatarScene;

    private AvatarAddress choosenAvatar;
    HBox hBox = new HBox();

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

//        Button button1 = new Button("start");
        Button button2 = new Button("random avatar");
//        button2.setStyle("-fx-background-image: url('/cupheadProject/png/pressed_button.png');");
//        button2.setLayoutX(50);
//        button2.setLayoutY(50);
        vBox.getChildren().add(createStartButton());
        vBox.getChildren().add(button2);

        avatarPane.setCenter(vBox);
//        avatarPane.getChildren().add(button2);
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
                    System.out.println("started");
                    Game gameManager = new Game();
                    Stage stage = new Stage();
                    App.setMainStage(stage, choosenAvatar);
                }

            }
        });

        return startButton;
    }

    public Scene getAvatarScene() {
        return avatarScene;
    }

    private void addImage(String url) {
        Image image = new Image(getClass().getResource(url).toExternalForm());
        ImageView view = new ImageView(image);
        view.setFitWidth(140);
        view.setFitHeight(120);
        VBox vBox = new VBox();
        vBox.getChildren().add(view);
        Button button = new Button();
        vBox.getChildren().add(button);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(vBox);
    }
}
