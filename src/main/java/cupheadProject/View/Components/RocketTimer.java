package cupheadProject.View.Components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RocketTimer extends Rectangle {
    private static RocketTimer instance = null;

    public static RocketTimer getInstance(){
        if (instance == null)
            instance = new RocketTimer();
        return instance;
    }

    private RocketTimer(){
        super(400, 10, 100, 20);
        this.setFill(Color.BLUE);
    }
}
