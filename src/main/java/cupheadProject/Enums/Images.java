package cupheadProject.Enums;

public enum Images {
    MINIBOSS("/cupheadProject/png/miniBosses/0.png"),
    CHECK("/cupheadProject/png/checkmark.png"),
    CROSS("/cupheadProject/png/cross.png"),
    BUTTOM("/cupheadProject/png/button.png"),
    PRESSEDBUTTOM("/cupheadProject/png/pressed_button.png"),
    BULLET("/cupheadProject/png/bullet.png");


    private String url;

    private Images(String url){
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
