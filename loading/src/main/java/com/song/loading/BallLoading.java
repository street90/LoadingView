package com.song.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by song on 2016/9/24.
 */

public class BallLoading extends View {


    private int circleRadius = 300;

    private int startRadius = 0;

    private int centerX,centerY;

    private int startAngle = 0;

    private Paint mPaint;

    private boolean isAdd = true;

    public BallLoading(Context context) {
        super(context);
    }

    public BallLoading(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);

    }

    public BallLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ff00ddff"));
        mPaint.setStyle(Paint.Style.FILL);

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

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX,centerY);


        startAngle+=2;

        if(isAdd)
        {
            startRadius +=4;
        }
        else
        {
            startRadius-= 4;
        }

        for (int i = 0; i < 4; i++) {
            int x = (int) ((startRadius*0.5-20) * Math.cos(Math.PI*(startAngle+i*90)/180 ));
            int y = (int) ((startRadius*0.5-20) * Math.sin(Math.PI*(startAngle+i*90)/180));
            canvas.drawCircle(x,y,20,mPaint);

        }

        if(startRadius < 40)
        {
            isAdd = true;
        }
        else if(startRadius >= 280)
        {
            isAdd = false;
        }


        postInvalidateDelayed(4);


    }
}
