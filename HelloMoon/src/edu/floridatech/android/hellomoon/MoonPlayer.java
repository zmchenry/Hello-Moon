package edu.floridatech.android.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MoonPlayer implements SurfaceHolder.Callback {

	private static String TAG = "Player";
	private SurfaceHolder videoHolder;
	private Uri videoUri;
	private int lastPlayed = 0;
	
	private MediaPlayer mPlayer;
	
	public void addCallBack(SurfaceView view) {
		videoHolder = view.getHolder();
		videoHolder.addCallback(this);
	}

	public void play(Context c, boolean isAudioCaller) {		
		if(lastPlayed == 1 && isAudioCaller) {
			mPlayer.start();
			return;
		} else if(lastPlayed == 2 && !isAudioCaller) {
			mPlayer.start();
			return;
		}
		
		if (mPlayer != null) {
			stop();
		}
		if (isAudioCaller) {
			// Pass the context into the MediaPlayer so that it can make sense
			// of the audio file's resource ID (i.e., so it knows enough to know
			// how
			// to play it)
			Log.e(TAG, "Audio " + isAudioCaller);
			mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
			lastPlayed = 1;

		} else {
			videoUri = Uri.parse("android.resource://"
					+ "edu.floridatech.android.hellomoon/raw/apollo_17_stroll");
			mPlayer = MediaPlayer.create(c, videoUri, videoHolder);
			lastPlayed = 2;
			Log.e(TAG, "Video " + isAudioCaller);
		}

		// Rule of Thumb: keep around only one MediaPlayer, and keep it only
		// as
		// long as it is playing something
		// This code makes sure that there is only one instance of the
		// MediaPlayer, even if the user clicks the
		// Play button more than once
		mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				stop();
			}
		});
		mPlayer.start();
	}

	public void stop() {
		if (mPlayer != null) {
			// media player is not null, so we should release it so that other
			// apps
			// can have access to the audio player (only one app can use it at
			// one time, remember)

			// in Android 4.4 (KitKat) you need to reset the player before
			// releasing
			// it or you'll get errors about going away with unhandled events

			mPlayer.reset();
			mPlayer.release();
			mPlayer = null;
			lastPlayed = 0;
		}
	}

	public void pause() {
		if (mPlayer != null && mPlayer.isPlaying()) {
			mPlayer.pause();
		}
	}

	public void restart() {
		if (mPlayer != null) {
			mPlayer.seekTo(0);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		videoHolder = holder;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.e(TAG, "Surface Destroyed");
	}

}
