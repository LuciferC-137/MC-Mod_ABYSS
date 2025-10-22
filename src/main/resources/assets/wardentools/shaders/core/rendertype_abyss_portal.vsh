#version 150

in vec3 Position;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform vec3 BlockPos;

out vec3 worldPos;
out vec3 normal;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    // Position mondiale absolue (indépendante de la caméra)
    worldPos = Position + BlockPos;

    // La normale est basée sur Position local [0,1] pour savoir quelle face
    if (abs(Position.x - 0.0) < 0.001) normal = vec3(-1, 0, 0);
    else if (abs(Position.x - 1.0) < 0.001) normal = vec3(1, 0, 0);
    else if (abs(Position.y - 0.0) < 0.001) normal = vec3(0, -1, 0);
    else if (abs(Position.y - 1.0) < 0.001) normal = vec3(0, 1, 0);
    else if (abs(Position.z - 0.0) < 0.001) normal = vec3(0, 0, -1);
    else normal = vec3(0, 0, 1);
}