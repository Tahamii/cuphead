package cupheadProject.Transition;

import cupheadProject.View.Components.Boss;
import cupheadProject.View.Components.BossEgg;
import cupheadProject.View.Game;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class BossPhase3Animation extends Transition {
    private Random random;
    private BossShoot bossShoot;
    private boolean right;
    private boolean left;

    public BossPhase3Animation() {
        Boss.getInstance().setBackground("/cupheadProject/png/boss_shoot_3/0.png");
        Boss.getInstance().setHeight(161);
        Boss.getInstance().setWidth(448);
        Boss.getInstance().setY(Game.getGameHeight() - 200);
        bossShoot = new BossShoot();
        random = new Random();
        right = true;
        left = false;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (right) {
            if (Boss.getInstance().moveRightPossible())
                Boss.getInstance().moveRight();
            else {
                right = false;
                left = true;
            }
        }
        if (left) {
            if (BossEgg.getInstance().getFill() == Color.TRANSPARENT && random.nextBoolean()) {
                if (bossShoot.getStatus() == Status.STOPPED)
                    bossShoot.play();
            }
            if (Boss.getInstance().moveLeftPossible()) {
                Boss.getInstance().moveLeft();
            } else {
                left = false;
                right = true;
            }
        }
    }
}
