package cupheadProject.View.Components;

import cupheadProject.View.Game;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BossLife extends Rectangle {
    private static BossLife instance = null;
    private double number;

    public static BossLife getInstance(){
        if (instance == null)
            instance = new BossLife();
        return instance;
    }

    private BossLife(){
        super(600, 10, 100, 20);
        this.setFill(Color.rgb(234,68,68));
        this.number = 100;
    }

    public void update(){
        this.number = Boss.getInstance().getLife() * 100 / Boss.getTotalLife();
        this.setWidth(number);
        Game.getInstance().getBossLife().setText(String.valueOf(number) + "%");
    }

    public double getNumber() {
        return number;
    }
}
