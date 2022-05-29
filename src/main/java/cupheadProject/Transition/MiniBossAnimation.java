package cupheadProject.Transition;

import cupheadProject.View.Components.Bullet;
import cupheadProject.View.Components.MiniBosses;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class MiniBossAnimation extends Transition {
    private ArrayList<MiniBosses> miniBosses;
    AnchorPane pane;

    public MiniBossAnimation(ArrayList<MiniBosses> miniBosses, AnchorPane pane) {
        this.pane = pane;
        this.miniBosses = miniBosses;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 3);
        for (int i = 0; i < miniBosses.size(); i++) {
//            System.out.println(i); "/cupheadProject/png/miniBoss.png //"/cupheadProject/png/miniBosses/0.png"
            miniBosses.get(i).fill("/cupheadProject/png/miniBosses/" + frame + ".png");

            miniBosses.get(i).setX(miniBosses.get(i).getX() - 2);

            if(miniBosses.get(i).hasCollision(Bullet.getBullets()) != null){
                miniBosses.get(i).setLife( miniBosses.get(i).getLife() - 1);
                pane.getChildren().remove(miniBosses.get(i).hasCollision(Bullet.getBullets()));
                Bullet.remove((Bullet) miniBosses.get(i).hasCollision(Bullet.getBullets()));
            }

            if (miniBosses.get(i).getX() < -158 || miniBosses.get(i).getLife() <= 0) {
                pane.getChildren().remove(miniBosses.get(i));
                MiniBosses.remove(miniBosses.get(i));
            }
        }
        if (miniBosses.size() == 0) {
            this.stop();
        }
    }
}
