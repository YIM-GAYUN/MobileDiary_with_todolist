package smu.example.hwmydiary;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MemoMainActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);    
    	setContentView(R.layout.memoactivity_main);

    	TabHost tabHost = getTabHost();
    	TabHost.TabSpec spec;

    	Intent intent = new Intent().setClass(this, ShowMyData.class);

    	spec = tabHost.newTabSpec("show").setIndicator("메모 목록보기").setContent(intent);
    	tabHost.addTab(spec);

    	intent = new Intent().setClass(this, WriteDiaryActivity.class);
    	spec = tabHost.newTabSpec("write").setIndicator("메모 작성하기").setContent(intent);
    	tabHost.addTab(spec);    
    	

    	tabHost.setCurrentTab(0);
    }
}