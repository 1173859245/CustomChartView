package com.tuxiaoer.hotelbuter.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public abstract class MyBaseGraphView extends View {

    public Context mContext;
    //初始化view的属性
    public int width;
    public int height;
    //起点的x,y坐标值--具体值请动态获取屏幕宽高
    public int originalX=100;
    public int originalY=700;
    public int axisDividedSizex;//X分几等份
    public int axisDevidedSizeY;//Y分等分
    public float maxAxisValueY;//y轴最大值
    public float maxAisValueX;//X轴最大值
    //第一个维度为值，第二个纬度为颜色
    public int[][] columnInfo;
    //图表私有属性
    private String graphTitle;
    private String xAxisName;
    private String yAxisName;
    private float textSise;
    private int textColor;
    //画笔
    public Paint mPaint;
    public MyBaseGraphView(Context context) {
        this(context,null);
    }

    public MyBaseGraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyBaseGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyGraphStyle);
        graphTitle=typedArray.getString(R.styleable.MyGraphStyle_graphTitle);
        xAxisName=typedArray.getString(R.styleable.MyGraphStyle_xAxisName);
        yAxisName=typedArray.getString(R.styleable.MyGraphStyle_YAxisName);
//        textColor=typedArray.getColor(R.styleable.MyGraphStyle_textColor,context.getResources().getColor(android.R
//        .color.black));

        textSise= typedArray.getDimension(R.styleable.MyGraphStyle_textSise,12);

        if (typedArray!=null){
            typedArray.recycle();
        }
        initPaint(context);
    }

    private void initPaint(Context context){

        if (mPaint==null){
            mPaint=new Paint();
            mPaint.setDither(true);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(24);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth()-originalX-100;
        height=(originalY>getHeight()?getHeight():originalY)-300;
        drawXAxis(canvas,mPaint);//X轴
        drawYAxis(canvas,mPaint);//Y轴
        drawTitle(canvas,mPaint);//标题
        drawXAxisScale(canvas,mPaint);//x轴刻度
        drawXAxisScaleValue(canvas,mPaint);//x轴刻度值
        drawYAxisScale(canvas,mPaint);//y轴刻度
        drawYAxisScaleValue(canvas,mPaint);//y轴刻度值
        drawXArrow(canvas,mPaint);//x轴箭头
        drawYArrow(canvas,mPaint);//y轴箭头
        drawColumn(canvas,mPaint);//方块区域
    }
    /**
     * 柱状区域
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawColumn(Canvas canvas, Paint mPaint);




    /**
     * Y轴箭头
     * @param canvas
     * @param mPaint
     */
    private  void drawYArrow(Canvas canvas, Paint mPaint){
          Path mPathY=new Path();
          mPathY.moveTo(originalX,originalY-height-30);
          mPathY.lineTo(originalX-10,originalY-height);
          mPathY.lineTo(originalX+10,originalY-height);
          mPathY.close();
          canvas.drawPath(mPathY,mPaint);
          canvas.drawText(yAxisName,originalX-50,originalY-height-30,mPaint);

    }

    /**
     * X轴箭头
     * @param canvas
     * @param mPaint
     */
    private  void drawXArrow(Canvas canvas, Paint mPaint){
        Path mPathX=new Path();
        mPathX.moveTo(originalX+width+30,originalY);
        mPathX.lineTo(originalX+width,originalY-10);
        mPathX.lineTo(originalX+width,originalY+10);
        mPathX.close();
        canvas.drawPath(mPathX,mPaint);
        canvas.drawText(xAxisName,originalX+width,originalY+30,mPaint);

    }

    /**
     * Y轴刻度
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxisScale(Canvas canvas, Paint mPaint);

    /**
     * Y轴刻度值
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxisScaleValue(Canvas canvas, Paint mPaint);
    /**
     *X轴刻度值
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxisScale(Canvas canvas, Paint mPaint);

    /**
     * X轴刻度值
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxisScaleValue(Canvas canvas, Paint mPaint);

    /**
     * 画标题
     * @param canvas
     * @param mPaint
     */
    private void drawTitle(Canvas canvas, Paint mPaint) {
        if (TextUtils.isEmpty(graphTitle)){
            mPaint.setTextSize(textSise);
            mPaint.setColor(textColor);
            mPaint.setFakeBoldText(true);//粗体
            canvas.drawText(graphTitle,(getWidth()/2)-mPaint.measureText(graphTitle)/2,originalY+40,mPaint);//字体的位置自行脑补--
        }
    }

    /**
     * 画Y轴
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYAxis(Canvas canvas, Paint mPaint);

    /**
     * 画X轴
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxis(Canvas canvas, Paint mPaint);

    /**
     * 控件对外数据交互方法
     * @param columnInfo
     */
    public void setColumnInfo(int[][] columnInfo ){
        this.columnInfo=columnInfo;
    }

    /**
     * 控件X的最大值和多少等分
     * @param maxAisValueX
     * @param axisDividedSizex
     */
    public  void setXAxisiScaleValue(float maxAisValueX,int axisDividedSizex){
        this.maxAisValueX=maxAisValueX;
        this.axisDividedSizex=axisDividedSizex;
    }
    /**
     * 控件Y的最大值和多少等分
     * @param maxAxisValueY
     * @param axisDevidedSizeY
     */
    public  void setYAxisiScaleValue(float maxAxisValueY ,int axisDevidedSizeY){
        this.maxAxisValueY=maxAxisValueY;
        this.axisDevidedSizeY=axisDevidedSizeY;
    }
}
