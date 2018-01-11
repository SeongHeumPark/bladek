package com.labs.bladek.rxjavaandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class OnClickFragment extends Fragment {
	private static final String TAG = OnClickFragment.class.getSimpleName();

	@BindView(R.id.btn_click_observer)
	Button button;

	public OnClickFragment() {
	}

	public static OnClickFragment newInstance() {
		return new OnClickFragment();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getClickEventObservable()
			.map(s -> "clicked")
			.subscribe(getObserver());
	}

	private Observable<View> getClickEventObservable() {
		return Observable.create(o ->
				button.setOnClickListener(o::onNext)
		);
	}

	private DisposableObserver<? super String> getObserver() {
		return new DisposableObserver<String>() {
			@Override
			public void onNext(String s) {
				Log.d(TAG, s);
			}

			@Override
			public void onError(Throwable e) {
				Log.e(TAG, e.getMessage());
			}

			@Override
			public void onComplete() {
				Log.d(TAG, "complete");
			}
		};
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_on_click, container, false);
		ButterKnife.bind(view);

		return view;
	}
}
