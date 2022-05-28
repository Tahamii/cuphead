package cupheadProject.Transition;

import cupheadProject.View.Components.Bullet;
import cupheadProject.View.Components.MiniBosses;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class MiniBossAnimation extends Transition {
    private ArrayList<MiniBosses> miniBosses ;
    AnchorPane pane;

    public MiniBossAnimation(ArrayList<MiniBosses> miniBosses, AnchorPane pane){
        this.pane = pane;
        this.miniBosses = miniBosses;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        for(int i = 0; i < miniBosses.size(); i++){
            miniBosses.get(i).setX(miniBosses.get(i).getX() - 5);
            if(miniBosses.get(i).getX() < -158 || miniBosses.get(i).hasCollision( (ArrayList<Rectangle>) Bullet.getBullets() ){
                pane.getChildren().remove(miniBosses.get(i));
                MiniBosses.remove(miniBosses.get(i));
            }
        }
        if(miniBosses.size() == 0){
            this.stop();
        }
    }
}
