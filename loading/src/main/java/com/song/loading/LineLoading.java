package com.song.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        backlinePaint.setStrokeWidth(SizeUtil.Dp2Px(context,10));
        backlinePaint.setAntiAlias(true);

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setColor(Color.parseColor("#ff00ddff"));
        progressPaint.setStrokeWidth(SizeUtil.Dp2Px(context,8));
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
                invalidate();

                break;

            case MotionEvent.ACTION_MOVE:

                complete = (int) event.getX();

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



        canvas.drawLine(0+10,0,mWidth-10,0, backlinePaint);
        canvas.drawLine(0+10,0,complete-10,0,progressPaint);

    }
}
