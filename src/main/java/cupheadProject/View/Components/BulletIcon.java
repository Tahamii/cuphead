package cupheadProject.View.Components;

import cupheadProject.Enums.Images;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BulletIcon extends Rectangle {
    private static BulletIcon instance = null;
    private static boolean bullet;

    private BulletIcon() {
        super(0, 0, 40, 40);
        this.bullet = true;
        this.setBackground(Images.BULLETICON.getUrl());
    }

    public static BulletIcon getInstance() {
        if (instance == null)
            instance = new BulletIcon();
        return instance;
    }

    public static boolean isBullet() {
        return bullet;
    }

    public void switchIcon() {
        if (bullet) {
            this.setBackground(Images.BOMBICON.getUrl());
            bullet = false;
        } else {
            this.setBackground(Images.BULLETICON.getUrl());
            bullet = true;
        }
    }

    public void setBackground(String url) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));
    }
}
