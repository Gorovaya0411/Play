package com.example.myapplication

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    val DEFAULT_ANIMATION_DURATION = 5000L
    var mScreenHeight: Float = 0.toFloat()
    var mScreenWidth: Float = 0.toFloat()
    var in2 = 0
     private var  value :Float= 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView.visibility = ImageView.INVISIBLE
        button2.setOnClickListener { onStartAnimation() }
        imageView.setOnClickListener{
            textView.text = "$in2"
            in2++
            onStartAnimation()
        }

    }

    private fun onStartAnimation(){
        ff.visibility = CardView.INVISIBLE
        imageView.visibility = ImageView.VISIBLE
        val r = Random()
        val i1: Float = r.nextFloat() * mScreenWidth
        val i2: Float = r.nextFloat() * 3 - 2

        val valueAnimator = ValueAnimator.ofFloat(-mScreenHeight, mScreenHeight)
        valueAnimator.addUpdateListener { animation ->
             value = animation.animatedValue as Float
//            imageView.scaleX = i2
//            imageView.scaleY = imageView.scaleX
            imageView.translationY = value
            imageView.translationX = i1
        }

       }

        valueAnimator.addListener(object:Animator.AnimatorListener() {
            override fun onAnimationStart(animation:Animator) {

            }
            override fun onAnimationEnd(animation:Animator) {

                Toast.makeText(this@WithListenerAnimationActivity, "Doge is on the moon", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
            override fun onAnimationCancel(animation:Animator) {
            }
            override fun onAnimationRepeat(animation:Animator) {
            }
        })
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = DEFAULT_ANIMATION_DURATION

        valueAnimator.start()
    }






    override fun onResume() {
        super.onResume()
        val displaymetrics = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics)
        mScreenHeight = displaymetrics.heightPixels.toFloat()
        mScreenWidth = displaymetrics.widthPixels.toFloat()
    }
}