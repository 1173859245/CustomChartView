package com.tuxiaoer.hotelbuter.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  private MyChart myChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myChart=(MyChart)findViewById(R.id.myChart);

        int[][] columnInfo=new int[][]{
                {6, Color.BLUE},
                {5, Color.GREEN},
                {9, Color.RED},
                {2, Color.GRAY},
                {1, Color.MAGENTA},
                {3, Color.CYAN},
                {8, Color.YELLOW},
        };

        myChart.setColumnInfo(columnInfo);
        myChart.setXAxisiScaleValue(100,9);
        myChart.setYAxisiScaleValue(10,7);
    }
}
