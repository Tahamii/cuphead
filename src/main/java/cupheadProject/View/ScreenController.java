package cupheadProject.View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.util.HashMap;

public class ScreenController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene scene;

    public ScreenController() {

    }

    public static void setScene(Scene scene) {
        ScreenController.scene = scene;
    }

    protected void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        scene.setRoot( screenMap.get(name) );
    }
}
