package cupheadProject.Transition;

import cupheadProject.View.Components.*;
import cupheadProject.View.Game;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class RocketAnimation extends Transition {
    private boolean first;
    Paint avatarBackground;
    AnchorPane pane;
    MiniBosses miniBoss;

    public RocketAnimation(AnchorPane pane){
        this.pane = pane;
        this.first = true;
        this.avatarBackground = Avatar.getInstance().getFill();
        RocketTimer.getInstance().setWidth(0);
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        if(first) {
            int frame = (int) Math.floor(v * 13);
            Avatar.getInstance().setBackground("/cupheadProject/png/rocket/" + frame + ".png");
            if (Avatar.getInstance().getX() < Game.getGameWidth())
                Avatar.getInstance().setX(Avatar.getInstance().getX() + 5);
            if(frame == 13)
                first = false;
        }
        miniBoss = (MiniBosses) Avatar.getInstance().hasCollision(MiniBosses.getMiniBosses());
        if(miniBoss != null){
            playBoom();
            pane.getChildren().remove(miniBoss);
            MiniBosses.remove(miniBoss);
            Avatar.getInstance().setScore(Avatar.getInstance().getScore() + 1);
            updateAvatar();
            this.stop();
        }
        if(Avatar.getInstance().hasCollision(Boss.getInstance())){
            playBoom();
            Boss.getInstance().setLife(Boss.getInstance().getLife() - 4 * Avatar.getInstance().getInjury());
            Avatar.getInstance().setScore(Avatar.getInstance().getScore() + 3);
            updateAvatar();
            this.stop();
        }
    }

    private void playBoom(){
        RectangleNode boom = new RectangleNode(Avatar.getInstance().getX(), Avatar.getInstance().getY());
        pane.getChildren().add(boom);
        BoomAnimation animation = new BoomAnimation(pane, boom);
        animation.play();
    }

    private void updateAvatar(){
        Avatar.getInstance().setFill(avatarBackground);
        Avatar.getInstance().setX(10);
        Avatar.getInstance().setY(200);
        Avatar.getInstance().setRocket(false);
    }
}
