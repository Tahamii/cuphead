package cupheadProject.Transition;

import cupheadProject.App;
import cupheadProject.View.Components.Avatar;
import cupheadProject.View.Components.Boom;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BoomAnimation extends Transition {
    private AnchorPane pane;
    private Boom image;

    public BoomAnimation(AnchorPane pane, Boom image){
//        pane.getChildren().add(image);
        this.image = image;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 11);
        image.setBackground("/cupheadProject/png/boom/" + frame + ".png");
        if(frame == 11){
            pane.getChildren().remove(image);
            this.stop();
        }
    }
}
