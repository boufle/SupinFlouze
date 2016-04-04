package com.example.SupinFlouze.Thread;

import android.os.AsyncTask;
import android.widget.Toast;
import com.example.SupinFlouze.Bonus.GameObject;
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

    public GameObject gameObject = new GameObject();
    private MyActivity myActivity;
      int finalCPM = 0 ;
    public LongOperation(GameObject gameObjectt,   MyActivity myActivity) {


        this.gameObject = gameObjectt;
        this.myActivity = myActivity;
        ;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int cont =0 ;
                int CPM = 0 ;
                for (Shop shop : gameObject.getData()) {
                    if(cont == 0){
                        cont=1;
                    }else {
                        int data=  ( shop.getCount()*shop.getUnitaire());
                        CPM +=data;
                        gameObject.Supinflouze+=data;

                    }


                }
                  finalCPM = CPM;
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myActivity.getActionBar().setTitle("SupinFlouz: " + gameObject.Supinflouze + " (+" + finalCPM + "$/s)");

                    }
                });

            }
        }, 0, 1000);//put here time 1000 milliseconds=1 second



    }

    public void addflouzz(Integer i) {
        gameObject.Supinflouze+=i;
        myActivity.getActionBar().setTitle("SupinFlouz: " + gameObject.Supinflouze + " (+" + finalCPM + "$/s)");

    }
    public void removeflouzz(Integer i) {
        gameObject.Supinflouze-=i;
        myActivity.getActionBar().setTitle("SupinFlouz: " + gameObject.Supinflouze + " (+" + finalCPM + "$/s)");

    }

    public boolean testBuyable(Integer i){

        if (gameObject.Supinflouze >= i){
            return true;
        }

        return false;

    }


    public void addflouzzsimple() {
        addflouzz(     gameObject.getData().get(0).getCount()* gameObject.getData().get(0).getUnitaire() );
    }
}
