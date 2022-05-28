package cupheadProject.Transition;

import cupheadProject.View.Components.Bullet;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class BulletAnimation extends Transition {
    private ArrayList<Bullet> bullets;
    Bullet bullet;
    AnchorPane pane;

    public BulletAnimation(ArrayList<Bullet>bullets, Bullet bullet, AnchorPane pane){
        this.pane = pane;
        this.bullet = bullet;
        this.bullets = bullets;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).setX(bullets.get(i).getX() + 10);
            if(bullets.get(i).getX() > 900 ){
                pane.getChildren().remove(bullets.get(i));
                bullet.remove(bullets.get(i));
            }
        }
        if(bullets.size() == 0){
            this.stop();
        }
    }
}
