package tty.community.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import tty.community.R
import java.io.IOException


object BitmapUtil {

    fun load(context: Context, uri: Uri): Bitmap {
        return ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
    }

    fun Bitmap.cropCenter(): Bitmap {
        val width = this.width
        val height = this.height
        return if (width > height) {
            Bitmap.createBitmap(this, (width - height) / 2, 0, height, height)
        } else {
            Bitmap.createBitmap(this, 0, (height - width) / 2, width, width)
        }
    }

    fun Bitmap.zoom(newWidth: Int, newHeight: Int): Bitmap{
        val width = width
        val height = height
        val scaleWidth = newWidth / width.toFloat()
        val scaleHeight: Float = newHeight / height.toFloat()
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    fun optionsNoCachePortraitDefaultUser() =
        RequestOptions().error(R.drawable.ic_user_gray_64dp).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)

    fun optionsMemoryCacheDefaultPortrait() =
        RequestOptions().error(R.drawable.ic_user_gray_64dp).diskCacheStrategy(DiskCacheStrategy.NONE)

    fun optionsMemoryCache() =
        RequestOptions().error(R.drawable.ic_broken_image_gray).diskCacheStrategy(DiskCacheStrategy.NONE)

    fun optionsNoCache() =
        RequestOptions().error(R.drawable.ic_broken_image_gray).diskCacheStrategy(DiskCacheStrategy.NONE)

}