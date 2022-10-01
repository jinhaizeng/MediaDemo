package media.demo

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ImageCodecActivity: AppCompatActivity() {
    companion object {
    }
    private var imageOriginView: ImageView? = null

    private val pickLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri? ->
        uri?.let { it ->
            Log.d(Constants.TAG, it.toString())
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, it))
            } else {
                MediaStore.Images.Media.getBitmap(this.contentResolver, it)
            }
            imageOriginView?.setImageBitmap(bitmap)
//            handleCameraImage(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_codec)
        imageOriginView = findViewById<ImageView>(R.id.image_codec_view)

        val albumButton = findViewById<Button>(R.id.sys_choose_image)
        albumButton.setOnClickListener { pickLauncher.launch("image/*") }

    }
}