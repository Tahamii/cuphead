package cupheadProject.View.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class RectangleNode extends Rectangle {

    public RectangleNode(double x, double y) {
        super(x, y, 100, 100);
        this.setBackground("/cupheadProject/png/boom/0.png");
    }

    public void setBackground(String url) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));
    }
}
