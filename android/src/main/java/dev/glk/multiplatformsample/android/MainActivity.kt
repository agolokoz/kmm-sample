package dev.glk.multiplatformsample.android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.glk.multiplatformsample.SpaceXApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()
    private val api by lazy { SpaceXApi() }

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text)

        findViewById<View>(R.id.loadButton).setOnClickListener {
            mainScope.launch {
                val launches = api.getAllLaunches()
                Log.d("MainActivity", "Launches: $launches")
                textView.text = launches.toString()
            }
        }
    }
}