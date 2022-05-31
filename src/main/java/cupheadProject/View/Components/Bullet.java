package cupheadProject.View.Components;

import cupheadProject.Enums.Images;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Bullet extends Rectangle {
    private static ArrayList<Bullet> bullets = new ArrayList<>();

    public Bullet(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.fill(w);
        bullets.add(this);
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public static void remove(Bullet bullet) {
        bullets.remove(bullet);
    }

    public static boolean hasCollision(Rectangle boss, int i) {
        if (boss.getBoundsInParent().intersects(bullets.get(i).getLayoutBounds())) {
            return true;
        }
        return false;
    }

    private void fill(double w) {
        if (w == 72)
            this.setFill(new ImagePattern(new Image(getClass().getResource(Images.BULLET.getUrl()).toExternalForm())));
        else
            this.setFill(new ImagePattern(new Image(getClass().getResource(Images.BOMB.getUrl()).toExternalForm())));
    }
}
