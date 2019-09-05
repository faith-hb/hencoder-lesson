package com.hencoder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

import com.hencoder.hencoder.R;

/**
 * @autor hongbing
 * @date 2019/4/11
 */
public class Utils {
    public static float dp2Px(float value) {
       return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                value,
                Resources.getSystem().getDisplayMetrics());
    }

    public static int getZForCamera(){
        return (int) (-6 * Resources.getSystem().getDisplayMetrics().density);
    }

    public static Bitmap getAvatar(Resources resources,int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, R.drawable.hb,options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resources,R.drawable.hb,options);
    }

    public static void shout(){
        System.out.println("i'm shouting!");
    }

}
