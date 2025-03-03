package org.sandboxpowered.silica.client.opengl

import org.joml.Matrix4fStack
import org.joml.Vector3f
import org.joml.Vector3fc
import org.sandboxpowered.silica.client.SilicaClient

object GlobalUniform {
    val MODEL = Matrix4fStack(50)
    val VIEW = Matrix4fStack(50)
    val PROJECTION = Matrix4fStack(50)
    val HEIGHT_SCALE = 0f
    val POSITION = Vector3f(-1f, 1.5f, -3f)
    val SUN_DIRECTION = Vector3f(1f, 1f, 1f).normalize()
    var time = 0

    fun update(client: SilicaClient) {
        time++

        MODEL.clear()
        VIEW.clear()
        PROJECTION.clear()
        PROJECTION.setPerspective(
            Math.toRadians(60.0).toFloat(),
            client.window.width.toFloat() / client.window.height.toFloat(),
            0.1f,
            100f
        )
        VIEW.lookAt(POSITION, Vector3f(0.5f, 0.5f, 0.5f), Vector3f(0f, 1F, 0F))
            .translate(0.5f, 0.5f, 0.5f)
            .rotateXYZ(0f, time.toFloat() / 100, 0f)
            .translate(-0.5f, -0.5f, -0.5f)
    }
}

private operator fun Vector3f.unaryMinus(): Vector3fc = Vector3f(-x, -y, -z)
