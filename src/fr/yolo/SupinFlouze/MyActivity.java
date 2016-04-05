package fr.yolo.SupinFlouze;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;
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


    public  ListView listView ;

   public LongOperation ope = null;
    private GoogleApiClient mGoogleApiClient;

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
        mGoogleApiClient.disconnect();
    }

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES) // Games
                 .addScope(Drive.SCOPE_APPFOLDER) // SavedGames
                .build();

        onConnected(savedInstanceState);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    public void SupinFlouzOnClick(View view){
        ope.addflouzzsimple();
    }


    @Override
    public void onConnected(Bundle bundle) {
        super.onCreate(bundle);
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

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        mGoogleApiClient.connect();
    }
}
