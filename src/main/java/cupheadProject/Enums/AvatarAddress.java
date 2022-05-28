package cupheadProject.Enums;

public enum AvatarAddress {
    RED("/cupheadProject/png/red.png"),
    BLUE("/cupheadProject/png/blue.png"),
    MALE("/cupheadProject/png/male.png");

    private String url;

    private AvatarAddress(String url){
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
