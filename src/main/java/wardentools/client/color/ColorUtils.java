package wardentools.client.color;

public class ColorUtils {

    public static int rgbToHex(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    public static int addOpaqueAlpha(int rgb) {
        return addAlpha(rgb, 255);
    }

    public static int addAlpha(int rgb, int alpha) {
        return ((alpha & 0xFF) << 24) | (rgb & 0xFFFFFF);
    }

    public static class Alpha {
        public static int getAlpha(int argb) { return (argb >>> 24) & 0xFF; }
        public static int getRed(int argb)   { return (argb >>> 16) & 0xFF; }
        public static int getGreen(int argb) { return (argb >>>  8) & 0xFF; }
        public static int getBlue(int argb)  { return  argb         & 0xFF; }
    }

    public static int getRed(int rgb)   { return (rgb >>> 16) & 0xFF; }
    public static int getGreen(int rgb) { return (rgb >>>  8) & 0xFF; }
    public static int getBlue(int rgb)  { return  rgb         & 0xFF; }

    public static int lerpColor(int colorA, int colorB, float t) {
        int rA = getRed(colorA);
        int gA = getGreen(colorA);
        int bA = getBlue(colorA);
        int rB = getRed(colorB);
        int gB = getGreen(colorB);
        int bB = getBlue(colorB);

        int r = (int) (rA + (rB - rA) * t);
        int g = (int) (gA + (gB - gA) * t);
        int b = (int) (bA + (bB - bA) * t);

        return rgbToHex(r, g, b);
    }

}
