package org.sandboxpowered.silica.client.opengl

import org.sandboxpowered.silica.client.opengl.RenderingFormat.Attribute
import org.sandboxpowered.silica.util.Identifier

object DefaultRenderingFormat {
    val POSITION_TEXTURE = RenderingFormat(
        Identifier("silica", "main"),
        Attribute("position", 3, RenderingFormat.DataType.FLOAT, false),
        Attribute("texCoord", 2, RenderingFormat.DataType.FLOAT, false)
    )
}