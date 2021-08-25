package xyz.auriium.openmineplatform.api.interfaceable.user;

public class PlatformTitle {

    private final String title;
    private final String subtitle;

    private final int fadein;
    private final int stay;
    private final int fadeout;

    PlatformTitle(String title, String subtitle, int fadein, int stay, int fadeout) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadein = fadein;
        this.stay = stay;
        this.fadeout = fadeout;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getFadein() {
        return fadein;
    }

    public int getStay() {
        return stay;
    }

    public int getFadeout() {
        return fadeout;
    }

    public static PlatformTitle of(String title, String subtitle) {
        return new PlatformTitle(title, subtitle, 0, 20, 10);
    }

    public static PlatformTitle of(String title, String subtitle, int stay, int fadeout) {
        return new PlatformTitle(title, subtitle, 0, stay, fadeout);
    }

    public static PlatformTitle of(String title, String subtitle, int fadein, int stay, int fadeout) {
        return new PlatformTitle(title, subtitle, fadein, stay, fadeout);
    }
}
