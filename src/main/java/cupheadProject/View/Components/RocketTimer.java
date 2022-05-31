package cupheadProject.View.Components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RocketTimer extends Rectangle {
    private static RocketTimer instance = null;

    private RocketTimer() {
        super(400, 10, 100, 20);
        this.setFill(Color.rgb(80, 90, 244));
    }

    public static RocketTimer getInstance() {
        if (instance == null)
            instance = new RocketTimer();
        return instance;
    }
}
