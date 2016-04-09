package fr.yolo.SupinFlouze.Adaptater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.games.Games;
import fr.yolo.SupinFlouze.Bonus.GameObject;
import fr.yolo.SupinFlouze.Bonus.Shop;
import fr.yolo.SupinFlouze.MyActivity;
import fr.yolo.SupinFlouze.R;
import fr.yolo.SupinFlouze.utils;

import java.util.ArrayList;

public class ListViewAdaptaterother extends BaseAdapter {

    MyActivity context;
    ArrayList<String> data = new ArrayList<>();
    private static LayoutInflater inflater = null;

    public ListViewAdaptaterother(MyActivity context ) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data =                utils.Othermebu();
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.rowother, null);
        Button text = (Button) vi.findViewById(R.id.button2other);
        text.setText(data.get(position));
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    context.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(context.mGoogleApiClient,
                            "CgkIwreR5JUGEAIQAA"), 1);
                }else if(position == 1){
                    context.startActivityForResult(Games.Achievements.getAchievementsIntent(
                            context.mGoogleApiClient), 10001);
                }else if(position == 2){
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Disclamer");
                    alertDialog.setMessage("Ceci est une application a but pédagogique, elle ne represente pas des faits/actes reels.Toutes similitudes avec des entitée vivantes ou mortes ne serait que pure coïncidence" +
                            "\n\nMore quieter you are more able to hear");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "J'ai pas lu !",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else if(position == 3){
                    final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            }
        });

        return vi;
    }
}