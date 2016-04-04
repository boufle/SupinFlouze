package com.example.SupinFlouze;

import com.example.SupinFlouze.Bonus.Shop;

import java.util.ArrayList;

/**
 * com.example.SupinFlouze
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class utils {



    public static ArrayList<Shop> getitem(){

       //String name, Integer count, Integer prix, Integer multiplicateurprix, nombre
        ArrayList <Shop> list = new ArrayList<>();
        list.add(new Shop("JPO", 0,50,2,5));
        list.add(new Shop("Vucher", 0,100,3,10));
        list.add(new Shop("Spring Break", 0,500,4,20));
        list.add(new Shop("Spécialisation", 0,1000,5,35));
        list.add(new Shop("Eleve", 0,6500,10,100));
        return list;
    }
}
