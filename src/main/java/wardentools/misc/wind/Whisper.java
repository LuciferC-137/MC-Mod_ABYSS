package wardentools.misc.wind;

public class Whisper {
    private final String whisper;
    private final String whisper_fr;
    private final int id;

    public Whisper(String whisper, String whisper_fr, int id) {
        this.whisper = whisper;
        this.whisper_fr = whisper_fr;
        this.id = id;
    }

    public String getWhisper() {
        return whisper;
    }

    public String getWhisper_fr() {
        return whisper_fr;
    }

    public int getId() {
        return id;
    }
}
