
package cc.xingyan.android.afc.chartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarChart3D;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.StackBarChart;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import cc.xingyan.android.afc.util.LogUtil;

public class BarChartView extends BaseChartView {

	private String TAG = "SpinnerBarChart01View";

	private int type;
	private int mChartStyle = 0;
	private int mOffsetHeight = 0;
	private BarChart mChart = null;

	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();

	public BarChartView(Context context, int chartStyle, int offsetHeight,
						 List<String> unitNames, List<String> lastDatas, List<String> currDatas, int type) throws  Exception{
		super(context);
		// TODO Auto-generated constructor stub

		this.type = type;
		mChartStyle = chartStyle;
		mOffsetHeight = offsetHeight;
		chartLabels(unitNames);
		chartDataSet(lastDatas, currDatas);
	}



    private void initChart(int chartStyle)
	{
		switch(chartStyle)
		{
			case 0:
				mChart = new BarChart();

				mChart.getAxisTitle().setLeftTitle("数量");
				break;
			case 1:
				mChart = new BarChart();
				mChart.setChartDirection(XEnum.Direction.HORIZONTAL);
				break;
			case 2:
				mChart = new BarChart3D();
				break;
			case 3:
				mChart = new BarChart3D();
				mChart.setChartDirection(XEnum.Direction.HORIZONTAL);
				break;
			case 4:
				mChart = new StackBarChart();
				((StackBarChart) mChart).setTotalLabelVisible(false);
				break;
			case 5:
				mChart = new StackBarChart();
				mChart.setChartDirection(XEnum.Direction.HORIZONTAL);
				((StackBarChart) mChart).setTotalLabelVisible(false);
				break;
		}



	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

	}

	public void chartRender(double max, double min, double steps)
	{
		try {

			initChart(mChartStyle);


			int [] ltrb = getBarLnDefaultSpadding();
			mChart.setPadding(DensityUtil.dip2px(getContext(), 50),ltrb[1], ltrb[2], ltrb[3]);



			mChart.setDataSource(chartData);
			mChart.setCategories(chartLabels);


			mChart.getDataAxis().setAxisMax(max);
			mChart.getDataAxis().setAxisMin(min);
			mChart.getDataAxis().setAxisSteps(steps);


			mChart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){

				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub
					Double tmp = Double.parseDouble(value);
					DecimalFormat df = new DecimalFormat("#0");
					String label = df.format(tmp).toString();
					return label+"";
				}

			});

			mChart.getBar().setItemLabelVisible(true);
			mChart.getBar().getItemLabelPaint().setColor(Color.rgb(72, 61, 139));
			mChart.getBar().getItemLabelPaint().setFakeBoldText(true);

			mChart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(value).toString();
					return label+"";
				}});


			mChart.DeactiveListenItemClick();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.error(TAG, e.toString());
		}
	}
	private void chartDataSet( List<String> lastDatas, List<String> currDatas) throws Exception
	{
		double max = 0;
		double min = 0;
		double steps = 0;


		List<Double> dataSeriesA= new LinkedList<Double>();

		for(String lastData : lastDatas){
			double lastDataTemp = Double.parseDouble(lastData);
			dataSeriesA.add(lastDataTemp);
			max = max > lastDataTemp ? max : lastDataTemp;
		}

		BarData barDataA = null;
		switch (type){
			case 0:
				barDataA = new BarData("（上周/上月/上季度）接报故障数",dataSeriesA,Color.rgb(73, 135, 218));
				break;

			case 1:
				barDataA = new BarData("（上周/上月/上季度）上表故障数",dataSeriesA,Color.rgb(73, 135, 218));
				break;
		}

		List<Double> dataSeriesB= new LinkedList<Double>();

		for(String currData : currDatas){
			double currDataTemp = Double.parseDouble(currData);
			dataSeriesB.add(currDataTemp);
			max = max > currDataTemp ? max : currDataTemp;
		}

		BarData barDataB = null;
		switch (type){
			case 0:
				barDataB = new BarData("(本周/本月/本季度）接报故障数",dataSeriesB,Color.rgb(224, 4, 0));
				break;

			case 1:
				barDataB = new BarData("(本周/本月/本季度）上表故障数",dataSeriesB,Color.rgb(224, 4, 0));
				break;
		}



		chartData.add(barDataA);
		chartData.add(barDataB);

		max = Math.rint(max*1.5);
		steps = Math.rint(max/5);

		chartRender(max, min, steps);
	}

	private void chartLabels(List<String> unitNames){

		for(String unitName : unitNames){
			chartLabels.add(unitName);
		}
	}

	@Override
	public void render(Canvas canvas) {
		try{

			mChart.setChartRange(0.0f, mOffsetHeight, this.getWidth(),this.getHeight() - mOffsetHeight);

			mChart.render(canvas);
		} catch (Exception e){
			LogUtil.error(TAG, e.toString());
		}
	}
}
