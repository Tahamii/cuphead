package cupheadProject.Enums;

public enum Images {
    MINIBOSS("/cupheadProject/png/miniBosses/0.png"),
    CHECK("/cupheadProject/png/checkmark.png"),
    CROSS("/cupheadProject/png/cross.png"),
    BUTTOM("/cupheadProject/png/button.png"),
    PRESSEDBUTTOM("/cupheadProject/png/pressed_button.png"),
    BULLET("/cupheadProject/png/bullet.png"),
    BOMB("/cupheadProject/png/bomb.png"),
    BULLETICON("/cupheadProject/png/bullet_icon.png"),
    BOMBICON("/cupheadProject/png/bomb_icon.png");

    private String url;

    private Images(String url){
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
