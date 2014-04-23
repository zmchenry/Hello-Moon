package edu.floridatech.android.hellomoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HelloMoonFragment extends Fragment implements OnClickListener {

	private Button mAudioPlayButton;
	private Button mVideoPlayButton;
	private Button mPauseButton;
	private Button mStopButton;
	private Button mRestartButton;

	private MoonPlayer mPlayer = new MoonPlayer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_hello_moon, parent, false);
		SurfaceView view = (SurfaceView) v.findViewById(R.id.video_view);
		mPlayer.addCallBack(view);

		mAudioPlayButton = (Button) v
				.findViewById(R.id.hellomoon_audio_playButton);
		mVideoPlayButton = (Button) v
				.findViewById(R.id.hellomoon_video_playButton);
		// the listeners can also be instantiated as anonymous inner classes, if
		// you wish
		mAudioPlayButton.setOnClickListener(this);
		mVideoPlayButton.setOnClickListener(this);

		mStopButton = (Button) v.findViewById(R.id.hellomoon_stopButton);

		// the listeners can also be instantiated as anonymous inner classes, if
		// you wish
		mStopButton.setOnClickListener(this);

		mPauseButton = (Button) v.findViewById(R.id.hellomoon_pauseButton);
		mPauseButton.setOnClickListener(this);

		mRestartButton = (Button) v.findViewById(R.id.hellomoon_restartButton);
		mRestartButton.setOnClickListener(this);
		return v;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mPlayer.stop();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.hellomoon_audio_playButton:
			mPlayer.play(getActivity(), true);
			break;
		case R.id.hellomoon_pauseButton:
			mPlayer.pause();
			break;
		case R.id.hellomoon_restartButton:
			mPlayer.restart();
			break;
		case R.id.hellomoon_stopButton:
			mPlayer.stop();
			break;
		case R.id.hellomoon_video_playButton:
			mPlayer.play(getActivity(), false);
			break;
		}
	}
}
