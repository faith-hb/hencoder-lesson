package com.hencoder.hencoder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hencoder.Utils


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        window.decorView.postDelayed({
////            val animator1 = ObjectAnimator.ofInt(objectAnimatorView, "turnoverDegreeFirst", 0, 30)
////            animator1.duration = 1000
////            animator1.interpolator = LinearInterpolator()
////
////            val animator2 = ObjectAnimator.ofInt(objectAnimatorView, "degree", 0, 270)
////            animator2.duration = 2000
////            animator2.interpolator = AccelerateDecelerateInterpolator()
////
////            val animator3 = ObjectAnimator.ofInt(objectAnimatorView, "turnoverDegreeLast", 0, 30)
////            animator3.duration = 1000
////            animator3.interpolator = LinearInterpolator()
////
////            val animatorSet = AnimatorSet()
////            // 三个动画依次执行
//////                animatorSet.playSequentially(animator2, animator3);
////            animatorSet.playSequentially(animator1, animator2, animator3)
////            animatorSet.start()
//
//
//
//            var bottomFlipAnimator = ObjectAnimator.ofFloat(objectAnimatorView,"bottomFlip",30f)
//            bottomFlipAnimator.duration = 1000
//
//            var topFlipAnimator = ObjectAnimator.ofFloat(objectAnimatorView,"topFlip",-30f)
//            topFlipAnimator.startDelay = 200
//            topFlipAnimator.duration = 1000
//
//            var flipRotationAnimator = ObjectAnimator.ofFloat(objectAnimatorView,"flipRotation",270f)
//            flipRotationAnimator.startDelay = 200
//            flipRotationAnimator.duration = 1000
//
//            var animatorSet = AnimatorSet()
//            animatorSet.playSequentially(bottomFlipAnimator,flipRotationAnimator,topFlipAnimator)
//            animatorSet.startDelay = 1000
//            animatorSet.start()
//        }, 2000)

        Utils.shout()
    }
}
