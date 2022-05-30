package cupheadProject.Transition;

import cupheadProject.Enums.AvatarAddress;
import cupheadProject.View.Components.Avatar;
import cupheadProject.View.Components.Boom;
import cupheadProject.View.Components.MiniBosses;
import cupheadProject.View.Components.RocketTimer;
import cupheadProject.View.Game;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
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
//            Boom boom = new Boom(Avatar.getInstance().getX(), Avatar.getInstance().getY());
            Boom boom = Boom.getInstance(Avatar.getInstance().getX(), Avatar.getInstance().getY());
            pane.getChildren().add(boom);
            BoomAnimation animation = new BoomAnimation(pane, boom);
            animation.play();
//            pane.getChildren().remove(image);
            pane.getChildren().remove(miniBoss);
            MiniBosses.remove(miniBoss);
            Avatar.getInstance().setFill(avatarBackground);
            Avatar.getInstance().setX(10);
            Avatar.getInstance().setY(200);
            Avatar.getInstance().setRocket(false);
            this.stop();
        }
    }
}
