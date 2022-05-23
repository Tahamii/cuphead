package cupheadProject;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import cupheadProject.Components.Avatar;

public class GamePageController {

    public Avatar createAvatar() {
        Avatar avatar = Avatar.getInstance();
//        avatar.setOnKeyPressed();
        avatar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName(); //Get Pressed Key Name!
                System.out.println(keyName);
                switch (keyName) {
                    case "Left":
                        avatar.moveLeft();
                        break;
                    case "Right":
                        avatar.moveRight();
                        break;
                }
            }
        });
        return avatar;
    }

    void initialize(){

    }

}
