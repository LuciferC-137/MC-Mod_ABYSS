#version 150

// Minecraft standard uniforms
uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

// Custom uniforms
uniform vec2 ScreenSize;
uniform float Time;
uniform float AuroraIntensity;
uniform vec3 AuroraColor;

in vec2 texCoord;
in vec3 worldDir;

out vec4 fragColor;

#define PI 3.14159265358979323846264

// Godot mat2(vec2(c, s), vec2(-s, c)) = column 0: (c,s), column 1: (-s,c)
// GLSL mat2(a,b,c,d) = column 0: (a,b), column 1: (c,d)
mat2 mm2(float a) {
    float c = cos(a), s = sin(a);
    return mat2(c, s, -s, c);
}

// Original Godot: mat2(vec2(0.95534, 0.29552), vec2(-0.29552, 0.95534))
const mat2 m2 = mat2(0.95534, 0.29552, -0.29552, 0.95534);

float tri(float x) {
    return clamp(abs(fract(x) - 0.5), 0.01, 0.49);
}

vec2 tri2(vec2 p) {
    return vec2(tri(p.x) + tri(p.y), tri(p.y + tri(p.x)));
}

float triNoise2d(vec2 p, float spd) {
    float z = 1.8;
    float z2 = 2.5;
    float rz = 0.0;
    p *= mm2(p.x * 0.06);
    vec2 bp = p;
    
    for (float i = 0.0; i < 5.0; i++) {
        vec2 dg = tri2(bp * 1.85) * 0.75;
        dg *= mm2(Time * spd);
        p -= dg / z2;

        bp *= 1.3;
        z2 *= 0.45;
        z *= 0.42;
        p *= 1.21 + (rz - 1.0) * 0.02;

        rz += tri(p.x + tri(p.y)) * z;
        p *= (m2 * -1.0);
    }
    return clamp(1.0 / pow(rz * 29.0, 1.3), 0.0, 0.55);
}

float hash21(vec2 n) {
    return fract(sin(dot(n, vec2(12.9898, 4.1414))) * 43758.5453);
}

vec4 aurora(vec3 ro, vec3 rd, vec2 fragCoord) {
    vec4 col = vec4(0.0);
    vec4 avgCol = vec4(0.0);

    for(float i = 0.0; i < 50.0; i++) {
        float of = 0.006 * hash21(fragCoord) * smoothstep(0.0, 15.0, i);
        float pt = ((0.8 + pow(i, 1.4) * 0.002) - ro.y) / (rd.y * 2.0 + 0.4);
        pt -= of;
        vec3 bpos = ro + pt * rd;
        vec2 p = bpos.zx;
        float rzt = triNoise2d(p, 0.06);
        vec4 col2 = vec4(0.0, 0.0, 0.0, rzt);

        vec3 color_variation = (sin(1.0 - vec3(2.15, -0.5, 1.2) + i * 0.043) * 0.5 + 0.5);
        col2.rgb = AuroraColor * color_variation * rzt;

        avgCol = mix(avgCol, col2, 0.5);
        col += avgCol * exp2(-i * 0.065 - 2.5) * smoothstep(0.0, 5.0, i);
    }
    col *= clamp(rd.y * 15.0 + 0.4, 0.0, 1.0);
    return col * AuroraIntensity;
}

void main() {
    // Use default value if ScreenSize is not defined
    vec2 screenSize = ScreenSize;
    if (screenSize.x < 1.0) screenSize = vec2(1920.0, 1080.0);

    // Use world direction directly passed by vertex shader
    // This ensures aurora follows exactly the same transformations as the sky
    vec3 rd = normalize(worldDir);

    // Ray origin (for aurora calculation)
    vec3 ro = vec3(0.0, 0.0, -6.7);
    
    vec3 col = vec3(0.0);

    // Fade based on vertical angle for smooth transition to horizon
    float fade = smoothstep(0.0, 0.01, abs(rd.y)) * 0.1 + 0.9;

    vec2 fragCoord = texCoord * screenSize;

    if (rd.y > 0.0) {
        // Sky above horizon - display only aurora
        vec4 aur = smoothstep(0.0, 1.5, aurora(ro, rd, fragCoord)) * fade;
        col = aur.rgb;
    }
    // Below horizon - display nothing

    // For additive blending, use alpha to control intensity
    float alpha = length(col) > 0.01 ? 1.0 : 0.0;
    fragColor = vec4(col, alpha);
}
