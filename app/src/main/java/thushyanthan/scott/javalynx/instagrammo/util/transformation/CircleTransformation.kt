package thushyanthan.scott.javalynx.instagrammo.util.transformation

import android.graphics.*
import com.squareup.picasso.Transformation

class CircleTransformation: Transformation {
    override fun transform(source: Bitmap?): Bitmap {
        var size:Int = Math.min(source!!.width,source!!.height)
        var x:Int = (source.width - size) / 2
        var y:Int = (source.height - size) / 2

        var squaredBitmap : Bitmap = Bitmap.createBitmap(source,x,y,size,size)
        if(squaredBitmap != source){
            source.recycle()
        }

        var bitmap:Bitmap = Bitmap.createBitmap(size,size,source.config)

        var canvas : Canvas = Canvas(bitmap)
        var paint : Paint = Paint()
        var shader : BitmapShader = BitmapShader(squaredBitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        paint.setShader(shader)
        paint.isAntiAlias

        var r : Float = size/2f
        canvas.drawCircle(r,r,r,paint)

        squaredBitmap.recycle()
        return bitmap

    }

    override fun key(): String {
        return "circle"
    }
}