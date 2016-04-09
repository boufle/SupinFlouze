package fr.yolo.SupinFlouze;

import android.content.Context;
import fr.yolo.SupinFlouze.Bonus.GameObject;
import fr.yolo.SupinFlouze.Bonus.Shop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * com.example.SupinFlouze
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class utils {

    public static ArrayList<String> Othermebu(){
        ArrayList <String> list = new ArrayList<>();
        list.add("HightScore");
        list.add("Achievement");
        list.add("Disclamer");
        list.add("Notez l'application");
        return list;
    }

    public static GameObject getitem(Context context){
        GameObject game = new GameObject();

        try {
            FileInputStream fis = context.openFileInput("save");
            ObjectInputStream is = new ObjectInputStream(fis);
            game = (GameObject) is.readObject();
            is.close();
            fis.close();
        } catch (ClassNotFoundException e) {
          return basegame();
        } catch (IOException e) {
            return basegame();
        }
        return game;

       //String name, Integer count, Integer prix, Integer multiplicateurprix, nombre


    }
    public static GameObject basegame( ){
        GameObject game = new GameObject();

        ArrayList <Shop> list = new ArrayList<>();
        list.add(new Shop("Putaclic", 1,50,1.2,1));
        list.add(new Shop("JPO", 0,250,1.2,2));
        list.add(new Shop("Vucher/ticket", 0,1000,1.3,10));
        list.add(new Shop("Spring Break", 0,2500,1.4,15));
        list.add(new Shop("Spécialisation", 0,4500,1.5,20));
        list.add(new Shop("KKWS", 0,8000,1.6,22));
        list.add(new Shop("SIIS/BBDE", 0,11000,1.7,25));
        list.add(new Shop("Propagande", 0,20000,1.8,43));
        list.add(new Shop("Eleve", 0,25000,1.9,60));
        list.add(new Shop("Procès", 0,600,2.0,-100));
        list.add(new Shop("Mug/Pins", 0,40000,1.7,100));
        list.add(new Shop("Ferrari", 0,80000,2.1,220));
        list.add(new Shop("Campus", 0,150000,2.5,500));
        game.setData(list);
        return game;
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

        long truncated = value / (divideBy / 100); //the number part of the output times 10
        boolean hasDecimal = truncated < 1000 && (truncated / 100d) != (truncated / 100);
        return hasDecimal ? (truncated / 100d) + suffix : (truncated / 100) + suffix;
    }
}
