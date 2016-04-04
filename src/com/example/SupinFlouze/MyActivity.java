package com.example.SupinFlouze;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.example.SupinFlouze.Adaptater.*;
import com.example.SupinFlouze.Bonus.Shop;
import com.example.SupinFlouze.Thread.LongOperation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.example.SupinFlouze.utils.getitem;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     *
     */


    public  ListView listView ;

   public LongOperation ope = null;

    @Override
    protected void onStop() {


        FileOutputStream fos = null;
        try {
            fos = getApplicationContext().openFileOutput("save", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ope.gameObject);
            os.close();
            fos.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }


        super.onStop();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TabHost mTabHost;

          ope = new LongOperation(getitem(getApplicationContext()), this);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("JEU").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("SHOP").setContent(R.id.tab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Disclamer").setContent(R.id.tab3));

        mTabHost.setCurrentTab(0);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new ListViewAdaptater(this,ope.gameObject ));





    }

    public void SupinFlouzOnClick(View view){
        ope.addflouzzsimple();
    }




}
