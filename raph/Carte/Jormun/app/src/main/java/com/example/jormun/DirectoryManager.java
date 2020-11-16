package com.example.jormun;

import android.os.Environment;
import android.util.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class DirectoryManager {
    public static final String DIR_hero = "Hero";

    public static final String DIR_village = "Village";

    public static final String DIR_monster = "Monster";

    public static final String DIR_team = "Team";

    public static final String DIR_structure = "Structure";

    public static final String DIR_Game = "Jormun";

    private String sPrevSavedFilePath;

    private String sPath;
    private String sGameDir;
    private String sHeroDir;
    private String sVillageDir;
    private String sMonsterDir;
    private String sTeamDir;
    private String sStructure;

    public DirectoryManager(){
        SetEnvironment();
    }
    public void SetEnvironment(){
        File flTemp;
        flTemp = new File(Environment.getExternalStorageDirectory()+File.separator+"Jormun");
        if(!flTemp.exists() || !flTemp.isDirectory()){
            try{

                flTemp.mkdir();
            }
            catch (Exception e)
            {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                e.printStackTrace();
            }
        }
        setDirGame(flTemp.getAbsolutePath());
        BuildSubDirectory();

    }
    public void BuildSubDirectory(){
        String[] a_sDirName = {DIR_monster,DIR_hero,DIR_village,DIR_team,DIR_structure};

        File flTemp;

        for(String sDirName:a_sDirName){
            flTemp = new File(getDirGame()+File.separator+sDirName);
            if(!flTemp.exists() || !flTemp.isDirectory()){
                flTemp.mkdir();
            }
            switch (sDirName){
                case DIR_monster:
                    setsMonsterDir(flTemp.getAbsolutePath());
                    break;
                case DIR_hero:
                    setsHeroDir(flTemp.getAbsolutePath());
                    break;
                case DIR_village:
                    setsVillageDir(flTemp.getAbsolutePath());
                    break;
                case DIR_team:
                    setsTeamDir(flTemp.getAbsolutePath());
                    break;
                case DIR_structure:
                    setsStructure(flTemp.getAbsolutePath());
                    break;
            }
        }
    }
    public void CheckFile(String sFile){
        File flBuild = new File(sFile);

        if(!flBuild.exists()  || !flBuild.isFile()){
            try {
                flBuild.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public JSONObject ReadJson(String sFile) {
        String sContent;
        String sLine;
        System.out.println(sFile);
        JSONObject jsnTemp;

        try {

            BufferedReader bufrdrCurrent = new BufferedReader(new FileReader(new File(sFile)));
            sContent="";
            while ((sLine=bufrdrCurrent.readLine())!=null){
                sContent+=sLine;
            }
            jsnTemp=new JSONObject(sContent);
            return jsnTemp;
        }catch(FileNotFoundException e){
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean saveJson(String sToken, String sType, JSONObject jsnTemp) {
        File jsnflSave;

        FileWriter flwtrJson;

        Writer jsnwtrJSON;

        String sPath;

        Boolean bSaved;

        bSaved=false;
        sPath = getsDirectory(sType);
        jsnflSave = new File(sPath+File.separator+sToken+".json");
        try {
            if (!jsnflSave.exists() || !jsnflSave.isFile()) {
                jsnflSave.createNewFile();
            }
            jsnwtrJSON = null;

            jsnwtrJSON = new BufferedWriter(new FileWriter(jsnflSave));
            jsnwtrJSON.write(jsnTemp.toString());
            jsnwtrJSON.close();
            setsPrevSavedFilePath(jsnflSave.getAbsolutePath());
            bSaved=true;
        }catch (IOException e){
            return bSaved;
        }

        return bSaved;
    }
    private String getsDirectory(String sType){
        String sPath;
        switch (sType){
            case DIR_hero:
                sPath = getsHeroDir();
                break;
            case DIR_monster:
                sPath = getsMonsterDir();
                break;
            case DIR_village:
                sPath = getsVillageDir();
                break;
            case DIR_team:
                sPath = getsTeamDir();
                break;
            case DIR_structure:
                sPath = getsStructure();
                break;
            default:
                sPath="";
                break;
        }
        return sPath;
    }
    public String getsPath() {
        return sPath;
    }

    public void setsPath(String sPath) {
        this.sPath = sPath;
    }

    public String getDirGame() {
        return sGameDir;
    }

    public void setDirGame(String dirGame) {
        this.sGameDir = dirGame;
    }

    public String getsHeroDir() {
        return sHeroDir;
    }

    public void setsHeroDir(String sHeroDir) {
        this.sHeroDir = sHeroDir;
    }

    public String getsVillageDir() {
        return sVillageDir;
    }

    public void setsVillageDir(String sVillageDir) {
        this.sVillageDir = sVillageDir;
    }

    public String getsMonsterDir() {
        return sMonsterDir;
    }

    public void setsMonsterDir(String sMonsterDir) {
        this.sMonsterDir = sMonsterDir;
    }

    public String getsTeamDir() {
        return sTeamDir;
    }

    public void setsTeamDir(String sTeamDir) {
        this.sTeamDir = sTeamDir;
    }

    public String getsPrevSavedFilePath() {
        return sPrevSavedFilePath;
    }

    public void setsPrevSavedFilePath(String sPrevSavedFilePath) {
        this.sPrevSavedFilePath = sPrevSavedFilePath;
    }

    public String getsStructure() {
        return sStructure;
    }

    public void setsStructure(String sStructure) {
        this.sStructure = sStructure;
    }

}
