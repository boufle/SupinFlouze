package com.example.SupinFlouze;

import android.content.Context;
import com.example.SupinFlouze.Bonus.GameObject;
import com.example.SupinFlouze.Bonus.Shop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * com.example.SupinFlouze
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class utils {



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
        list.add(new Shop("Putaclic", 1,5,1.4,1));
        list.add(new Shop("JPO", 0,50,1.6,5));
        list.add(new Shop("Vucher", 0,100,1.8,10));
        list.add(new Shop("Spring Break", 0,500,2.0,20));
        list.add(new Shop("Spécialisation", 0,1000,2.2,35));
        list.add(new Shop("Eleve", 0,6500,2.1,100));
        game.setData(list);
        return game;
    }
}
