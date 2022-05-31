package cupheadProject.View.Components;

import cupheadProject.Transition.BlinkAvatar;
import cupheadProject.View.Game;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Avatar extends Rectangle {
    private static Avatar instance;
    private boolean isRocket;
    private double score = 0;
    private double life = 5;
    private double Injury = 1;
    private double Vulnerability = 1;

    public static Avatar getInstance() {
        if (instance == null)
            instance = new Avatar();
        return instance;
    }
    private Avatar() {
        super(10, 200, 109, 95);
        isRocket = false;
    }

    public void setBackground(String url) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(url).toExternalForm())));
    }

    public void setBackground(Image image){
        this.setFill(new ImagePattern(image));
    }

    public boolean isRocket() {
        return isRocket;
    }

    public void setRocket(boolean rocket) {
        isRocket = rocket;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
        if(Game.getInstance().getScore() != null)
            Game.getInstance().getScore().setText(String.valueOf(this.score));
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
        if(Game.getInstance().getAvatarLife() == null) {
            return;
        }
        if(Avatar.getInstance().getLife() <= 1){
            Game.getInstance().getAvatarLife().setFill(Color.RED);
        }
        Game.getInstance().getAvatarLife().setText(String.valueOf("life: " + this.life));
        RectangleNode blink = new RectangleNode(this.getX(), this.getY());
        Game.getGamePane().getChildren().add(blink);
        BlinkAvatar blinkAvatar = new BlinkAvatar(this.getX(), this.getY(), blink);
        blinkAvatar.play();
        this.setX(-250);
        this.setY(-250);
    }

    public double getInjury() {
        return Injury;
    }

    public void setInjury(double injury) {
        Injury = injury;
    }

    public double getVulnerability() {
        return Vulnerability;
    }

    public void setVulnerability(double vulnerability) {
        Vulnerability = vulnerability;
    }

    public Rectangle hasCollision(ArrayList<MiniBosses> blocks){
        for (Rectangle block : blocks) {
            if(block.getBoundsInParent().intersects(this.getLayoutBounds())){
//                System.out.println(block);
                return block;
            }
        }
        return null;
    }

    public boolean hasCollision(Rectangle block){
        if(block.getBoundsInParent().intersects(this.getLayoutBounds())){
            return true;
        }
        return false;
    }

    public void moveRight() {
        if (!hitRightWall())
            this.setX(this.getX() + 10);
    }

    public void moveLeft() {
        if (!hitLeftWall())
            this.setX(this.getX() - 10);
    }

    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 600;
    }

    public boolean hitLeftWall() {
        return this.getX() <= 0;
    }
}
