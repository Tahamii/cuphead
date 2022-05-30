package cupheadProject.View.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Avatar extends Rectangle {
    private static Avatar instance;
    private boolean isRocket;

    public static Avatar getInstance() {
        if (instance == null)
            instance = new Avatar();
        return instance;
    }
    private Avatar() {
        super(10, 200, 109, 95);
        isRocket = false;
    }

    public void setBackground(String url) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));
    }

    public void setBackground(Image image){
        this.setFill(new ImagePattern(image));
    }

    public boolean isRocket() {
        return isRocket;
    }

    public void setRocket(boolean rocket) {
        isRocket = rocket;
    }

    public Rectangle hasCollision(ArrayList<MiniBosses> blocks){
        for (Rectangle block : blocks) {
            if(block.getBoundsInParent().intersects(this.getLayoutBounds())){
//                System.out.println(block);
                return block;
            }
        }
        return null;
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
