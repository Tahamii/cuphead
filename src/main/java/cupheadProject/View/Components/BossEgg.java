package cupheadProject.View.Components;

import cupheadProject.App;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BossEgg extends Rectangle {
    private static BossEgg instance = null;

    public static BossEgg getInstance() {
        if (instance == null)
            instance = new BossEgg();
        return instance;
    }

    private BossEgg(){
        super(500, 200, 90.6, 77.3);
        this.setFill(Color.TRANSPARENT);
    }

    public void setBackground(String url) {
        this.setFill(new ImagePattern(new Image(App.class.getResource(url).toExternalForm())));
    }

    public boolean hasCollision(Rectangle avatar){
        if(avatar.getBoundsInParent().intersects(this.getLayoutBounds())){
            return true;
        }
        return false;
    }
}
