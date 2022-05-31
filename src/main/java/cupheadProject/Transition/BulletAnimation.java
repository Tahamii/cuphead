package cupheadProject.Transition;

import cupheadProject.View.Components.*;
import cupheadProject.View.Game;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class BulletAnimation extends Transition {
    private ArrayList<Bullet> bullets;
    Bullet bullet;
    AnchorPane pane;
    int gameHeight = Game.getGameHeight();
    int gameWidth = Game.getGameWidth();

    public BulletAnimation(ArrayList<Bullet>bullets, Bullet bullet, AnchorPane pane){
        this.pane = pane;
        this.bullet = bullet;
        this.bullets = bullets;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (BulletIcon.getInstance().isBullet()) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).setX(bullets.get(i).getX() + 10);
                if(Bullet.hasCollision(Boss.getInstance(), i)){
                    Boss.getInstance().setLife(Boss.getInstance().getLife() - 1 * Avatar.getInstance().getInjury());
                    Avatar.getInstance().setScore(Avatar.getInstance().getScore() + 1);
                    exit(i);
                }
                else if (bullets.get(i).getX() > gameWidth) {
                    exit(i);
                }
            }
            if (bullets.size() == 0) {
                this.stop();
            }
        }
        else{
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).setY(bullets.get(i).getY() + 10);
                if(Bullet.hasCollision(Boss.getInstance(), i)){
                    Boss.getInstance().setLife(Boss.getInstance().getLife() - 2 * Avatar.getInstance().getInjury());
                    Avatar.getInstance().setScore(Avatar.getInstance().getScore() + 2);
                    exit(i);
                }
                else if (bullets.get(i).getY() > gameHeight) {
                    exit(i);
                }
            }
            if (bullets.size() == 0) {
                this.stop();
            }
        }
    }

    private void exit(int i){
        pane.getChildren().remove(bullets.get(i));
        bullet.remove(bullets.get(i));
    }
}
