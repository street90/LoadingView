package com.song.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.song.loading.util.SizeUtil;

/**
 * Created by song on 2016/9/25.
 */

public class LineLoading extends View {

    private int mWidth;//可以使用的宽度
    private int mHeight;//可以使用的高度

    private Paint backlinePaint;//背景会的的画笔

    private Paint progressPaint;//进度使用的画笔

    private int complete;//完成的进度

    private RectF backRect;

    private RectF progressRect;

    private float rate = 0.0f;

    public LineLoading(Context context) {
        super(context);
    }

    public LineLoading(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public LineLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        backlinePaint = new Paint();
        backlinePaint.setStyle(Paint.Style.STROKE);
        backlinePaint.setColor(Color.parseColor("#C0C0C0"));
        backlinePaint.setStrokeWidth(SizeUtil.Dp2Px(context,2));
        backlinePaint.setAntiAlias(true);

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setColor(Color.parseColor("#ff00ddff"));
        progressPaint.setStrokeWidth(SizeUtil.Dp2Px(context,2));
        progressPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = (int) SizeUtil.Dp2Px(getContext(),100);

        int viewWidth = mWidth + getPaddingRight() + getPaddingLeft();
        int viewHeight = (int) (mHeight + getPaddingBottom() + getPaddingTop());

        Log.e("LineLoading",mHeight + "   " + mWidth);


        backRect = new RectF();
        backRect.left = 20;
        backRect.right = backRect.left + mWidth-40;
        backRect.top = -20;
        backRect.bottom = 20;


        progressRect = new RectF();
        progressRect.left = 20+4;
        progressRect.right = progressRect.left  + mWidth*rate-40-8;
        progressRect.top = -16;
        progressRect.bottom = 16;

        setMeasuredDimension(viewWidth,viewHeight);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                complete = (int) event.getX();
                if( complete>(mWidth-20-20))
                {
                    progressRect.right = progressRect.left  + mWidth-40-8;
                }
                else
                {
                    progressRect.right = progressRect.left  + complete-40-8;
                }
                invalidate();

                break;

            case MotionEvent.ACTION_MOVE:

                complete = (int) event.getX();

                if( complete>(mWidth-20-20))
                {
                    progressRect.right = progressRect.left  + mWidth-40-8;
                }
                else
                {
                    progressRect.right = progressRect.left  + complete-40-8;
                }


                Log.e("LineLoading","complete  "+complete);

                invalidate();

                break;

            case MotionEvent.ACTION_UP:
                break;

        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0,mHeight/2);

        canvas.drawRoundRect(backRect,20,20,backlinePaint);

        canvas.drawRoundRect(progressRect,16,16,progressPaint);

    }

    public void setComplete(float rate) {

        float loading = 0;

        if (Float.compare(1, rate) == 0 || Float.compare(1, rate)< 0)
        {
            loading = 1;
        }
        else if(Float.compare(0, rate)== 0 || Float.compare(0, rate) >0)
        {
            loading = 0;
        }
        else
        {
            loading = rate;
        }

        Log.e("LineLoading",""+rate);

        this.rate = rate;


        invalidate();

    }

}
