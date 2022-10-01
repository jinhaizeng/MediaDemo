package media.demo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val imageCodecLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult -> {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageButton = findViewById<Button>(R.id.image_codec)
        imageButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent().setAction("image_codec")
                imageCodecLauncher.launch(intent)
            }
        })
    }
}