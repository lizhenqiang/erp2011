
package cc.xingyan.android.afc.chartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.event.click.ArcPosition;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.plot.PlotLegend;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import cc.xingyan.android.afc.util.LogUtil;


public class PieChartView extends BaseChartView implements Runnable{
	
	private String TAG = "PieChartView";
	private PieChart chart = new PieChart();
	private ArrayList<PieData> chartData = new ArrayList<PieData>();
	private int mSelectedID = -1;

	private int aGNum;
	private int bomNum;
	private int tvmNum;
	private int otherNum;

	private double aGPer;
	private double bomPer;
	private double tvmPer;
	private double otherPer;



	public PieChartView(Context context, Map<String, String> datasMap) {
		super(context);
		// TODO Auto-generated constructor stub
		this.aGNum = Integer.parseInt(datasMap.get("AGNum"));
		this.bomNum = Integer.parseInt(datasMap.get("BomNum"));
		this.tvmNum = Integer.parseInt(datasMap.get("TvmNum"));
		this.otherNum = Integer.parseInt(datasMap.get("OtherNum"));

		this.aGPer = Double.parseDouble(datasMap.get("AGPer")) * 100;
		this.bomPer = Double.parseDouble(datasMap.get("BomPer")) * 100;
		this.tvmPer = Double.parseDouble(datasMap.get("TvmPer")) * 100;
		this.otherPer = Double.parseDouble(datasMap.get("OtherPer")) * 100;

		initView();
	}
	
	public PieChartView(Context context, AttributeSet attrs){
        	super(context, attrs);   
        	initView();
	 }
	 
	 public PieChartView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
		 	chartDataSet();	
			chartRender();
			
			new Thread(this).start();
	 }
	 	 	
	@Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  

        chart.setChartRange(w,h);
    }  	
	
	
	private void chartRender()
	{
		try {					
			

			int [] ltrb = getPieDefaultSpadding();
			float right = DensityUtil.dip2px(getContext(), 100);
			chart.setPadding(ltrb[0], ltrb[1], right, ltrb[3]);			
			

			chart.setLabelStyle(XEnum.SliceLabelStyle.INSIDE);
			chart.getLabelPaint().setColor(Color.WHITE);
			

			chart.setTitle("设备故障占比");

			chart.setTitleVerticalAlign(XEnum.VerticalAlign.BOTTOM);
								

			chart.ActiveListenItemClick();
			chart.showClikedFocus();
			

			PlotLegend legend = chart.getPlotLegend();
			legend.show();
			legend.setType(XEnum.LegendType.COLUMN);
			legend.setHorizontalAlign(XEnum.HorizontalAlign.RIGHT);
			legend.setVerticalAlign(XEnum.VerticalAlign.MIDDLE);
			legend.showBox();
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.error(TAG, e.toString());
		}
	}
	
	private void chartDataSet()
	{

		DecimalFormat df = new DecimalFormat("#.00");

		chartData.add(new PieData("AG故障数", df.format(aGPer) + "%" + "(" + aGNum + ")", aGPer,Color.rgb(191, 79, 75)));
		chartData.add(new PieData("BOM故障数", df.format(bomPer) + "%" + "(" + bomNum + ")", bomPer, Color.rgb(242, 167, 69)));
		chartData.add(new PieData("TVM故障数", df.format(tvmPer) + "%" + "(" + tvmNum + ")", tvmPer, Color.rgb(60, 173, 213)));
		chartData.add(new PieData("其他故障数", df.format(otherPer) + "%" + "(" + otherNum + ")", otherPer, Color.rgb(90, 79, 88)));
	
	}

	@Override
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
			LogUtil.error(TAG, e.toString());
        }
    }


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {          
         	chartAnimation();         	
         }
         catch(Exception e) {
             Thread.currentThread().interrupt();
         }  
	}
	
	private void chartAnimation()
	{
		  try {       
			 
			    chart.setDataSource(chartData);
			  	int count = 360 / 10;
			  	
	          	for(int i=1;i<count ;i++)
	          	{
	          		Thread.sleep(40);
	          		
	          		chart.setTotalAngle(10 * i);
	          		

	    			if(count - 1 == i)
	    			{
	    				chart.setTotalAngle(360);
	    				
	    				chart.ActiveListenItemClick();

	    				chart.getArcBorderPaint().setColor(Color.YELLOW);
	    				chart.getArcBorderPaint().setStrokeWidth(3);
	    			}
	    			
	          		postInvalidate();            				          	          	
	          }
			  
          }
          catch(Exception e) {
              Thread.currentThread().interrupt();
          }       
		  
	}
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub		
		super.onTouchEvent(event);		
		if(event.getAction() == MotionEvent.ACTION_UP) 
		{						
			if(chart.isPlotClickArea(event.getX(),event.getY()))
			{											
				triggerClick(event.getX(),event.getY());					
			}
		}
		return true;
	}
	


	private void triggerClick(float x,float y)
	{	
		if(!chart.getListenItemClickStatus())return;
				
		ArcPosition record = chart.getPositionRecord(x,y);
		if( null == record) return;

		PieData pData = chartData.get(record.getDataID());
		if(record.getDataID() == mSelectedID ) 
		{
			boolean bStatus = chartData.get(mSelectedID).getSelected();
			chartData.get(mSelectedID).setSelected(!bStatus);
		}else{
			if(mSelectedID >= 0)
				chartData.get(mSelectedID).setSelected(false);
			pData.setSelected(true);
		}
		mSelectedID = record.getDataID();
		this.refreshChart();		
				

	}
	
	
}
