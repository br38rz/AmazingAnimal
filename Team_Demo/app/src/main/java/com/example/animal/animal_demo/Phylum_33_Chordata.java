package com.example.animal.animal_demo;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Kevin on 2017/8/16.
 */

public class Phylum_33_Chordata extends BmobObject {
    private String Ano;
    private String Aname;
    private BmobFile AImage;
    private BmobFile AGIF;
    private String AKingdom;
    private String APhyluM;
    private String AClass;
    private String AOrder;
    private String AFamily;
    private String AGenus;
    private String ASpecies;
    private String AIntroduction;

    public String getAno() {
        return Ano;
    }

    public BmobFile getAImage() {
        return AImage;
    }

    public void setAImage(BmobFile AImage) {
        this.AImage = AImage;
    }

    public void setAno(String ano) {
        Ano = ano;
    }

    public String getAname() {
        return Aname;
    }

    public void setAname(String aname) {
        Aname = aname;
    }

    public String getAKingdom() {
        return AKingdom;
    }

    public void setAKingdom(String AKingdom) {
        this.AKingdom = AKingdom;
    }

    public String getAPhyluM() {
        return APhyluM;
    }

    public void setAPhyluM(String APhyluM) {
        this.APhyluM = APhyluM;
    }

    public String getAClass() {
        return AClass;
    }

    public void setAClass(String AClass) {
        this.AClass = AClass;
    }

    public String getAOrder() {
        return AOrder;
    }

    public void setAOrder(String AOrder) {
        this.AOrder = AOrder;
    }

    public String getAFamily() {
        return AFamily;
    }

    public void setAFamily(String AFamily) {
        this.AFamily = AFamily;
    }

    public String getAGenus() {
        return AGenus;
    }

    public void setAGenus(String AGenus) {
        this.AGenus = AGenus;
    }

    public String getASpecies() {
        return ASpecies;
    }

    public void setASpecies(String ASpecies) {
        this.ASpecies = ASpecies;
    }

    public String getAIntroduction() {
        return AIntroduction;
    }

    public void setAIntroduction(String AIntroduction) {
        this.AIntroduction = AIntroduction;
    }

    public BmobFile getAGIF() {
        return AGIF;
    }

    public void setAGIF(BmobFile AGIF) {
        this.AGIF = AGIF;
    }
}
