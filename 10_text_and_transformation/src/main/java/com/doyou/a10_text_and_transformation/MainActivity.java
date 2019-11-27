package com.doyou.a10_text_and_transformation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import com.doyou.a10_text_and_transformation.view.CameraView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CameraView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCameraView = findViewById(R.id.cameraView);

        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(mCameraView,"bottomFlip",30);
        bottomFlipAnimator.setDuration(1000);

        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(mCameraView,"topFlip",-30);
        topFlipAnimator.setStartDelay(200);
        topFlipAnimator.setDuration(1000);

        ObjectAnimator rotationFlipAnimator = ObjectAnimator.ofFloat(mCameraView,"flipRotation",270);
        rotationFlipAnimator.setStartDelay(200);
        rotationFlipAnimator.setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator,rotationFlipAnimator,topFlipAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();
    }
}
