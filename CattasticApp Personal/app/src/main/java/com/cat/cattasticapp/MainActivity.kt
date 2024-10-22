package com.cat.cattasticapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //variables
    lateinit var imageView: ImageView
    // timer delay -- splash
    // android rule -- 5 secs ie 5000ms
    val delay: Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //typecast
        imageView = findViewById(R.id.ivCat)
        //handler to loop the splashy
        Handler(Looper.getMainLooper()).postDelayed({
            //start next activity
            val intent = Intent(this@MainActivity,Login::class.java)
            startActivity(intent)
            finish()
        },delay)

    }
}