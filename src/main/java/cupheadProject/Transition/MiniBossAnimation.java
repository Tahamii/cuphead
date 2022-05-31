package cupheadProject.Transition;

import cupheadProject.View.Components.*;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
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
            miniBosses.get(i).fill("/cupheadProject/png/miniBosses/" + frame + ".png");

            miniBosses.get(i).setX(miniBosses.get(i).getX() - 2);

            if(miniBosses.get(i).hasCollision(Bullet.getBullets()) != null){
                if(BulletIcon.isBullet())
                    miniBosses.get(i).setLife( miniBosses.get(i).getLife() - 1);
                else {
                    miniBosses.get(i).setLife(miniBosses.get(i).getLife() - 2);
                    Avatar.getInstance().setScore(Avatar.getInstance().getScore() + 1);
                }
                pane.getChildren().remove(miniBosses.get(i).hasCollision(Bullet.getBullets()));
                Bullet.remove((Bullet) miniBosses.get(i).hasCollision(Bullet.getBullets()));
            }

            if(!Avatar.getInstance().isRocket() && miniBosses.get(i).hasCollision(Avatar.getInstance())){
                miniBosses.get(i).setLife( miniBosses.get(i).getLife() - 2);
                Avatar.getInstance().setLife(Avatar.getInstance().getLife() - 1 * Avatar.getInstance().getVulnerability());
            }

            if (miniBosses.get(i).getX() < -158 ) {
                pane.getChildren().remove(miniBosses.get(i));
                MiniBosses.remove(miniBosses.get(i));
            }
            else if(miniBosses.get(i).getLife() <= 0){
                RectangleNode boom = new RectangleNode(miniBosses.get(i).getX(), miniBosses.get(i).getY());
////              RectangleNode boom = RectangleNode.getInstance(Avatar.getInstance().getX(), Avatar.getInstance().getY());
                pane.getChildren().add(boom);
                BoomAnimation animation = new BoomAnimation(pane, boom);
                animation.play();
                pane.getChildren().remove(miniBosses.get(i));
                MiniBosses.remove(miniBosses.get(i));
            }
        }
        if (miniBosses.size() == 0) {
            this.stop();
        }
    }
}
