package app.fuck.com.progressbarwidthnumber;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import static android.graphics.Paint.*;

/**
 * Created by Owner on 2016-1-19.
 */
public class RoundProgressBarWidthNumber extends HorizontalProgressBarWithNumber {

    /**
     * mRadius of view
     */
    private int mRadius = dp2px(30);
    public RoundProgressBarWidthNumber(Context context) {
        this(context, null);
    }

    public RoundProgressBarWidthNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        mReachedProgressBarHeight = (int) (mUnReachedProgressBarHeight*2.5f);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.RoundProgressBarWidthNumber);
        mRadius = (int) typedArray.getDimension(R.styleable.RoundProgressBarWidthNumber_radius,mRadius);
        typedArray.recycle();

        mTextSize = sp2px(14);
        mPaint.setStyle(Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int mPaintWidth = Math.max(mReachedProgressBarHeight,mUnReachedProgressBarHeight);

        if(heightMode != MeasureSpec.EXACTLY){
            int exceptHeight = getPaddingTop() + getPaddingBottom() + mRadius*2+mPaintWidth;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,MeasureSpec.EXACTLY);
        }

        if(widthMode != MeasureSpec.EXACTLY){
            int exceptWidth = getPaddingTop() + getPaddingBottom() + mRadius*2+mPaintWidth;
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(exceptWidth,MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress()+"%";

        float textWidth = mPaint.measureText(text);
        float textHeigh = (mPaint.descent()+mPaint.ascent())/2;

        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mPaint.setStyle(Style.STROKE);
        //draw unreached bar
        mPaint.setColor(mUnReAchedBarColor);
        mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        //draw reached bar
        mPaint.setColor(mReachedBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        float sweepAngle = getProgress()*1.0f/getMax()*360;
        canvas.drawArc(new RectF(0,0,mRadius*2,mRadius*2),0,sweepAngle,false,mPaint);

        //draw text
        mPaint.setStyle(Style.FILL);
        canvas.drawText(text,mRadius-textWidth/2,mRadius-textHeigh,mPaint);

        canvas.restore();
    }
}
