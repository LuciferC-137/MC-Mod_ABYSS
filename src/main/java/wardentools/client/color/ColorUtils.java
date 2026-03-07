package wardentools.client.color;

import org.joml.Vector3f;

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

    public static float[] lerpColor(float[] colorA, float[] colorB, float t) {
        float r = colorA[0] + (colorB[0] - colorA[0]) * t;
        float g = colorA[1] + (colorB[1] - colorA[1]) * t;
        float b = colorA[2] + (colorB[2] - colorA[2]) * t;
        return new float[]{r, g, b};
    }

    public static int lerpColor(int color0, int color1, float t) {
        int rA = getRed(color0);
        int gA = getGreen(color0);
        int bA = getBlue(color0);
        int rB = getRed(color1);
        int gB = getGreen(color1);
        int bB = getBlue(color1);

        int r = (int) (rA + (rB - rA) * t);
        int g = (int) (gA + (gB - gA) * t);
        int b = (int) (bA + (bB - bA) * t);

        return rgbToHex(r, g, b);
    }

    public static float[] hexToNormalizedRGB(int hex) {
        float r = getRed(hex) / 255f;
        float g = getGreen(hex) / 255f;
        float b = getBlue(hex) / 255f;
        return new float[]{r, g, b};
    }

}
