package cupheadProject.View.Components;

import cupheadProject.Enums.Images;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Bullet extends Rectangle {
    private static ArrayList<Bullet> bullets = new ArrayList<>();

    public Bullet(double x, double y){
        super(x, y, 72, 15);
        this.fill();
        bullets.add(this);
    }

    private void fill(){
        this.setFill(new ImagePattern(new Image(getClass().getResource(Images.BULLET.getUrl()).toExternalForm())));
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void remove(Bullet bullet) {
        bullets.remove(bullet);
    }
}
