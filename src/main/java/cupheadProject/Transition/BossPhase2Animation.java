package cupheadProject.Transition;

import cupheadProject.View.Components.Boss;
import cupheadProject.View.Components.BossEgg;
import cupheadProject.View.Components.BossLife;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class BossPhase2Animation extends Transition {
    Random randomNumber;
    int currentRandom;

    public BossPhase2Animation() {
        randomNumber = new Random();
        currentRandom = randomNumber.nextInt(5);
        Boss.getInstance().setBackground("/cupheadProject/png/boss_shoot_2/0.png");
        Boss.getInstance().setHeight(251);
        Boss.getInstance().setWidth(173);
        this.setCycleDuration(Duration.millis(10000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        switch (currentRandom) {
            case 0:
                Boss.getInstance().moveLeft();
                if (!Boss.getInstance().moveLeftPossible() || randomNumber.nextInt(100) == 5) {
                    currentRandom = randomNumber.nextInt(5);
                }
                break;
            case 1:
                Boss.getInstance().moveUp();
                if (!Boss.getInstance().moveUpPossible() || randomNumber.nextInt(100) == 5) {
                    currentRandom = randomNumber.nextInt(5);
                }
                break;
            case 2:
                Boss.getInstance().moveRight();
                if (!Boss.getInstance().moveRightPossible() || randomNumber.nextInt(100) == 5) {
                    currentRandom = randomNumber.nextInt(5);
                }
                break;
            case 3:
                Boss.getInstance().moveDown();
                if (!Boss.getInstance().moveDownPossible() || randomNumber.nextInt(100) == 5) {
                    currentRandom = randomNumber.nextInt(5);
                }
                break;
            case 4:
                if (BossEgg.getInstance().getFill() == Color.TRANSPARENT) {
                    BossShoot bossShoot = new BossShoot();
                    bossShoot.play();
                }
                currentRandom = randomNumber.nextInt(5);
        }
        if (BossLife.getInstance().getNumber() <= 0) {
            Boss.getInstance().setPhase3(true);
            Boss.getInstance().setLife(Boss.getTotalLife());
            BossPhase3Animation animation = new BossPhase3Animation();
            animation.play();
            this.stop();
        }
    }
}
