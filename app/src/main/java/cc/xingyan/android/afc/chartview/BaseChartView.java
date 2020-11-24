package cc.xingyan.android.afc.chartview;

import android.content.Context;
import android.util.AttributeSet;

import org.xclcharts.common.DensityUtil;
import org.xclcharts.view.ChartView;

public class BaseChartView extends ChartView {

	
	public BaseChartView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	 public BaseChartView(Context context, AttributeSet attrs){
	        super(context, attrs);   
	        
	 }
	 
	 public BaseChartView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		
	 }
	
	protected int[] getBarLnDefaultSpadding()
	{
		int [] ltrb = new int[4];
		ltrb[0] = DensityUtil.dip2px(getContext(), 40);
		ltrb[1] = DensityUtil.dip2px(getContext(), 30);
		ltrb[2] = DensityUtil.dip2px(getContext(), 20);
		ltrb[3] = DensityUtil.dip2px(getContext(), 50);
		return ltrb;
	}
	
	protected int[] getPieDefaultSpadding()
	{
		int [] ltrb = new int[4];
		ltrb[0] = DensityUtil.dip2px(getContext(), 20);
		ltrb[1] = DensityUtil.dip2px(getContext(), 0);
		ltrb[2] = DensityUtil.dip2px(getContext(), 20);
		ltrb[3] = DensityUtil.dip2px(getContext(), 20);
		return ltrb;
	}
		
	@Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
    
    }  

}
