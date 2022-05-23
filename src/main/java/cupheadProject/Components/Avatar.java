package cupheadProject.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Avatar extends Rectangle {
    private static Avatar instance;

    public static Avatar getInstance() {
        if (instance == null)
            instance = new Avatar();
        return instance;
    }
    private Avatar() {
        super(275, 320, 50, 15);
//        this.getStyleClass().add("board");
    }

    public void setBackground(String url) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));
    }

    public void moveRight() {
        if (!hitRightWall())
            this.setX(this.getX() + 10);
    }

    public void moveLeft() {
        if (!hitLeftWall())
            this.setX(this.getX() - 10);
    }

    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 600;
    }

    public boolean hitLeftWall() {
        return this.getX() <= 0;
    }
}
