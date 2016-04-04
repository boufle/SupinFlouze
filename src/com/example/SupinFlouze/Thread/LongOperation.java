package com.example.SupinFlouze.Thread;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.SupinFlouze.Adaptater.ListViewAdaptater;
import com.example.SupinFlouze.Bonus.GameObject;
import com.example.SupinFlouze.Bonus.Shop;
import com.example.SupinFlouze.MyActivity;
import com.example.SupinFlouze.R;

import java.util.*;

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
                        myActivity.getActionBar().setTitle("SupinFlouz: " + format(gameObject.Supinflouze) + " (" + format(finalCPM) + "$/s)");
                        ListViewAdaptater adat = (ListViewAdaptater) myActivity.listView.getAdapter();
                        try {

                            for (int i =  myActivity.listView.getFirstVisiblePosition(); i <=  myActivity.listView.getLastVisiblePosition(); i++) {
                                View ve=    myActivity.listView.getChildAt(i);

                                Button button = (Button) ve.findViewById(R.id.button);
                                if (myActivity.ope.testBuyable(gameObject.getData().get( myActivity.listView.getPositionForView(ve)).getPrix())){
                                    if(!button.isEnabled()){
                                        button.setEnabled(true);

                                    }

                                }else {

                                    if(button.isEnabled()){
                                        button.setEnabled(false);

                                    }
                                }
                            }
                        }catch (NullPointerException e){

                        }




                    }
                });

            }
        }, 0, 1000);//put here time 1000 milliseconds=1 second



    }

    public void addflouzz(Integer i) {
        gameObject.Supinflouze+=i;
        myActivity.getActionBar().setTitle("SupinFlouz: " + format(gameObject.Supinflouze) + " (" + format(finalCPM) + "$/s)");

    }
    public void removeflouzz(Integer i) {
        gameObject.Supinflouze-=i;
        myActivity.getActionBar().setTitle("SupinFlouz: " + format(gameObject.Supinflouze) + " (" + format(finalCPM) + "$/s)");

    }

    public boolean testBuyable(Integer i){

        if (gameObject.Supinflouze >= i){
            return true;
        }

        return false;

    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }
    public void addflouzzsimple() {
        addflouzz(     gameObject.getData().get(0).getCount()* gameObject.getData().get(0).getUnitaire() );
    }
}
