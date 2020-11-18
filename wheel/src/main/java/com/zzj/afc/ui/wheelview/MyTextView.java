package com.zzj.afc.ui.wheelview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("DrawAllocation")
public class MyTextView extends TextView {

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	 @Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		 Paint paint = new Paint();
         paint.setStyle(Paint.Style.STROKE);
         paint.setColor(getTextColors().getDefaultColor());
         Path path = new Path();
         path.moveTo(0, getHeight()-10);
         path.lineTo(getWidth(), getHeight()-10);
         PathEffect effects = new DashPathEffect(
                         new float[]{5,5,5,5}, 1);
         paint.setPathEffect(effects);
         canvas.drawPath(path, paint);
	}

}
