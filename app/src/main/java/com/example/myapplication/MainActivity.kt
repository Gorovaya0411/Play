package com.example.myapplication

import android.animation.*
import android.graphics.Insets
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowInsets
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    var DEFAULT_ANIMATION_DURATION = 5000L
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
            onStartAnimation()
        }
        imageViewPresent.setOnClickListener {
            if (DEFAULT_ANIMATION_DURATION.toInt() == 5000){
                DEFAULT_ANIMATION_DURATION + 1000
            }

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
        val i1: Float = r.nextFloat() * (mScreenWidth - imageViewPresent.width)
        val min = 1
        val max = 1.5
        val random = (min + r.nextFloat() * (max - min))

        val valueAnimator = ValueAnimator.ofFloat(-mScreenHeight, mScreenHeight)
        valueAnimator.addUpdateListener { animation ->
            value = animation.animatedValue as Float
            imageViewPresent.translationY = value
            imageViewPresent.translationX = i1
            imageViewPresent.scaleX = random.toFloat()
            imageViewPresent.scaleY = imageViewPresent.scaleX
        }

        val rotationAnimator: ObjectAnimator = ObjectAnimator.ofFloat(
            imageViewPresent,
            "rotation",
            0f,
            180f
        )

        val animatorSet = AnimatorSet()

        animatorSet.play(valueAnimator).with(rotationAnimator)
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.duration = DEFAULT_ANIMATION_DURATION
        animatorSet.start()
    }

    override fun onResume() {
        super.onResume()
        getScreenSize(this)
    }

    private fun getScreenSize(activity: MainActivity) {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets =
                windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
            windowMetrics.bounds.height() - insets.top - insets.bottom
            mScreenWidth = windowMetrics.bounds.width().toFloat()
            mScreenHeight = windowMetrics.bounds.height().toFloat()

        } else {
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            mScreenWidth = metrics.widthPixels.toFloat()
            mScreenHeight = metrics.heightPixels.toFloat()
        }
    }
}