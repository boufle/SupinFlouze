package fr.yolo.SupinFlouze;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.plus.Plus;
import fr.yolo.SupinFlouze.Adaptater.*;
import fr.yolo.SupinFlouze.Bonus.Shop;
import fr.yolo.SupinFlouze.Thread.LongOperation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static fr.yolo.SupinFlouze.utils.getitem;

public class MyActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    /**
     * Called when the activity is first created.
     *
     */


    public static Boolean debug = Boolean.FALSE;
    public  ListView listView ;

   public LongOperation ope = null;
    public GoogleApiClient mGoogleApiClient;

    @Override
    protected void onStop() {

        super.onStop();

        FileOutputStream fos = null;
        try {
            fos = getApplicationContext().openFileOutput("save", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ope.gameObject);
            os.close();
            fos.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }catch (NullPointerException e){

        }

        if(!debug){
            mGoogleApiClient.disconnect();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if(debug){
            onConnected(savedInstanceState);
        }else {
            setContentView(R.layout.playservice);

            mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(Games.API).addScope(Games.SCOPE_GAMES) // Games
                    .setViewForPopups(findViewById(android.R.id.content)) // SavedGames
                    .build();

        }



    }
    @Override
    protected void onStart() {
        super.onStart();
        if(!debug){
            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                Log.w("sup",
                        "GameHelper: client was already connected on onStart()");
            } else {
                Log.d("sup","Connecting client.");
             mGoogleApiClient.connect();
            }
        }

    }

    public void SupinFlouzOnClick(View view){
        if(ope.gameObject.countclic >=1000000  && !ope.gameObject.hf4){

                ope.gameObject.hf4 = true;
                Games.Achievements.unlock(this.mGoogleApiClient, "CgkIwreR5JUGEAIQBQ");

        }
        ope.gameObject.countclic +=1;

       TextView txt = (TextView) findViewById(R.id.hint);
        txt.setVisibility(View.GONE);
        ope.addflouzzsimple();
    }


    @Override
    public void onConnected(Bundle bundle) {
        //super.onCreate(bundle);
        Log.d("sup","OUIII");
        setContentView(R.layout.main);
        TabHost mTabHost;

        if(ope== null){
            ope = new LongOperation(getitem(getApplicationContext()), this);
        }

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();


        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("JEU").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("SHOP").setContent(R.id.tab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Menu").setContent(R.id.tab3));

        mTabHost.setCurrentTab(0);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new ListViewAdaptater(this,ope.gameObject ));
        ListView listView1 = (ListView) findViewById(R.id.listother);
        listView1.setAdapter(new ListViewAdaptaterother(this  ));
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("sup", String.valueOf(connectionResult.getErrorCode()));
       // Log.d("sup",connectionResult.getErrorMessage());

      //  GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
        if (connectionResult.hasResolution()) {
            Log.d("MyApp",connectionResult.toString());
            try {

                connectionResult.startResolutionForResult(this, 1001);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("sup", String.valueOf(requestCode +" " +resultCode));
        if (requestCode == 1001) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() && !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }

        }
    }
}
