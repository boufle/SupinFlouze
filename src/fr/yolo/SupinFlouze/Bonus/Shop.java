package fr.yolo.SupinFlouze.Bonus;

import java.io.Serializable;

/**
 * com.example.SupinFlouze.Bonus
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class Shop implements Serializable {

    public String name ;
    public Integer count;
    public Integer prix;
    public Double Multiplicateur;
    private Integer unitaire;


    public Shop(String name, Integer count, Integer prix, Double multiplicateur,Integer unitaire) {
        this.name = name;
        this.count = count;
        this.prix = prix;
        Multiplicateur = multiplicateur;
        this.unitaire = unitaire;


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

    public Integer getprixdata(){
        Double cefe =Math.floor(this.getPrix() + (this.getPrix() * this.getCount() *0.1)* this.getMultiplicateur()  );

        return   cefe .intValue() ;

    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Double getMultiplicateur() {
        return Multiplicateur;
    }

    public void setMultiplicateur(Double multiplicateur) {
        Multiplicateur = multiplicateur;
    }

    public Integer getUnitaire() {
        return unitaire;
    }

    public Integer getprixdataone() {
        Double cefe =Math.floor(this.getPrix() + (this.getPrix() * (this.getCount()+1) *0.1)* this.getMultiplicateur()  );

        return   cefe .intValue() ;
    }
}
