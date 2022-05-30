package cupheadProject.Enums;

public enum Sounds {
    SHOOT("/cupheadProject/music/shoot.wav"),
    GAME("/cupheadProject/music/game.mp3"),
    Menu("/cupheadProject/music/menu.mp3");


    private String url;

    private Sounds(String url){
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
