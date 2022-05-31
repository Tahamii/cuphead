package cupheadProject.Transition;

import cupheadProject.View.Components.Avatar;
import cupheadProject.View.Components.Boom;
import cupheadProject.View.Game;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BlinkAvatar extends Transition {
    private Boom blink;
    private double x;
    private double y;
    public BlinkAvatar(double x, double y, Boom blink){
        this.blink = blink;
        this.x = x;
        this.y = y;
        blink.setX(x);
        blink.setY(y);
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(1);
    }
    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 3);
        blink.setBackground("/cupheadProject/png/blink/" + frame + ".png");
//        blink.setFill(new ImagePattern(new Image(getClass().getResource("/cupheadProject/png/blink/" + frame + ".png").toExternalForm())));
        if(frame == 3){
            Avatar.getInstance().setX(x);
            Avatar.getInstance().setY(y);
            Game.getGamePane().getChildren().remove(blink);
        }
    }
}
