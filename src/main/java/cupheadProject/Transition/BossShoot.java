package cupheadProject.Transition;

import cupheadProject.View.Components.Boss;
import cupheadProject.View.Components.BossEgg;
import cupheadProject.View.Components.BossLife;
import javafx.animation.Transition;
import javafx.util.Duration;

public class BossShoot extends Transition {
    BossEggAnimation bossEggAnimation;
    public BossShoot(){
        bossEggAnimation = new BossEggAnimation();
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(1);
    }
    @Override
    protected void interpolate(double v) {
        if(Boss.getInstance().isPhase3()){
            int frame = (int) Math.floor(v * 31);
            Boss.getInstance().setBackground("/cupheadProject/png/boss_shoot_3/" + frame + ".png");
            if (frame == 18) {
                if (bossEggAnimation.getStatus() == Status.STOPPED)
                    bossEggAnimation.play();
            }
        }
        else if (BossLife.getInstance().getNumber() > 50){
            int frame = (int) Math.floor(v * 11);
            Boss.getInstance().setBackground("/cupheadProject/png/boss_shoot/" + frame + ".png");
            if (frame == 6) {
                if (bossEggAnimation.getStatus() == Status.STOPPED)
                    bossEggAnimation.play();
            }
        }
        else {
            int frame = (int) Math.floor(v * 20);
            Boss.getInstance().setBackground("/cupheadProject/png/boss_shoot_2/" + frame + ".png");
            if (frame == 12) {
//                System.out.println("in boss shoot  " + BossEgg.getInstance().getX());
                if (bossEggAnimation.getStatus() == Status.STOPPED)
                    bossEggAnimation.play();
            }
        }
    }
}
