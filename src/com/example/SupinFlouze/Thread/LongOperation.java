package com.example.SupinFlouze.Thread;

import android.os.AsyncTask;
import android.widget.Toast;
import com.example.SupinFlouze.Bonus.Shop;
import com.example.SupinFlouze.MyActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * com.example.SupinFlouze.Thread
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class LongOperation  {

    private  ArrayList<Shop> shops;
    private MyActivity myActivity;
    private Integer flouzz = 0 ;
    Integer Supinflouze = 0;

    public LongOperation(ArrayList<Shop> shops,   MyActivity myActivity) {


        this.shops = shops;
        this.myActivity = myActivity;
        ;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int cont =0 ;
                for (Shop shop : shops) {
                    if(cont == 0){

                    }else {
                        flouzz+=( shop.getCount()*shop.getUnitaire());
                    }


                }
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myActivity.getActionBar().setTitle("SupinFlouz : " + flouzz);

                    }
                });

            }
        }, 0, 1000);//put here time 1000 milliseconds=1 second



    }

    public void addflouzz(Integer i) {
        flouzz+=i;
        myActivity.getActionBar().setTitle("SupinFlouz : " + flouzz);

    }
    public void removeflouzz(Integer i) {
        flouzz-=i;
        myActivity.getActionBar().setTitle("SupinFlouz : " + flouzz);

    }

    public boolean testBuyable(Integer i){

        if (flouzz >= i){
            return true;
        }

        return false;

    }


    public void addflouzzsimple() {
        addflouzz(    shops.get(0).getCount()*shops.get(0).getUnitaire() );
    }
}
