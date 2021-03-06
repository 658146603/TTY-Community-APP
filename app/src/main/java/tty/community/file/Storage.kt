package tty.community.file

import android.content.Context
import android.os.Environment
import android.util.Log

import java.io.File

object Storage {

    fun getStorageDirectory(context: Context, type: String): File? {
        val dir = getExternalStorageDirectory(context, type) ?: getInternalStorageDirectory(context, type)
        dir.mkdirs()
        Log.d(TAG, dir.path)
        return dir
    }


    private fun getExternalStorageDirectory(context: Context, type: String): File? {
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            return context.getExternalFilesDir(type)
        }

        return null
    }


    private fun getInternalStorageDirectory(context: Context, type: String): File {
        val dir: File = if (type.isEmpty()) {
            // /data/data/app_package_name/cache
            context.cacheDir

        } else {
            // /data/data/app_package_name/files/type
            File(context.filesDir, type)
        }

        if (!dir.exists()) {
            dir.mkdirs()
        }

        return dir
    }

    const val TAG = "tty.community.file.Storage"
}
