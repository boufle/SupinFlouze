package fr.yolo.SupinFlouze.Adaptater;

import android.content.Context;
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

import java.util.ArrayList;

import static fr.yolo.SupinFlouze.utils.format;

public class ListViewAdaptater extends BaseAdapter {

    MyActivity context;
    private GameObject data1;
    ArrayList<Shop> data = new ArrayList<>();
    private static LayoutInflater inflater = null;

    public ListViewAdaptater(MyActivity context, GameObject data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        data1 = data;
        this.data = data.getData();
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
            vi = inflater.inflate(R.layout.row, null);
        TextView text = (TextView) vi.findViewById(R.id.name);
        text.setText(data.get(position).getName());

        TextView textt = (TextView) vi.findViewById(R.id.count);
        textt.setText(data.get(position).getCount().toString());

        TextView texttt = (TextView) vi.findViewById(R.id.data);
        if(position ==0){
            texttt.setText("" + format(data.get(position).getprixdata() ) + "$ / " + format(data.get(position).getCount()*data.get(position).getUnitaire()) + "$/clic ->" +  format(data.get(position).getUnitaire()*(data.get(position).getCount()+1)) + "$/clic" );

        }else {
            texttt.setText("" + format(data.get(position).getprixdata()) + "$ / " + format(data.get(position).getCount()*data.get(position).getUnitaire()) + "$/s ->" +  format(data.get(position).getUnitaire()*(data.get(position).getCount()+1)) + "$/s" );
        }


            Button button = (Button) vi.findViewById(R.id.button);
            if (context.ope.testBuyable(data.get(position).getprixdata())){
                if(!button.isEnabled()){
                    button.setEnabled(true);

                }

            }else {

                if(button.isEnabled()){
                    button.setEnabled(false);

                }
            }
           vi.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shop item = (Shop) data.get(position);

               if (context.ope.testBuyable(item.getprixdata())){

                   context.ope.removeflouzz(item.getprixdata());
                   item.setCount(item.getCount() + 1);
                  // Double value =  (item.getMultiplicateur() * item.getPrix());
                  // item.setPrix(value.intValue());




                   if(item.getName().equals("Procès")  && !data1.hf2){
                       if(item.getCount()>= 50){
                           data1.hf2 = true;
                           Games.Achievements.unlock(context.mGoogleApiClient, "CgkIwreR5JUGEAIQAw");
                       }
                   }
                   if(item.getName().equals("Campus")  && !data1.hf3){
                       if(item.getCount()>= 30){
                           data1.hf3 = true;
                           Games.Achievements.unlock(context.mGoogleApiClient, "CgkIwreR5JUGEAIQBA");
                       }
                   }
                   if(item.getName().equals("JPO")  && !data1.hf6){
                       if(item.getCount()>= 10000){
                           data1.hf6 = true;
                           Games.Achievements.unlock(context.mGoogleApiClient, "CgkIwreR5JUGEAIQCQ");
                       }
                   }



                   notifyDataSetChanged();
                   notifyDataSetInvalidated();
                }
            }
        });



        return vi;
    }
}