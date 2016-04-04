package com.example.SupinFlouze;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.example.SupinFlouze.Adaptater.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     *
     */


    ListView listView ;
    TabHost mTabHost;
    int Supinflouze = 0;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        getActionBar().setTitle("SupinFlouz : " + Supinflouze);
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("JEU").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("SHOP").setContent(R.id.tab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("CLASSEMENT").setContent(R.id.tab3));

        mTabHost.setCurrentTab(0);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new ListViewAdaptater(this, new String[] { "data1",
                "data2" }));


    }

    public void SupinFlouzOnClick(View view){

        Supinflouze += 1;
        getActionBar().setTitle("SupinFlouze : "+Supinflouze);
    }




}
