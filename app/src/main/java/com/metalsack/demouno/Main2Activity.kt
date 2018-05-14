package com.metalsack.demouno

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        showRandomNumber()
    }
    companion object {
        const val TOTAL_COUNT = "total_count"
    }
    fun showRandomNumber() {
        val count = intent.getIntExtra(TOTAL_COUNT, 0)
        val random = Random()
        var randomInt = 0
        if (count > 0) {
            randomInt = random.nextInt(count + 1)
        }
        txtRandom.text = Integer.toString(randomInt)
    }
}
