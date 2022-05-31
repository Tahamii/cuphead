package cupheadProject.View.Components;

import cupheadProject.App;
import cupheadProject.View.Game;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle {
    private static Boss instance = null;
    private static final double TOTAL_LIFE = 40;
    private double life = TOTAL_LIFE;
    private boolean phase3 = false;

    public static Boss getInstance() {
        if (instance == null)
            instance = new Boss();
        return instance;
    }

    private Boss(){
        super(500, 100, 397.5, 253.5);
        this.setBackground("/cupheadProject/png/boss_fly/0.png");
    }

    public void setBackground(String url) {
        this.setFill(new ImagePattern(new Image(App.class.getResource(url).toExternalForm())));
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
        BossLife.getInstance().update();
    }

    public static double getTotalLife(){
        return TOTAL_LIFE;
    }

    public boolean isPhase3() {
        return phase3;
    }

    public void setPhase3(boolean phase3) {
        this.phase3 = phase3;
    }

    public void moveRight(){
        if(moveRightPossible())
            this.setX(this.getX() + 5);
    }

    public void moveLeft(){
        if(moveLeftPossible())
            this.setX(this.getX() - 5);
    }

    public void moveUp(){
        if(moveUpPossible())
            this.setY(this.getY() - 5);
    }

    public void moveDown(){
        if(moveDownPossible())
            this.setY(this.getY() + 5);
    }

    public boolean moveRightPossible(){
        if(this.getX() < Game.getGameWidth() - this.getWidth())
            return true;
        return false;
    }

    public boolean moveLeftPossible(){
        if(this.getX() > 0)
            return true;
        return false;
    }

    public boolean moveDownPossible(){
        if (this.getY() < Game.getGameHeight() - this.getHeight())
            return true;
        return false;
    }

    public boolean moveUpPossible(){
        if(this.getY() > 0)
            return true;
        return false;
    }
}
