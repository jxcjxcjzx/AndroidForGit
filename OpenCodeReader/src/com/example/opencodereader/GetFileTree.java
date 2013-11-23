package com.example.opencodereader;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GetFileTree extends Activity {

	static String ScAddr = String.valueOf(Environment.getExternalStorageDirectory())+"//"+"data"+"//"+"SC";   // we set the default address for the source code
	private ArrayList<String> items = null;
    private ArrayList<String> paths = null;
    private String rootPath = "/";
    private TextView mPath;
    private ListView FileList;
    private String ItemSelected;
    // for back navigate
    private ArrayList<String> PathHistory;
	
    // later we will make this work
    private void processExtraData(){
    	  Intent intent = getIntent();
    	  //use the data received here
    	  
    	}
    
    
    public void onBackPressed() {
        // 如果是返回键,直接返回到桌面
            // handle with the back key event
    		if(!PathHistory.isEmpty()){  
    			PathHistory.remove(PathHistory.size()-1);
    		}
            if(!PathHistory.isEmpty()){            	            	
            	getFileDir(PathHistory.get(PathHistory.size()-1));
                AdjustDir();
            }
            else{
            	// in case something wrong
            	getFileDir(ScAddr);
                AdjustDir();
            }
        }


    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_file_tree);
		// process the extra data, if needed
		//processExtraData();
		PathHistory = new ArrayList<String>();
		// add the first addr
		
        //FileList = (ListView)findViewById(R.layout.activity_get_file_tree);   // this way is recommended
        FileList = new ListView(this);
     
        getFileDir(ScAddr);
        AdjustDir();
        
		// set a listener
        FileList.setOnItemClickListener(
        		new OnItemClickListener(){
        			@Override
        			public void onItemClick(AdapterView<?> parent, View arg1, int pos,long arg3) {  
        				ItemSelected = parent.getItemAtPosition(pos).toString();
        				
        				File file  = new File(paths.get(items.indexOf(ItemSelected)));
        				if(file.exists()){
        					// if the file exists , we continue with the whether directory judgenment
        					if(file.isDirectory())
        					{
        						// then we add the files under the directory to the adapter
        						// our listview use
        						getFileDir(paths.get(items.indexOf(ItemSelected)));        
        						AdjustDir();
        					}
        					else{
        						// then we should display the text on the screen
        						
        						Intent intent = new Intent(GetFileTree.this,CodeLoader.class);
        						// pass the parament to the display program
        						intent.putExtra("FileToDisplay", paths.get(items.indexOf(ItemSelected)));
        						startActivity(intent);
        					}
        				}
        		   
        				
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

        if(items!=null&&paths!=null){
        	
        }
        else{
	        items = new ArrayList<String>();
	        paths = new ArrayList<String>();
        }
        items.clear();
        paths.clear();
        
	    File file = new File(filePath);
	    if(file.exists()&&file.isDirectory()){
	    	// the above judgment actually is 
	    	// not needed, but we will do this as though
	    	
		    File[] files = file.listFiles();
		    for(File fileTemp :files) {
		            items.add(fileTemp.getName());
		            paths.add(fileTemp.getPath());
		    }
		    // this contain function of arraylist may cause some problems itself
		    if(PathHistory.contains(filePath)){
		    	// do not add
		    }
		    else{
		    	// add the path to hsitory in case the user return
		    	PathHistory.add(filePath);
		    	
		    	// tell the user the current path
				GetFileTree.this.setTitle(filePath);
		    }
	    }
	    else{
	    	
	    }
	    
       
    }
	
	
	private void AdjustDir(){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
		for(String stringtmp:items){
			adapter.add(stringtmp);
			FileList.setAdapter(adapter);
		}
		
	}
	
	
	

}
