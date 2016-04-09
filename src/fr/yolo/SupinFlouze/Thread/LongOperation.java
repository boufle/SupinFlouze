package fr.yolo.SupinFlouze.Thread;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.games.Games;
import fr.yolo.SupinFlouze.Adaptater.ListViewAdaptater;
import fr.yolo.SupinFlouze.Bonus.GameObject;
import fr.yolo.SupinFlouze.Bonus.Shop;
import fr.yolo.SupinFlouze.MyActivity;
import fr.yolo.SupinFlouze.R;

import java.util.*;

import static fr.yolo.SupinFlouze.utils.format;

/**
 * com.example.SupinFlouze.Thread
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class LongOperation  {
    Random r = new Random();

    public GameObject gameObject = new GameObject();
    private MyActivity myActivity;
    public   int finalCPM = 0 ;
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





                if(!MyActivity.debug) {
                    if (gameObject.getSupinflouze() >= 100000000 && !gameObject.hf1) {
                        gameObject.hf1 = true;
                        Games.Achievements.unlock(myActivity.mGoogleApiClient, "CgkIwreR5JUGEAIQAg");
                    }
                    if (CPM >= 1000000 && !gameObject.hf5) {
                        gameObject.hf5 = true;
                        Games.Achievements.unlock(myActivity.mGoogleApiClient, "CgkIwreR5JUGEAIQBg");
                    }


                    if (CPM != finalCPM) {
                        Games.Leaderboards.submitScore(myActivity.mGoogleApiClient, "CgkIwreR5JUGEAIQCg", CPM);

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
                                if (myActivity.ope.testBuyable(gameObject.getData().get( myActivity.listView.getPositionForView(ve)).getprixdata())){
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



                        if ((r.nextInt(150-1) + 1) == 2 && (gameObject.getData().get(9).getUnitaire()*gameObject.getData().get(9).getCount()) > finalCPM ){
                            AlertDialog alertDialog = new AlertDialog.Builder(myActivity).create();
                            alertDialog.setTitle("POLICE");
                            alertDialog.setMessage("Un procès furtif est apparu ");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Je prend !",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            gameObject.getData().get(9).count++;
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

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


    public void addflouzzsimple() {
        addflouzz(     gameObject.getData().get(0).getCount()* gameObject.getData().get(0).getUnitaire() );
    }
}
