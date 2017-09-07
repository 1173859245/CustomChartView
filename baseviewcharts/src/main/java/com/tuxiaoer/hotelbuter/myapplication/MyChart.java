package com.tuxiaoer.hotelbuter.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class MyChart extends MyBaseGraphView {


    public MyChart(Context context) {
        super(context);
    }

    public MyChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth()-originalX;
        height=(originalY>getHeight()?getHeight():originalY)-300;
    }

    @Override
    protected void drawColumn(Canvas canvas, Paint mPaint) {
   if (columnInfo !=null){
       float cellWidth=width/axisDividedSizex;
       for (int i=0;i<columnInfo.length;i++){
           mPaint.setColor(columnInfo[i][1]);
           float leftTopY=originalY-height*(columnInfo[i][0])/axisDevidedSizeY;
           canvas.drawRect(originalX+cellWidth*(i+1),leftTopY,originalX+cellWidth*(i+2),originalY,mPaint);
       }
   }
    }



    @Override
    protected void drawYAxisScale(Canvas canvas, Paint mPaint) {
        float cellWidth=height/axisDevidedSizeY;
        for (int i=0;i<axisDevidedSizeY;i++){
            canvas.drawLine(originalX,(originalY-cellWidth*(i+1)),originalX+10,(originalY-cellWidth*(i+1)),mPaint);
        }
    }

    @Override
    protected void drawYAxisScaleValue(Canvas canvas, Paint mPaint) {
        float cellHeight=height/axisDevidedSizeY;
        float cellValue=maxAxisValueY/axisDevidedSizeY;
        for (int i=1;i<axisDevidedSizeY;i++){
            canvas.drawText(String.valueOf(cellValue*i),originalX-30,originalY-cellHeight*i+10,mPaint);
        }

    }

    @Override
    protected void drawXAxisScale(Canvas canvas, Paint mPaint) {
        float cellWidth=width/axisDividedSizex;
        for (int i=0;i<axisDividedSizex;i++){
            canvas.drawLine(cellWidth*(i+1)+originalX,originalY,cellWidth*(i+1)+originalX,originalY-10,mPaint);
        }

    }

    @Override
    protected void drawXAxisScaleValue(Canvas canvas, Paint mPaint) {
        float cellWidth=width/axisDividedSizex;
        for (int i=0;i<axisDividedSizex;i++){
            canvas.drawText(i+"月",cellWidth * i+originalX-35,originalY+30,mPaint);
        }
    }

    @Override
    protected void drawYAxis(Canvas canvas, Paint mPaint) {
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(originalX,originalY,originalX,originalY-height,mPaint);

    }

    @Override
    protected void drawXAxis(Canvas canvas, Paint mPaint) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        canvas.drawLine(originalX,originalY,originalX+width,originalY,mPaint);

    }

}
