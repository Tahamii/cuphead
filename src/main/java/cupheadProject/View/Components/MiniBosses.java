package cupheadProject.View.Components;

import cupheadProject.Enums.Images;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MiniBosses extends Rectangle {
    private static ArrayList<MiniBosses> miniBosses= new ArrayList<>();
    private int life;

    public MiniBosses(double x, double y){
        super(x, y, 105, 73);
        this.life = 2;
        this.fill(Images.MINIBOSS.getUrl());
        miniBosses.add(this);
    }

    public static void remove(MiniBosses miniBoss) {
        miniBosses.remove(miniBoss);
    }

    public static ArrayList<MiniBosses> getMiniBosses() {
        return miniBosses;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Rectangle hasCollision(ArrayList<Bullet> blocks) {
        for (Rectangle block : blocks) {
            if(block.getBoundsInParent().intersects(this.getLayoutBounds())){
                return block;
            }
        }
        return null;
    }

    public boolean hasCollision(Avatar avatar) {
        if(((Rectangle) avatar).getBoundsInParent().intersects(this.getLayoutBounds())){
            return true;
        }
        return false;
    }

    public void fill(String url){
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));
    }
}
