package com.example.SupinFlouze.Bonus;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * com.example.SupinFlouze.Bonus
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class GameObject implements Serializable {

    ArrayList<Shop> data = new ArrayList<>();

    public GameObject() {
    }

   public Integer Supinflouze = 0;


    public Integer getSupinflouze() {
        return Supinflouze;
    }

    public void setSupinflouze(Integer supinflouze) {
        Supinflouze = supinflouze;
    }

    public ArrayList<Shop> getData() {
        return data;
    }

    public void setData(ArrayList<Shop> data) {
        this.data = data;
    }
}
