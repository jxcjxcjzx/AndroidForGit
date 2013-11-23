package com.example.opencodereader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class CodeLoader extends Activity {

	
	private TextView tv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tv = new TextView(this);		
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		Intent intent = getIntent();
		try {
			doRaw(intent.getExtras().getString("FileToDisplay"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContentView(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.code_loader, menu);
		return true;
	}
	
	
	// here are some functions written for loading stream contents from file
	private void doRaw(String filePath) throws FileNotFoundException{
		File file = new File(filePath);
		if(file.exists()){
			InputStream is = new FileInputStream(file);  
			try{
				doRead(is);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
		// tell the user the current path
		CodeLoader.this.setTitle(filePath);
	}
	
	private void doRead(InputStream is) throws IOException{
		DataInputStream dis = new DataInputStream(is);
		byte[] buffer = new byte[is.available()];
		dis.readFully(buffer);
		tv.setText(new String(buffer));
		dis.close();
		is.close();
	}

}
