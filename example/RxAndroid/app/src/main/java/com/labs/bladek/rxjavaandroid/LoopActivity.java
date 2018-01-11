package com.labs.bladek.rxjavaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class LoopActivity extends AppCompatActivity {
	private static final String TAG = LoopActivity.class.getSimpleName();

	private Iterable<String> samples = Arrays.asList(
		"banana", "orange", "apple", "apple mango", "melon", "watermelon");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loop);

		ButterKnife.bind(this);
	}

	@OnClick(R.id.btn_loop)
	void loop() {
		Log.d(TAG, ">>>>> get an apple :: java");
		for (String s : samples) {
			if (s.contains("apple")) {
				Log.d(TAG, s);
				return;
			}
		}
	}

	@OnClick(R.id.btn_loop2)
	void loop2() {
		Log.d(TAG, ">>>>> get an apple :: rx 2.x");
		Observable.fromIterable(samples)
			.filter(s -> s.contains("apple"))
			.first("Not Found")
			.subscribe(s -> Log.d(TAG, s));
	}
}
