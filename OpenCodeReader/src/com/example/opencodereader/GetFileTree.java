package com.example.opencodereader;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GetFileTree extends Activity {

	
	private ArrayList<String> items = null;
    private ArrayList<String> paths = null;
    private String rootPath = "/";
    private TextView mPath;
    private ListView FileList;
    private String ItemSelected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_file_tree);
		
        //FileList = (ListView)findViewById(R.layout.activity_get_file_tree);
        FileList = new ListView(this);
        // set a listener
      
        
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
        // for the adapter to add several items
        adapter.add("one");
        adapter.add("two");
        adapter.add("three");
        FileList.setAdapter(adapter);     
        
		
        FileList.setOnItemClickListener(
        		new OnItemClickListener(){
        			@Override
        			public void onItemClick(AdapterView<?> parent, View arg1, int pos,long arg3) {  
        				ItemSelected = parent.getItemAtPosition(pos).toString();
        				// toast debugging code
        				Toast.makeText(GetFileTree.this, ItemSelected, Toast.LENGTH_SHORT).show(); 
        			}	
        });
        
        
        setContentView(FileList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_file_tree, menu);
		return true;
	}
	
	
	// get the file directory
	private void getFileDir(String filePath) {
        mPath.setText(filePath);
         
        items = new ArrayList<String>();
        paths = new ArrayList<String>();
        File file = new File(filePath);
        File[] files = file.listFiles();
        if(!filePath.equals(rootPath)) {
            items.add("Back To " + rootPath);
            paths.add(rootPath);
            items.add("Back to ../");
            paths.add(file.getParent());
        }
        for(File fileTemp :files) {
            items.add(fileTemp.getName());
            paths.add(fileTemp.getPath());
        }
      
    }
	
	
	
	
	

}
