package com.example.myapplication

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    val DEFAULT_ANIMATION_DURATION = 5000L
    var mScreenHeight: Float = 0.toFloat()
    var mScreenWidth: Float = 0.toFloat()
    var score = 0
    private var value: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonPanel.visibility = CardView.INVISIBLE
        viewShadow.visibility = View.INVISIBLE
        imageViewPresent.visibility = ImageView.INVISIBLE
        buttonStart.setOnClickListener {
            imageViewPresent.visibility = ImageView.VISIBLE
            cardViewButton.visibility = CardView.INVISIBLE
            buttonPanel.visibility = CardView.VISIBLE
            viewShadow.visibility = View.VISIBLE
            onStartAnimation() }
        imageViewPresent.setOnClickListener {
            score++
            textViewGlasses.text = "$score"
            onStartAnimation()
        }
        imageButtonReturn.setOnClickListener {
            score = 0
            textViewGlasses.text = "$score"
            onStartAnimation()
        }

    }

    private fun onStartAnimation() {
        cardViewButton.visibility = CardView.INVISIBLE
        imageViewPresent.visibility = ImageView.VISIBLE
        val r = Random()
        val i1: Float = r.nextFloat() * mScreenWidth
        val i2: Float = r.nextFloat() * 3 - 2

        val valueAnimator = ValueAnimator.ofFloat(-mScreenHeight, mScreenHeight)
        valueAnimator.addUpdateListener { animation ->
            value = animation.animatedValue as Float
            imageViewPresent.translationY = value
            imageViewPresent.translationX = i1
        }


        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = DEFAULT_ANIMATION_DURATION

        valueAnimator.start()
    }

    override fun onResume() {
        super.onResume()
        val displaymetrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(displaymetrics)
        mScreenHeight = displaymetrics.heightPixels.toFloat()
        mScreenWidth = displaymetrics.widthPixels.toFloat()
    }
}