package com.baidu.dlnajar.sample;

import java.util.List;

import com.baidu.cyberplayer.dlna.DLNAActionListener;
import com.baidu.cyberplayer.dlna.DLNAProviderFactory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnClickListener {
	private Button mPlayBtn;
	private TextView mInfoTV;
	private EditText mSourceET;

	private final int UPDATE_INFO = 0;

	private final int GET_RENDER_SUC = 103;
	private final int GET_RENDER_FAIL = 104;
	private final int ENABLE_SUC = 105;
	private final int ENABLE_FAIL = 106;

	// 您的ak
	// private String AK = "xxx";
	// 您的sk的前16位
	// private String SK = "xxx";

	private Handler uiHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case ENABLE_SUC:
				Toast.makeText(MainActivity.this, "enable_suc",
						Toast.LENGTH_LONG).show();
				mPlayBtn.setEnabled(true);
				break;
			case ENABLE_FAIL:
				Toast.makeText(MainActivity.this, "enable_dlna_fail",
						Toast.LENGTH_LONG).show();
				break;
			case GET_RENDER_SUC:

				if (alertPorgress != null && alertPorgress.isShowing())
					alertPorgress.dismiss();
				showRenderList();
				break;
			case GET_RENDER_FAIL:

				if (alertPorgress != null && alertPorgress.isShowing())
					alertPorgress.dismiss();
				Toast.makeText(MainActivity.this, "null renders",
						Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}
		}
	};

	String[] mRetInfo = new String[] { "RET_NEW_PACKAGE_INSTALLED",
			"RET_NO_NEW_PACKAGE", "RET_STOPPED", "RET_CANCELED",
			"RET_FAILED_STORAGE_IO", "RET_FAILED_NETWORK",
			"RET_FAILED_ALREADY_RUNNING", "RET_FAILED_OTHERS",
			"RET_FAILED_ALREADY_INSTALLED", "RET_FAILED_INVALID_APK" };

	Handler mUIHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_INFO:
				mInfoTV.setText((String) msg.obj);
				break;
			default:
				break;
			}
		}
	};

	DLNAActionListener mActionListener = new DLNAActionListener() {

		@Override
		public void onStop(boolean isSucess, int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSetVolume(boolean isSucess, int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSetMute(boolean isSucess, int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSetMediaURI(boolean isSucess, int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSelectRenderDevice(boolean isSuccess, int errCode,
				String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSeek(boolean isSucess, int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPlay(boolean isSucess, int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPause(boolean isSucess, int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetVolume(boolean isSucess, int result, int errCode,
				String errDesc) {
			// TODO Auto-generated method stub

		}	

		@Override
		public void onGetSupportedProtocols(boolean isSucess, String result,
				int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetMute(boolean isSucess, boolean result, int errCode,
				String errDesc) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onEnableDLNA(boolean isSuccess, int errCode, String errDesc) {
			// TODO Auto-generated method stub
			if (isSuccess)
				uiHandler.sendEmptyMessage(ENABLE_SUC);
			else {
				uiHandler.sendEmptyMessage(ENABLE_FAIL);
			}
		}

		@Override
		public void onDisableDLNA(boolean isSuccess, int errCode, String errDesc) {
			// TODO Auto-generated method stub

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DLNAControlActivity.serviceProvider = DLNAProviderFactory.getProviderInstance(this);
		if (DLNAControlActivity.serviceProvider == null)
			finish();
		//使用您在百度开发者中心上申请的AK,SK
		DLNAControlActivity.serviceProvider.initialize(AK, SK);
		DLNAControlActivity.serviceProvider.addActionCallBack(mActionListener);
		DLNAControlActivity.serviceProvider.enableDLNA();
		setContentView(R.layout.activity_main);

		initUI();
	}

	void initUI() {
		mPlayBtn = (Button) findViewById(R.id.playBtn);
		mInfoTV = (TextView) findViewById(R.id.infoTV);
		mSourceET = (EditText) findViewById(R.id.getET);
		mSourceET
				.setText("http://bcs.duapp.com/dlna-sample/out_MP4_AVC_AAC_320x240_2013761628.mp4?sign=MBO:C09e40adc8851224375a26cf2c6d12a0:7zwy3HtoM%2B5hXB2%2FlJFN6OkWFCs%3D");

		mPlayBtn.setOnClickListener(this);
		mPlayBtn.setEnabled(false);
		Button list_button = (Button) findViewById(R.id.url_list);
		list_button.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.playBtn:
			playVideo();
			break;
		case R.id.url_list:
			showURLList();
			break;
		default:
			break;
		}
	}

	public void onDestroy() {
		super.onDestroy();
		if (DLNAControlActivity.serviceProvider != null)
			DLNAControlActivity.serviceProvider.disableDLNA();
	}

	private ProgressDialog alertPorgress;

	private void playVideo() {
	
			String source = mSourceET.getText().toString();
			if (source == null || source.equals("")) {
				setInfo("Please input a valid video path");
			} else {
				getRenderList();
		}
	}

	List<String> renderList;

	private void getRenderList() {
		renderList = DLNAControlActivity.serviceProvider.getRenderList();
		if (renderList == null) {
			uiHandler.sendEmptyMessage(GET_RENDER_FAIL);
			return;
		}
		uiHandler.sendEmptyMessage(GET_RENDER_SUC);
	}

	private AlertDialog selectDialog;

	private void showRenderList() {
		ListView listView = new ListView(MainActivity.this);
		listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, renderList));
		listView.setBackgroundColor(Color.WHITE);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				selectDialog.dismiss();
				String selectDevice = renderList.get(arg2);
				String playUrl = mSourceET.getText().toString();

				Intent intent = new Intent(MainActivity.this,
						DLNAControlActivity.class);
				intent.putExtra("device", selectDevice);
				intent.putExtra("url", playUrl);
				MainActivity.this.startActivity(intent);
			}
		});
		selectDialog = new AlertDialog.Builder(MainActivity.this)
				.setView(listView).setCancelable(true).create();
		selectDialog.show();
	}



	private void setInfo(String info) {
		Message msg = new Message();
		msg.what = UPDATE_INFO;
		msg.obj = info;
		mUIHandler.sendMessage(msg);
	}

	private String[] urlStrings = {
			"http://bcs.duapp.com/dlna-sample/out_MP4_AVC_AAC_320x240_2013761628.mp4?sign=MBO:C09e40adc8851224375a26cf2c6d12a0:7zwy3HtoM%2B5hXB2%2FlJFN6OkWFCs%3D",
			"http://bcs.duapp.com/dlna-sample/out.mpg?sign=MBO:C09e40adc8851224375a26cf2c6d12a0:1R59%2BHvmOopAtsSuvNGNwawbKrU%3D&response-content-disposition=filename*=utf8zz" };

	private void showURLList() {
		ListView listView = new ListView(MainActivity.this);
		listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, urlStrings));
		listView.setBackgroundColor(Color.WHITE);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				selectDialog.dismiss();
				mSourceET.setText(urlStrings[arg2]);
			}
		});
		selectDialog = new AlertDialog.Builder(MainActivity.this)
				.setView(listView).setCancelable(true).create();
		selectDialog.show();
	}
}
