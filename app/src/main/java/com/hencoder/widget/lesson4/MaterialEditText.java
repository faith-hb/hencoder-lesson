package com.hencoder.widget.lesson4;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.hencoder.Utils;

/**
 * 自定义EditText
 *
 * @autor hongbing
 * @date 2019/4/19
 */
public class MaterialEditText extends AppCompatEditText {

    private static final String TAG = "MaterialEditText";
    private static final float FONT_SIZE = Utils.dp2Px(12);
    public static final int MARGIN_TOP = (int) Utils.dp2Px(8);
    private static final float VER_OFFSET = Utils.dp2Px(36);
    private static final float HOR_OFFSET = Utils.dp2Px(5);
    // 垂直高度变化值，依据渐变值
    public static final float VER_EXTRA = Utils.dp2Px(16);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ObjectAnimator mAnimator;

    private float floatLabelPraction; // 渐变动画
    private boolean mFloatIsShow;

    public float getFloatLabelPraction() {
        return floatLabelPraction;
    }

    public void setFloatLabelPraction(float floatLabelPraction) {
        this.floatLabelPraction = floatLabelPraction;
        invalidate();
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "构造函数...");
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            Log.d(TAG, "key = " + attrs.getAttributeName(i) + "->value = " + attrs.getAttributeValue(i));
        }

        mPaint.setTextSize(FONT_SIZE);
        setPadding(getPaddingLeft(), (int) (getPaddingTop() + FONT_SIZE + MARGIN_TOP), getPaddingRight(), getPaddingBottom());
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mFloatIsShow && !TextUtils.isEmpty(s)) {
                    mFloatIsShow = !mFloatIsShow;
                    getAnimator().start(); // 正常执行
                } else if(mFloatIsShow && TextUtils.isEmpty(s)){
                    mFloatIsShow = !mFloatIsShow;
                    getAnimator().reverse(); // 反向执行
                }
            }
        });
    }

    private ObjectAnimator getAnimator(){
        if(mAnimator == null){
            mAnimator = ObjectAnimator.ofFloat(this, "floatLabelPraction", 0.f,1.f);
        }
        return mAnimator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "txt = " + getText() + "->floatLabelPraction = " + floatLabelPraction);
        mPaint.setAlpha((int) (floatLabelPraction * 0xff));
        canvas.drawText(getHint().toString(), HOR_OFFSET, VER_OFFSET - floatLabelPraction * VER_EXTRA, mPaint);
    }
}
