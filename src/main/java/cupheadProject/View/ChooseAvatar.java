package cupheadProject.View;

import cupheadProject.AvatarAddress;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChooseAvatar {
    private AnchorPane avatarPane;
    private static Scene avatarScene;
    HBox hBox = new HBox();

    public ChooseAvatar() {
        avatarPane = new AnchorPane();
        avatarScene = new Scene(avatarPane, 600, 400);
        addElements();
    }

    public void addElements() {
        VBox vBox = new VBox();
        vBox.setLayoutX(65);
        vBox.setLayoutY(50);
        vBox.setSpacing(50);
        Text title = new Text("choose your avatar");
        title.setStyle("-fx-font-size: 50; -fx-font-weight: bold;");
        vBox.getChildren().add(title);
        addImage(AvatarAddress.RED.getUrl());
        addImage(AvatarAddress.BLUE.getUrl());
        hBox.setSpacing(60);
        vBox.getChildren().add(hBox);
        avatarPane.getChildren().add(vBox);
        Button button1 = new Button("start");
        Button button2 = new Button("ramdom avatar");
        avatarPane.getChildren().add(button1);
//        avatarPane.getChildren().add(button2);
    }

    public Scene getAvatarScene() {
        return avatarScene;
    }

    private void addImage(String url) {
        Image carImage = new Image(getClass().getResource(url).toExternalForm());
        ImageView cImage = new ImageView(carImage);
        cImage.setFitWidth(140);
        cImage.setFitHeight(120);
        VBox vBox = new VBox();
        vBox.getChildren().add(cImage);
        Button button = new Button();
        vBox.getChildren().add(button);
        hBox.getChildren().add(vBox);
    }
}
