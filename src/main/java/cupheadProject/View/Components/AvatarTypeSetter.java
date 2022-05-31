package cupheadProject.View.Components;

import cupheadProject.Enums.AvatarAddress;
import cupheadProject.Enums.Images;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AvatarTypeSetter extends VBox {
    private ImageView circleImage;
    private ImageView avatarImage;

    private AvatarAddress avatar;

    private boolean isChoosen;

    public AvatarTypeSetter(AvatarAddress avatar) {
        circleImage = new ImageView(new Image(getClass().getResource(Images.CROSS.getUrl()).toExternalForm()));
        avatarImage = new ImageView(new Image(getClass().getResource(avatar.getUrl()).toExternalForm()));
        this.avatar = avatar;
        isChoosen = false;
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.maxWidth(350);
        this.getChildren().add(avatarImage);
        this.getChildren().add(circleImage);
    }

    public AvatarAddress getAvatar() {
        return avatar;
    }

    public boolean getIsChoosen() {
        return isChoosen;
    }

    public void setIsChoosen(boolean isCircleChoosen) {
        this.isChoosen = isCircleChoosen;
        String imageToSet = this.isChoosen ? Images.CHECK.getUrl() : Images.CROSS.getUrl();
        circleImage.setImage(new Image(getClass().getResource(imageToSet).toExternalForm()));
    }
}