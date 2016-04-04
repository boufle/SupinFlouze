package com.example.SupinFlouze;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.example.SupinFlouze.Adaptater.*;
import com.example.SupinFlouze.Bonus.Shop;
import com.example.SupinFlouze.Thread.LongOperation;

import java.util.ArrayList;

import static com.example.SupinFlouze.utils.getitem;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     *
     */


    ListView listView ;

    LongOperation ope = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TabHost mTabHost; ArrayList<Shop> item = getitem();

          ope = new LongOperation(item, this);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("JEU").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("SHOP").setContent(R.id.tab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("CLASSEMENT").setContent(R.id.tab3));

        mTabHost.setCurrentTab(0);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new ListViewAdaptater(this,item ));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shop item = (Shop) listView.getAdapter().getItem(position);

                if (ope.testBuyable(item.getPrix())){

                    ope.removeflouzz(item.getPrix());
                    item.setCount(item.getCount() + 1);
                    item.setPrix(item.getMultiplicateur() * item.getPrix());
                }

            }
        });

    }

    public void SupinFlouzOnClick(View view){
        ope.addflouzz(1);
    }




}
