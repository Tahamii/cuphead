package cupheadProject.View;

import cupheadProject.View.Components.Avatar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

public class SettingController extends Menu {
    @FXML
    public Button mute;

    public void levelOne() {
        Avatar.getInstance().setLife(10);
        Avatar.getInstance().setVulnerability(0.5);
        Avatar.getInstance().setInjury(1.5);
        mainMenu();
    }

    public void levelTwo() {
        Avatar.getInstance().setLife(5);
        Avatar.getInstance().setVulnerability(1);
        Avatar.getInstance().setInjury(1);
        mainMenu();
    }

    public void levelThree() {
        Avatar.getInstance().setLife(2);
        Avatar.getInstance().setVulnerability(1.5);
        Avatar.getInstance().setInjury(0.5);
        mainMenu();
    }

    public void mute() {
        if (Menu.getMenuSound().getStatus() == MediaPlayer.Status.STOPPED) {
            mute.setText("mute");
            Menu.playMenuSound();
            Game.getInstance().setIsMute(false);
        } else {
            mute.setText("unmute");
            Menu.stopMenuSound();
            Game.getInstance().setIsMute(true);
        }

    }

    public void noColor() {

    }

    public void mainMenu() {
        switchToPage("MainMenu");
    }
}
