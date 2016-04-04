package com.example.SupinFlouze;

import com.example.SupinFlouze.Bonus.Shop;

import java.util.ArrayList;

/**
 * com.example.SupinFlouze
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class utils {



    public static ArrayList<Shop> getitem(){

        Shop obj   = new Shop("Eleve", 0,2,3);
        Shop obj1   = new Shop("Eleve1", 0,2,3);
        ArrayList <Shop> list = new ArrayList<>();
        list.add(obj);   list.add(obj1);
        return list;
    }
}
