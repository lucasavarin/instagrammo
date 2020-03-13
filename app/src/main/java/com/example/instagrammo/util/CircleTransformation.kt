package com.example.instagrammo.util

import android.graphics.*
import com.squareup.picasso.Transformation
import java.nio.file.Files.size


class CircleTrasformation : Transformation{
    override fun key(): String = "Circle"

    override fun transform(source: Bitmap?): Bitmap {
        val minEdge = Math.min(source!!.width, source.height)
        val dx = (source.width - minEdge) / 2
        val dy = (source.height - minEdge) / 2

        // Init shader
        val shader = BitmapShader(source!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val matrix = Matrix()
        matrix.setTranslate((-dx).toFloat(), (-dy).toFloat())   // Move the target area to center of the source bitmap
        shader.setLocalMatrix(matrix)

        // Init paint
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = shader

        // Create and draw circle bitmap
        val output = Bitmap.createBitmap(minEdge, minEdge, source.config)
        val canvas = Canvas(output)
        canvas.drawOval(RectF(0f, 0f, minEdge.toFloat(), minEdge.toFloat()), paint)

        val r = minEdge / 2f
        val paintBorder = Paint()
        paintBorder.style = Paint.Style.STROKE
        paintBorder.color = Color.argb(84, 200, 0, 0)
        paintBorder.isAntiAlias = true
        paintBorder.setStrokeWidth(12F)
        canvas.drawCircle(r, r, r - 1, paintBorder)

        source.recycle()

        return output
    }

}