package cupheadProject.Transition;

import cupheadProject.Enums.Images;
import cupheadProject.View.Components.Avatar;
import cupheadProject.View.Components.Boss;
import cupheadProject.View.Components.BossEgg;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BossEggAnimation extends Transition {
    boolean first;
    public BossEggAnimation(){
        first = true;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        if(first) {
            if(Boss.getInstance().isPhase3())
                BossEgg.getInstance().setX(Boss.getInstance().getX() + Boss.getInstance().getWidth() / 2);
            else
                BossEgg.getInstance().setX(Boss.getInstance().getX());
            BossEgg.getInstance().setY(Boss.getInstance().getY() + Boss.getInstance().getHeight() / 2);
            first = false;
        }

        if(Boss.getInstance().isPhase3()){
            BossEgg.getInstance().setBackground(Images.EGG.getUrl());
            BossEgg.getInstance().setY(BossEgg.getInstance().getY() - 10);
//            BossEgg.getInstance().setRotate(BossEgg.getInstance().getRotate() + 2);
            if (BossEgg.getInstance().getY() <= -10) {
                exit();
            } else if (BossEgg.getInstance().hasCollision(Avatar.getInstance())) {
                Avatar.getInstance().setLife(Avatar.getInstance().getLife() - 1 * Avatar.getInstance().getVulnerability());
                exit();
            }
        }
        else {
            BossEgg.getInstance().setBackground(Images.EGG.getUrl());
            BossEgg.getInstance().setX(BossEgg.getInstance().getX() - 10);
            BossEgg.getInstance().setRotate(BossEgg.getInstance().getRotate() + 2);
//          System.out.println("egg: " + BossEgg.getInstance().getX());

            if (BossEgg.getInstance().getX() <= -10) {
                exit();
            } else if (BossEgg.getInstance().hasCollision(Avatar.getInstance())) {
                Avatar.getInstance().setLife(Avatar.getInstance().getLife() - 1 * Avatar.getInstance().getVulnerability());
                exit();
            }
        }

    }

    private void exit(){
        BossEgg.getInstance().setFill(Color.TRANSPARENT);
        first = true;
        this.stop();
    }
}
