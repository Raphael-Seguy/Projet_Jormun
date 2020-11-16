package com.example.jormun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;

import org.json.JSONObject;


public class InternalDBManager {

    private static final int VERSION_BDD = 1;

    private static final String NOM_BDD = "game.db";

    private DirectoryManager dirmgrCurrent;

    private String sInternAnswer;

    private SQLiteDatabase bdd;

    private InternalDBBuilder GameDBSQLITE;

    public InternalDBManager(Context context,DirectoryManager dirmgrInput){
        //On crée la BDD et sa table
        GameDBSQLITE = new InternalDBBuilder(context, NOM_BDD, null, VERSION_BDD);
        dirmgrCurrent = dirmgrInput;
    }

    public String saveJSON(String sToken,String sType,JSONObject jsnToSave){
        getDirmgrCurrent().saveJson(sToken,sType,jsnToSave);
        return getDirmgrCurrent().getsPrevSavedFilePath();
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = GameDBSQLITE.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }
    /**
     * This is where you do opérations on db
     *
     */
    public void ShowTable(String sTableName){
        String sList;

        Boolean bReading;

        Cursor crsCurrent;

        crsCurrent = getBDD().rawQuery("SELECT * FROM "+sTableName+";",null);
        crsCurrent.moveToFirst();
        sList = "";
        bReading=true;
        do {
            if(crsCurrent.getCount()>0 && !crsCurrent.isAfterLast()){
                for (String sColName:crsCurrent.getColumnNames()) {
                    sList+=sColName+" = "+crsCurrent.getString(crsCurrent.getColumnIndex(sColName))+"|";
                }
                sList+="#";
                crsCurrent.moveToNext();
            }else{
                bReading=!bReading;
            }
        }while(bReading);
        System.out.println(sList);
    }
    //Gestion du system de token#START
    public String FetchCurrentToken(){
        Cursor crsToken = getBDD().rawQuery("SELECT "+InternalDBBuilder.COL_infouser_TOKEN+" FROM "+InternalDBBuilder.TABLE_infouser+" WHERE "+InternalDBBuilder.COL_infouser_ACTIVE+"=1",null);
        if(crsToken.getCount()==1){
            crsToken.moveToFirst();
            return  crsToken.getString(crsToken.getColumnIndex(InternalDBBuilder.COL_infouser_TOKEN));
        }else{
            return "";
        }
    }
    public String CheckIfUserExist(String sUsername){
        String sToken;

        try {
            Cursor crsCurrent = getBDD().rawQuery("SELECT " + InternalDBBuilder.COL_infouser_TOKEN + " FROM " + InternalDBBuilder.TABLE_infouser + " WHERE " + InternalDBBuilder.COL_infouser_USERNAME + " = ?;", new String[]{sUsername});
            if (crsCurrent.getCount() == 0) {
                return "0#Not-Found";
            }else{
                crsCurrent.moveToFirst();
                sToken = crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_infouser_TOKEN));
                if (crsCurrent.getCount() > 1) {
                    return "-2#Too-Many-Registered";
                } else {
                    return "1#Registered#" + sToken;
                }
            }
        }catch (SQLiteException e){
            return "-1#Error";
        }
    }
    public int CheckToken(){
        try {
            String sQuery = "SELECT "+InternalDBBuilder.COL_infouser_TOKEN+" FROM "+InternalDBBuilder.TABLE_infouser;
            Cursor crsCurrent = getBDD().rawQuery(sQuery,null);
            return crsCurrent.getCount();
        }catch (SQLiteException e){
            e.printStackTrace();
            return -1;
        }
    }
    public String CheckAliveToken(DBManager dbCurrent){
        String sQuery;
        String sQuery2;

        try {
            sQuery="SELECT "+InternalDBBuilder.COL_infouser_TOKEN+" FROM "+InternalDBBuilder.TABLE_infouser+" WHERE "+InternalDBBuilder.COL_infouser_ACTIVE+"=1;";
            Cursor crsCurrent = getBDD().rawQuery(sQuery,new String[]{});
            if(crsCurrent.getCount()>0) {
                crsCurrent.moveToFirst();
                setsInternAnswer(crsCurrent.getString(crsCurrent.getColumnIndex(InternalDBBuilder.COL_infouser_TOKEN)));
                sQuery2="sRequest=CheckDevice&sToken="+getsInternAnswer();
                if (dbCurrent.threadedRequest(DBManager.sREQUEST_GET,sQuery2).equals("REGISTERED")){
                    return "1#"+getsInternAnswer();
                }else {
                    sQuery2 = InternalDBBuilder.COL_currentteam_TOKEN+" = ?;";
                    getBDD().delete(InternalDBBuilder.TABLE_infouser,sQuery2,new String[]{getsInternAnswer()});
                    return "0#0";
                }
            }else{
                return "0#0";
            }
        }catch (SQLiteException e){
            e.printStackTrace();
            return "-1#0";
        }
    }
    public void PrintTable(String sTableName){
        Cursor crsRequest = getBDD().rawQuery("SELECT * FROM infouser;",null);
        StringBuilder sTable;
        if(crsRequest.getCount()>0){
            crsRequest.moveToFirst();
            sTable = new StringBuilder();
            for (String sTemp : crsRequest.getColumnNames()){
                sTable.append("| ").append(sTemp);
            }
            sTable.append(" |\n");
            do {
                for (String sTemp : crsRequest.getColumnNames()){
                    sTable.append("| ").append(crsRequest.getString(crsRequest.getColumnIndex(sTemp)));
                }
                sTable.append(" |\n");
                if(!crsRequest.isLast()){
                    crsRequest.moveToNext();
                }
            }while (!crsRequest.isLast());
            System.out.println(sTable);
        }else{
            System.out.println("TABLE EMPTY");
        }
    }
    public void RegisterNewPlayer(String sToken,String sUsername){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(InternalDBBuilder.COL_infouser_TOKEN, sToken);
            contentValues.put(InternalDBBuilder.COL_infouser_USERNAME, sUsername);
            contentValues.put(InternalDBBuilder.COL_infouser_ACTIVE, true);
            long result = getBDD().insert(InternalDBBuilder.TABLE_infouser, null, contentValues);
            if (result == -1){
                System.out.println("Insert Failed");
            }else{
                ChangePlayerState(sToken, true);
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public void ChangePlayerState(String sToken,boolean bState){
        try {
            ContentValues cvUpdate = new ContentValues();
            cvUpdate.put(InternalDBBuilder.COL_infouser_ACTIVE,bState);
            getBDD().update(InternalDBBuilder.TABLE_infouser,cvUpdate,InternalDBBuilder.COL_infouser_TOKEN+"=?",new String[]{sToken});
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    //Gestion du systeme de token#END
    public void SavePlayersHero(String sToken,String sPath){
        try {
            ContentValues cvInsert = new ContentValues();
            cvInsert.put(InternalDBBuilder.COL_hero_PATHJSON,sPath);
            cvInsert.put(InternalDBBuilder.COL_hero_TOKEN,sToken);
            getBDD().insert(InternalDBBuilder.TABLE_hero,"",cvInsert);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public void SavePlayersTeam(String sToken,String sPath){
        try {
            ContentValues cvInsert = new ContentValues();
            cvInsert.put(InternalDBBuilder.COL_currentteam_PATHJSON,sPath);
            cvInsert.put(InternalDBBuilder.COL_currentteam_TOKEN,sToken);
            getBDD().insert(InternalDBBuilder.TABLE_currentteam,"",cvInsert);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public String getsInternAnswer() {
        return sInternAnswer;
    }

    public void setsInternAnswer(String sInternAnswer) {
        this.sInternAnswer = sInternAnswer;
    }

    public DirectoryManager getDirmgrCurrent() {
        return dirmgrCurrent;
    }

    public void setDirmgrCurrent(DirectoryManager dirmgrCurrent) {
        this.dirmgrCurrent = dirmgrCurrent;
    }

}