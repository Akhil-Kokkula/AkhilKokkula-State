package com.example.akhilkokkula_state

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var imgHolder : ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userText: EditText
    private lateinit var logosArr: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgHolder = findViewById(R.id.imageView)
        userText = findViewById(R.id.editTextText)
        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val celticsImg = R.drawable.boston_celtics_logo
        val warriorsImg = R.drawable.golden_state_warriors_logo
        val lakersImg = R.drawable.los_angeles_lakers_logo
        logosArr = listOf(celticsImg, warriorsImg, lakersImg)

        loadState()
    }

    override fun onDestroy() {
        saveState()
//        println("destroying")
//        println(sharedPreferences.getInt("drawableResId", 0))
//        println(sharedPreferences.getString("userText", ""))
        super.onDestroy()
    }

    fun newImage(view: View) {
        val randomInt = Random().nextInt(logosArr.size)
        imgHolder.setImageDrawable(ContextCompat.getDrawable(this, logosArr[randomInt]))
        imgHolder.tag = logosArr[randomInt]
    }

    private fun loadState() {
        val loadedImgResId = sharedPreferences.getInt("drawableResId", 0)
        val loadedUserText = sharedPreferences.getString("userText", "")
        if (loadedImgResId != 0) {
            imgHolder.setImageDrawable(ContextCompat.getDrawable(this, loadedImgResId))
            userText.setText(loadedUserText)
        } else {
            println("loadedImgResId or loadedUserText are empty - initial state")
        }
    }

    private fun saveState() {
        val currImgResId = imgHolder.tag.toString().toInt()
        val currUserText = userText.text.toString()
        println("saving")
        println(currImgResId)
        println(currUserText)
        sharedPreferences.edit().putInt("drawableResId", currImgResId).apply()
        sharedPreferences.edit().putString("userText", currUserText).apply()
    }

}