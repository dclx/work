package com.baidu.bvideoviewsample1;

import com.baidu.bvideoviewsample1.R;
import com.baidu.cyberplayer.core.BMediaController;
import com.baidu.cyberplayer.core.BVideoView;
import com.baidu.cyberplayer.core.BVideoView.OnCompletionListener;
import com.baidu.cyberplayer.core.BVideoView.OnErrorListener;
import com.baidu.cyberplayer.core.BVideoView.OnInfoListener;
import com.baidu.cyberplayer.core.BVideoView.OnPlayingBufferCacheListener;
import com.baidu.cyberplayer.core.BVideoView.OnPreparedListener;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class VideoViewPlayingActivity extends Activity implements OnPreparedListener, 
							OnCompletionListener,
							OnErrorListener, 
							OnInfoListener,
							OnPlayingBufferCacheListener
							{
	private final String TAG = "VideoViewPlayingActivity";
	
	/**
	 * 鎮ㄧ殑ak 
	 */
	//private String AK = "XXX";
	/**
	 * 鎮ㄧ殑sk鐨勫墠16浣�	 */
	//private String SK = "XXX";
	
		
	private String mVideoSource = null;
	
	private BVideoView mVV = null;
	private BMediaController mVVCtl = null;
	private RelativeLayout mViewHolder = null;
	private LinearLayout mControllerHolder = null;
	
	private boolean mIsHwDecode = false;
	
	private EventHandler mEventHandler;
	private HandlerThread mHandlerThread;
	
	private final Object SYNC_Playing = new Object();
		
	private final int EVENT_PLAY = 0;
	
	private WakeLock mWakeLock = null;
	private static final String POWER_LOCK = "VideoViewPlayingActivity";
	
	/**
	 * 鎾斁鐘舵�
	 */
	private  enum PLAYER_STATUS {
		PLAYER_IDLE, PLAYER_PREPARING, PLAYER_PREPARED,
	}
	
	private PLAYER_STATUS mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;
	
	
	/**
	 * 璁板綍鎾斁浣嶇疆
	 */
	private int mLastPos = 0;
	
	
	
	class EventHandler extends Handler {
		public EventHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case EVENT_PLAY:
				/**
				 * 濡傛灉宸茬粡鎾斁浜嗭紝绛夊緟涓婁竴娆℃挱鏀剧粨鏉�				 */
				if (mPlayerStatus != PLAYER_STATUS.PLAYER_IDLE) {
					synchronized (SYNC_Playing) {
						try {
							SYNC_Playing.wait();
							Log.v(TAG, "wait player status to idle");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				/**
				 * 璁剧疆鎾斁url
				 */
				mVV.setVideoPath(mVideoSource);
				
				/**
				 * 缁挱锛屽鏋滈渶瑕佸姝�				 */
				if (mLastPos > 0) {

					mVV.seekTo(mLastPos);
					mLastPos = 0;
				}

				/**
				 * 鏄剧ず鎴栬�闅愯棌缂撳啿鎻愮ず 
				 */
				mVV.showCacheInfo(true);
				
				/**
				 * 寮�鎾斁
				 */
				mVV.start();

				mPlayerStatus = PLAYER_STATUS.PLAYER_PREPARING;
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * 瀹炵幇鍒囨崲绀轰緥
	 */
	private View.OnClickListener mPreListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.v(TAG, "pre btn clicked");
			/**
			 * 濡傛灉宸茬粡寮�彂鎾斁锛屽厛鍋滄鎾斁
			 */
			if(mPlayerStatus != PLAYER_STATUS.PLAYER_IDLE){
				mVV.stopPlayback();
			}
			
			/**
			 * 鍙戣捣涓�鏂扮殑鎾斁浠诲姟
			 */
			if(mEventHandler.hasMessages(EVENT_PLAY))
				mEventHandler.removeMessages(EVENT_PLAY);
			mEventHandler.sendEmptyMessage(EVENT_PLAY);
		}
	};
	
	private View.OnClickListener mNextListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.v(TAG, "next btn clicked");
		}
	};
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
						
		setContentView(R.layout.controllerplaying);

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, POWER_LOCK);
		
		mIsHwDecode = getIntent().getBooleanExtra("isHW", false);
		Uri uriPath = getIntent().getData();
		if (null != uriPath) {
			String scheme = uriPath.getScheme();
			if (null != scheme) {
				mVideoSource = uriPath.toString();
			} else {
				mVideoSource = uriPath.getPath();
			}
		}
		
		initUI();
		
		/**
		 * 寮�惎鍚庡彴浜嬩欢澶勭悊绾跨▼
		 */
		mHandlerThread = new HandlerThread("event handler thread",
				Process.THREAD_PRIORITY_BACKGROUND);
		mHandlerThread.start();
		mEventHandler = new EventHandler(mHandlerThread.getLooper());
	}
	
	/**
	 * 鍒濆鍖栫晫闈�	 */
	private void initUI() {		
		mViewHolder = (RelativeLayout)findViewById(R.id.view_holder);
		mControllerHolder = (LinearLayout )findViewById(R.id.controller_holder);
		
		/**
		 * 璁剧疆ak鍙妔k鐨勫墠16浣�		 */
		String AK = "1111";
		String SK = "222";
		BVideoView.setAKSK(AK, SK);
		
		/**
		 *鍒涘缓BVideoView鍜孊MediaController
		 */
		mVV = new BVideoView(this);
		mVVCtl = new BMediaController(this);
		mViewHolder.addView(mVV);
		mControllerHolder.addView(mVVCtl);
		
		/**
		 * 娉ㄥ唽listener
		 */
		mVV.setOnPreparedListener(this);
		mVV.setOnCompletionListener(this);
		mVV.setOnErrorListener(this);
		mVV.setOnInfoListener(this);
		mVVCtl.setPreNextListener(mPreListener, mNextListener);
		
		/**
		 * 鍏宠仈BMediaController
		 */
		mVV.setMediaController(mVVCtl);
		/**
		 * 璁剧疆瑙ｇ爜妯″紡
		 */
		mVV.setDecodeMode(BVideoView.DECODE_SW);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(TAG, "onPause");
		/**
		 * 鍦ㄥ仠姝㈡挱鏀惧墠 浣犲彲浠ュ厛璁板綍褰撳墠鎾斁鐨勪綅缃�浠ヤ究浠ュ悗鍙互缁挱
		 */
		if (mPlayerStatus == PLAYER_STATUS.PLAYER_PREPARED) {
			mLastPos = mVV.getCurrentPosition();
			mVV.stopPlayback();
		}
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v(TAG, "onResume");
		if (null != mWakeLock && (!mWakeLock.isHeld())) {
			mWakeLock.acquire();
		}
		/**
		 * 鍙戣捣涓�鎾斁浠诲姟,褰撶劧鎮ㄤ笉涓�畾瑕佸湪杩欏彂璧�		 */
		mEventHandler.sendEmptyMessage(EVENT_PLAY);	
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		Log.v(TAG, "onStop");
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		/**
		 * 缁撴潫鍚庡彴浜嬩欢澶勭悊绾跨▼
		 */
		mHandlerThread.quit();
		Log.v(TAG, "onDestroy");
	}

	@Override
	public boolean onInfo(int what, int extra) {
		// TODO Auto-generated method stub
		switch(what){
		/**
		 * 寮�缂撳啿
		 */
		case BVideoView.MEDIA_INFO_BUFFERING_START:
			break;
		/**
		 * 缁撴潫缂撳啿
		 */
		case BVideoView.MEDIA_INFO_BUFFERING_END:
			break;
		default:
			break;
		}
		return false;
	}
	
	/**
	 * 褰撳墠缂撳啿鐨勭櫨鍒嗘瘮锛�鍙互閰嶅悎onInfo涓殑寮�缂撳啿鍜岀粨鏉熺紦鍐叉潵鏄剧ず鐧惧垎姣斿埌鐣岄潰
	 */
	@Override
	public void onPlayingBufferCache(int percent) {
		// TODO Auto-generated method stub
		
	}	

	/**
	 * 鎾斁鍑洪敊
	 */
	@Override
	public boolean onError(int what, int extra) {
		// TODO Auto-generated method stub
		Log.v(TAG, "onError");
		synchronized (SYNC_Playing) {
			SYNC_Playing.notify();
		}
		mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;
		return true;
	}

	/**
	 * 鎾斁瀹屾垚
	 */
	@Override
	public void onCompletion() {
		// TODO Auto-generated method stub
		Log.v(TAG, "onCompletion");
		
		synchronized (SYNC_Playing) {
			SYNC_Playing.notify();
		}
		mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;
	}

	/**
	 * 鎾斁鍑嗗灏辩华
	 */
	@Override
	public void onPrepared() {
		// TODO Auto-generated method stub
		Log.v(TAG, "onPrepared");				
		mPlayerStatus = PLAYER_STATUS.PLAYER_PREPARED;
	}
}
