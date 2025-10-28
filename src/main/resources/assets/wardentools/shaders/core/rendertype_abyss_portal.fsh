#version 150

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

uniform float GameTime;
uniform int AbyssPortalLayers;
uniform float Scale; // Nombre de blocs sur lesquels la texture se répète

in vec3 worldPos;
in vec3 normal;
out vec4 fragColor;

const vec3[] COLORS = vec3[](
    vec3(0.4, 0.4, 0.4),
    vec3(0.38, 0.38, 0.38),
    vec3(0.36, 0.36, 0.36),
    vec3(0.34, 0.34, 0.34),
    vec3(0.32, 0.32, 0.32),
    vec3(0.30, 0.30, 0.30),
    vec3(0.28, 0.28, 0.28),
    vec3(0.26, 0.26, 0.26),
    vec3(0.24, 0.24, 0.24),
    vec3(0.22, 0.22, 0.22),
    vec3(0.20, 0.20, 0.20),
    vec3(0.18, 0.18, 0.18),
    vec3(0.16, 0.16, 0.16),
    vec3(0.14, 0.14, 0.14),
    vec3(0.12, 0.12, 0.12),
    vec3(0.10, 0.10, 0.10)
);

mat2 rotate(float a) {
    float c = cos(a);
    float s = sin(a);
    return mat2(c, -s, s, c);
}

// hash rapide et bien dispersé
float hash(float n) {
    return fract(sin(n * 91.3458) * 47453.5453);
}

// direction pseudo-aléatoire stable pour chaque layer
vec2 randomDir(float seed) {
    float a = hash(seed) * 6.2831853; // 2π
    return vec2(cos(a), sin(a));
}

// Choisit le plan de projection selon la face du bloc
vec2 projectToFace(vec3 pos, vec3 n) {
    if (abs(n.y) > 0.9)
        return pos.xz;
    else if (abs(n.x) > 0.9)
        return pos.zy;
    else
        return pos.xy;
}

void main() {
    // Coordonnées fixes dans le monde
    vec2 uvBase = projectToFace(worldPos, normal);
    uvBase /= Scale;

    // Fond animé lent
    vec2 uv = uvBase + vec2(sin(GameTime * 0.05) * 0.02, cos(GameTime * 0.05) * 0.02);
    vec3 color = texture(Sampler0, uv).rgb * COLORS[0];

    float baseDepthOffset = 0.00005;

    // Rendu multi-couches
    for (int i = 0; i < AbyssPortalLayers; i++) {
        float layer = float(i + 1);
        float depth = layer / float(AbyssPortalLayers);

        // Direction et mouvement aléatoires
        vec2 dir = randomDir(layer * 37.293);
        float speed = 0.3 + depth * 0.4;
        float t = GameTime * speed;
        vec2 offset = dir * vec2(sin(t + layer * 3.1), cos(t * 1.3 + layer * 5.7)) * 0.25 * depth;

        // Rotation indépendante
        float angle = GameTime * 0.25 + layer * 1.9;
        vec2 layerUV = rotate(angle) * uvBase;

        // Scale plus visible : les couches profondes sont plus petites
        float scale = 1.0 - depth * 0.4;
        layerUV *= scale;
        layerUV += offset;

        // Texture et intensité
        vec3 layerColor = texture(Sampler1, layerUV).rgb;
        color += layerColor * COLORS[i] * (1.0 - depth * 0.5);

        // Décalage du plan de rendu (évite z-fighting)
        gl_FragDepth = gl_FragCoord.z - (baseDepthOffset * layer);
    }

    fragColor = vec4(color, 1.0);
}
