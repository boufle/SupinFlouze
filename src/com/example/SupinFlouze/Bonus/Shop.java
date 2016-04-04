package com.example.SupinFlouze.Bonus;

import java.io.Serializable;

/**
 * com.example.SupinFlouze.Bonus
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class Shop implements Serializable {

    public String name ;
    public Integer count;
    public Integer prix;
    public Integer Multiplicateur;


    public Shop(String name, Integer count, Integer prix, Integer multiplicateur) {
        this.name = name;
        this.count = count;
        this.prix = prix;
        Multiplicateur = multiplicateur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getMultiplicateur() {
        return Multiplicateur;
    }

    public void setMultiplicateur(Integer multiplicateur) {
        Multiplicateur = multiplicateur;
    }
}
