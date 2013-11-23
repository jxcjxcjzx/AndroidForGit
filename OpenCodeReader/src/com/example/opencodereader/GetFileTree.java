package com.example.opencodereader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GetFileTree extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_file_tree);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_file_tree, menu);
		return true;
	}

}
