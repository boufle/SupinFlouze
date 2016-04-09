package fr.yolo.SupinFlouze.Bonus;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * com.example.SupinFlouze.Bonus
 * Created by Theo on 04/04/2016 for SupinFlouze.
 */
public class GameObject implements Serializable {
    static final long serialVersionUID = 4210L;

    ArrayList<Shop> data = new ArrayList<>();
    public int countclic = 0;

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



    public Boolean hf1 = false;
    public Boolean hf2 = false;
    public Boolean hf3 = false;
    public Boolean hf4 = false;
    public Boolean hf5 = false;
    public Boolean hf6 = false;

}
