package com.example.jormun;

import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class Hero {
    public static String sCLASS_APPRENTIS = "apprenti";
    public static String sCLASS_VOLEUR = "voleur";
    public static String sCLASS_MERCENAIRE = "mercenaire";

    String Name;

    String description;
    String[] a_sClass = new String[2];

    int iLevel;
    int iMaxHP;
    int iHP;
    int iTotDef;
    int iTotAtk;

    ArrayList<String> al_sChosenSkills = new ArrayList<>(4);
    ArrayList<JSONObject> al_jsnCompetence = new ArrayList<>();
    ArrayList<String> al_sFullSkillList = new ArrayList<>();
    ArrayList<JSONObject> al_jsnInventaire = new ArrayList<>();
    ArrayList<String> al_jsnClass = new ArrayList<>(2);

    public Hero() {

    }


    public void LoadHero(JSONObject jsnO)
    {

    }
    public void SaveHero(DirectoryManager jsmgrCurrent,String sPath)
    {
        JSONArray jsnTempArray;
        JSONObject jsnHero;
        JSONObject jsnCurrent;

        String sFullPath = "."+ Environment.getExternalStorageDirectory()+File.separator+DirectoryManager.DIR_Game+File.separator+DirectoryManager.DIR_hero+ File.separator+sPath;

        jsmgrCurrent.CheckFile(sFullPath);
        jsnCurrent = jsmgrCurrent.ReadJson(sFullPath);
        jsnHero = new JSONObject();
        try {
            jsnHero.put("description","Hero player");
            jsnHero.put("iLevel",iLevel);
            jsnTempArray = new JSONArray();
            jsnTempArray.put(a_sClass[0]);
            jsnTempArray.put(a_sClass[1]);
            jsnHero.put("classe",jsnTempArray);
            jsnHero.put("iMaxHP",iMaxHP);
            jsnHero.put("iHP",iHP);
            jsnHero.put("iTotDef",iTotDef);
            jsnHero.put("iTotAtk",iTotAtk);
            //jsnCurrent.put("Name",jsnHero);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public Hero(String sName,String sClass1,String sClass2){

    }

}
