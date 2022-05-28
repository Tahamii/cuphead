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
        super(x, y, 158, 109);
        this.life = 2;
        this.fill();
        miniBosses.add(this);
    }

    public static void remove(MiniBosses miniBoss) {
        miniBosses.remove(miniBoss);
    }

    private void fill(){
        this.setFill(new ImagePattern(new Image(getClass().getResource(Images.MINIBOSS.getUrl()).toExternalForm())));
    }

    public static ArrayList<MiniBosses> getMiniBosses() {
        return miniBosses;
    }

    public boolean hasCollision(ArrayList<Rectangle> blocks) {
        for (Rectangle block : blocks) {
            if(block.getBoundsInParent().intersects(this.getLayoutBounds())){
                return true;
            }
        }
        return false;
    }
}
