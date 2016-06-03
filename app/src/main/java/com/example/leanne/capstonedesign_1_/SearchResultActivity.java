package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Chloe on 4/13/2016.
 *
 */
public class SearchResultActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);

		initView();
	}

	private void initView() {

	}

	@Override
	public void onBackPressed() {
		this.finish();
		overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
	}
}
