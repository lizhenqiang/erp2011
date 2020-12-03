package cc.xingyan.android.afc.photoutil.album;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

import cc.xingyan.android.afc.R;


public class ImageFileActivity extends Activity {

	String keyID;
	String type;
	String remark;
	private FolderAdapter folderAdapter;
	private Button bt_cancel;
	private GridView gridView;
	private Context mContext;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.plugin_camera_image_file);
		mContext = this;
		bt_cancel = (Button) findViewById(R.id.cancel);
		bt_cancel.setOnClickListener(new CancelListener());
		gridView = (GridView) findViewById(R.id.fileGridView);

		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("getKeyID");
		keyID = bundle.getString("keyID");
		type = bundle.getString("type");
		remark = bundle.getString("remark");

	}
	
	

	@Override
	protected void onRestart() {

		super.onRestart();
	}

	@Override
	protected void onResume() {

		super.onResume();

		folderAdapter = new FolderAdapter(this, keyID, type, remark);
		gridView.setAdapter(folderAdapter);

	}



	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			finish();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		
		return true;
	}

}
