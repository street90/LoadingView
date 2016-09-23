package com.song.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by song on 2016/9/22.
 */

public class CircleLoading extends View {

    private int circleRadius = 360;
    private Paint mCirclePaint;
    private int startAngle = 0;
    private SweepGradient mSweepGradient;
    private int centerX,centerY;
    private RectF circleBounds = new RectF();
    private Matrix mMatrix = new Matrix();

    public CircleLoading(Context context) {
        super(context);
    }

    public CircleLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CircleLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.parseColor("#ff00ddff"));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(20);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int viewWidth = circleRadius + this.getPaddingLeft() + this.getPaddingRight();
        int viewHeight = circleRadius + this.getPaddingTop() + this.getPaddingBottom();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);



        int width;
        int height;

        if(widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        }
        else if(widthMode == MeasureSpec.AT_MOST)
        {
            width = Math.min(viewWidth,widthSize);
        }
        else
        {
            width = viewWidth;
        }

        if(heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        }
        else if(heightMode == MeasureSpec.AT_MOST)
        {
            height = Math.min(heightSize,viewHeight);
        }
        else
        {
            height = viewHeight;
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w/2;
        centerY = h/2;
//        mSweepGradient = new SweepGradient(centerX,centerY,Color.parseColor("#ff00ddff"),Color.parseColor("#ffffffff"));
//        mCirclePaint.setShader(mSweepGradient);

        circleBounds.set(getPaddingLeft()+10,getPaddingTop()+10,w-getPaddingRight()-10,h-getPaddingBottom()-10);
    }

    int complate = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       startAngle+= 3;

        complate++;


        canvas.drawArc(circleBounds,startAngle,complate,false,mCirclePaint);


        Log.e("CircleLoading","Circle"+startAngle);

        if(complate > 360)
        {

        }
        else
            postInvalidateDelayed(10);



    }
}
