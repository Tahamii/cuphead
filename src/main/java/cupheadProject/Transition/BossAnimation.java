package cupheadProject.Transition;

import cupheadProject.View.Components.Boss;
import cupheadProject.View.Components.BossLife;
import cupheadProject.View.Game;
import javafx.animation.Transition;
import javafx.util.Duration;

public class BossAnimation extends Transition {
    private BossShoot bossShoot;
    private boolean down;
    private boolean up;

    public BossAnimation() {
        down = true;
        up = false;
        bossShoot = new BossShoot();
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        if (BossLife.getInstance().getNumber() > 50) {
            int frame = (int) Math.floor(v * 5);
            Boss.getInstance().setBackground("/cupheadProject/png/boss_fly/" + frame + ".png");

            if (down) {
                if (Boss.getInstance().moveDownPossible())
                    Boss.getInstance().moveDown();
                else {
                    down = false;
                    up = true;
                }
            }
            if (up) {
                if (Boss.getInstance().getY() == 100) {
                    if (bossShoot.getStatus() == Status.STOPPED)
                        bossShoot.play();
                }
                if (Boss.getInstance().moveUpPossible()) {
                    Boss.getInstance().moveUp();
                } else {
                    up = false;
                    down = true;
                }
            }
        } else {
            this.stop();
            bossShoot.stop();
            BossPhase2Animation animation = new BossPhase2Animation();
            animation.play();
        }
    }
}
