package cupheadProject.Transition;

import cupheadProject.View.Components.RectangleNode;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BoomAnimation extends Transition {
    private AnchorPane pane;
    private RectangleNode image;

    public BoomAnimation(AnchorPane pane, RectangleNode image) {
        this.image = image;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 11);
        image.setBackground("/cupheadProject/png/boom/" + frame + ".png");
        if (frame == 11) {
            pane.getChildren().remove(image);
            this.stop();
        }
    }
}
