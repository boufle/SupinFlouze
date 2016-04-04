package com.example.SupinFlouze.Adaptater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.SupinFlouze.Bonus.Shop;
import com.example.SupinFlouze.MyActivity;
import com.example.SupinFlouze.R;

import java.util.ArrayList;

public class ListViewAdaptater extends BaseAdapter {

    MyActivity context;
    ArrayList<Shop> data = new ArrayList<>();
    private static LayoutInflater inflater = null;

    public ListViewAdaptater(MyActivity context, ArrayList<Shop> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
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
        texttt.setText("Prix : " + data.get(position).getPrix() +" multi : " +  data.get(position).getUnitaire()*data.get(position).getCount() );

           vi.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shop item = (Shop) data.get(position);

               if (context.ope.testBuyable(item.getPrix())){

                   context.ope.removeflouzz(item.getPrix());
                    item.setCount(item.getCount() + 1);
                   Double value =  (item.getMultiplicateur() * item.getPrix());

                    item.setPrix(value.intValue());
                   notifyDataSetChanged();
                   notifyDataSetInvalidated();
                }
            }
        });



        return vi;
    }
}