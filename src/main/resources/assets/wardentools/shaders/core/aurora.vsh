#version 150

in vec3 Position;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec2 texCoord;
out vec3 worldDir;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    
    // Ray direction is simply the normalized vertex position
    // since vertices are on a dome centered at the origin
    worldDir = normalize(Position);

    // Texture coordinates based on spherical direction
    texCoord = vec2(
        atan(worldDir.z, worldDir.x) / 6.28318 + 0.5,
        asin(worldDir.y) / 3.14159 + 0.5
    );
}
