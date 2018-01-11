package com.labs.bladek.rxjavaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends RxAppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	@BindView(R.id.text_view)
	TextView textView;

	private Unbinder unbinder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		unbinder = ButterKnife.bind(this);

		Observable.just("Hello. rx world!")
			.compose(bindToLifecycle())
			.subscribe(textView::setText);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (unbinder != null) {
			unbinder.unbind();
		}
	}
}
